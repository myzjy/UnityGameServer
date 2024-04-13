package com.gameServer.home.character.controller;

import com.gameServer.common.cache.character.LoginCreateCharacterAnswer;
import com.gameServer.common.cache.character.LoginCreateCharacterAsk;
import com.gameServer.common.cache.weapon.CreateWeaponDefaultAnswer;
import com.gameServer.common.cache.weapon.CreateWeaponDefaultAsk;
import com.gameServer.common.entity.CharacterPlayerUserEntity;
import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.gameServer.common.entity.composite.CharacterUserWeaponCompositeDataID;
import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.gameServer.common.ormEntity.CharacterConfigEntity;
import com.gameServer.common.protocol.character.*;
import com.gameServer.home.character.service.ICharacterService;
import com.gameServer.home.weapon.service.IWeaponService;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 角色 协议回调
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 23 43
 */
@Component
public class CharacterUserController {
    private static final Logger logger = LoggerFactory.getLogger(CharacterUserController.class);
    @Autowired
    private IWeaponService iWeaponService;
    @Autowired
    private ICharacterService characterService;

    @PacketReceiver
    public void atAcquireCharacterRequest(Session session, AcquireCharacterRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
    }

    @PacketReceiver
    public void atCreateCharacterRequest(Session session, CreateCharacterRequest request, GatewayAttachment gatewayAttachment) throws Exception {
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
        var findId = new CharacterUserCompositeDataID();
        findId.setCharacterId(request.getCharacterId());
        findId.setUid(session.getUid());
        logger.info("[当前查找data:{}]", JsonUtils.object2String(findId));
        //查找到的角色相关数据
        var entity = OrmContext.getAccessor().load(findId, CharacterPlayerUserEntity.class);
        if (entity != null) {
            logger.info("[当前创建相关角色 data:{}] 数据库存在，请检查 协议消息", JsonUtils.object2String(findId));
            NetContext.getRouter().send(session, Error.valueOf("角色不可重复"), gatewayAttachment);
            return;
        }
        //基础属性
        var config = OrmContext.getAccessor().load(findId.getCharacterId(), CharacterConfigEntity.class);
        if (config == null) {
            logger.info("数据错误");
            NetContext.getRouter().send(session, Error.valueOf("数据错误"), gatewayAttachment);
            return;
        }
        var weaponConfig = OrmContext.getQuery(WeaponUsePlayerDataEntity.class)
                                     .eq("weaponId", config.getCharacterDefaultWeaponId()).eq("userUid", session.getUid()).
                                     gt("userPlayerId", 0).queryFirst();
        if (weaponConfig == null) {
            var sk = CreateWeaponDefaultAsk.valueOf(config.getCharacterDefaultWeaponId(), config.getWeaponType(), 10001, session.getUid());
            var Data = NetContext.getConsumer().syncAsk(sk, CreateWeaponDefaultAnswer.class, null).packet();
            weaponConfig = OrmContext.getAccessor().load(Data.getWeaponIndex(), WeaponUsePlayerDataEntity.class);
        }
        CharacterUserWeaponCompositeDataID weaponCreateData =
                null;
        if (weaponConfig != null) {
            weaponCreateData = characterService.createCharacterUserWeaponCompositeDataID(
                    config.getCharacterDefaultWeaponId(),
                    config.getWeaponType(), weaponConfig.id());
        }
        var entityCreate = characterService.createCharacterPlayerUserEntity(findId,
                                                                            config.getLevel1HpValue(),
                                                                            config.getLevel1HpValue(),
                                                                            config.getLevel1HpValue(),
                                                                            config.getLevel1HpValue(),
                                                                            config.getLevel1Atk(),
                                                                            config.getLevel1Atk(),
                                                                            config.getLevel1Atk(),
                                                                            config.getLevel1Atk(),
                                                                            config.getLevel1Def(),
                                                                            config.getLevel1CriticalHitChance(),
                                                                            config.getLevel1ElementMastery(),
                                                                            config.getLevel1CriticalHitDamage(),
                                                                            config.getLevel1ChargingEfficiencyOfElements(),
                                                                            0,
                                                                            config.getElementType(),
                                                                            weaponCreateData);
        logger.info("createCharacterPlayerUserEntity:{}", JsonUtils.object2String(entityCreate));
        OrmContext.getAccessor().insert(entityCreate);
        CreateCharacterResponse data = null;
        if (weaponCreateData != null) {
            data = getInitCreateCharacterResponse(weaponCreateData, config);
        }
        NetContext.getRouter().send(session, data, gatewayAttachment);
    }

    /**
     * 初始 创建
     *
     * @param weaponCreateData 角色 身上装备的武器 结构：武器id + 武器类型 + 玩家获取的武器在数据库中的orm index
     * @param config           这个角色 的基础数据
     * @return 返回 创建 角色成功的 Response 里面 附带 角色数据
     */
    private CreateCharacterResponse getInitCreateCharacterResponse(CharacterUserWeaponCompositeDataID weaponCreateData, CharacterConfigEntity config) {
        var character = CharacterWeaponIDData.valueOf();
        character.setWeaponId(weaponCreateData.getWeaponId());
        character.setWeaponFindId(weaponCreateData.getWeaponOrmIndex());
        CharacterBaseData characterBaseData = getCharacterBaseData(config, character);
        var data = CreateCharacterResponse.valueOf();
        data.setCharacterBaseData(characterBaseData);
        return data;
    }

    /**
     * 角色数据
     *
     * @param config    这个角色 的基础数据
     * @param character 角色 身上装备的武器 结构：武器id + 武器类型 + 玩家获取的武器在数据库中的orm index
     * @return 这个角色 的基础数据
     */
    private CharacterBaseData getCharacterBaseData(CharacterConfigEntity config, CharacterWeaponIDData character) {
        CharacterBaseData characterBaseData = CharacterBaseData.valueOf();
        characterBaseData.setQuantity(config.getQuality());
        characterBaseData.setCharacterWeaponIDData(character);
        characterBaseData.setLv(config.getLvInit());
        characterBaseData.setNowMaxLv(config.getInitLvMax());
        characterBaseData.setElementType(config.getElementType());
        characterBaseData.setElementNum(0);
        //初始的 没有 装备 圣遗物
        characterBaseData.setEquipmentList(new ArrayList<>());
        return characterBaseData;
    }

    @PacketReceiver
    public void atLoginCreateCharacterAsk(Session session, LoginCreateCharacterAsk loginCreateCharacterAsk) throws Exception {
        logger.info("[当前服务器调用时间{}] [调用协议: 6003 ]", TimeUtils.simpleDateString());
        // 需要创建的角色 id
        var playerCreteId = loginCreateCharacterAsk.getPlayerId();
        //当前 角色 配置
        var config = OrmContext.getAccessor().load(playerCreteId, CharacterConfigEntity.class);
        if (config == null) {
            return;
        }
        // 创建武器的 rpc
        // 创建角色
        var findId = new CharacterUserCompositeDataID();
        findId.setCharacterId(playerCreteId);
        findId.setUid(session.getUid());
        var characterUser = CharacterPlayerUserEntity.ValueOf();
        CharacterUserWeaponCompositeDataID _weaponCreateData =
                characterService.createCharacterUserWeaponCompositeDataID(
                        config.getCharacterDefaultWeaponId(),
                        config.getWeaponType(), loginCreateCharacterAsk.getWeaponIndex());
        characterUser = characterService
                .createCharacterPlayerUserEntity(
                        findId, config.getLevel1HpValue(),
                        config.getLevel1HpValue(), config.getLevel1HpValue(),
                        config.getLevel1HpValue(), config.getLevel1Atk(),
                        config.getLevel1Atk(), config.getLevel1Atk(),
                        config.getLevel1Atk(), config.getLevel1Def(),
                        config.getLevel1CriticalHitChance(), config.getLevel1ElementMastery(),
                        config.getLevel1CriticalHitDamage(), config.getLevel1ChargingEfficiencyOfElements(),
                        0,
                        config.getElementType(),
                        _weaponCreateData);
        characterUser.setUserUID(session.getUid());
        // 角色创建 1级
        characterUser.setNowLv(config.getLvInit());
        // 角色 初始
        characterUser.setNowMaxLv(config.getInitLvMax());
        characterUser.setNowReinforcementEqualOrder(0);
        characterUser.setMaxReinforcementEqualOrder(config.getMaxReinforcementEqualOrder());
        logger.info("createCharacterPlayerUserEntity:{}", JsonUtils.object2String(characterUser));
        OrmContext.getAccessor().insert(characterUser);
        var data = new LoginCreateCharacterAnswer();
        NetContext.getRouter().send(session, data);
    }
}
