package com.gameServer.home.character.controller;

import com.gameServer.common.cache.character.LoginCreateCharacterAnswer;
import com.gameServer.common.cache.character.LoginCreateCharacterAsk;
import com.gameServer.common.entity.CharacterPlayerUserEntity;
import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.gameServer.common.entity.composite.CharacterUserWeaponCompositeDataID;
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
    public void atCreateCharacterRequest(Session session, CreateCharacterRequest request, GatewayAttachment gatewayAttachment) {
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
        var weaponCreateData =
                characterService.createCharacterUserWeaponCompositeDataID(
                        config.getCharacterDefaultWeaponId(),
                        config.getWeaponType(), 0);
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
                                                                            1,
                                                                            weaponCreateData);
        logger.info("createCharacterPlayerUserEntity:{}", JsonUtils.object2String(entityCreate));
        OrmContext.getAccessor().insert(entityCreate);
        var character = CharacterWeaponIDData.valueOf();
        character.setWeaponId(weaponCreateData.getWeaponId());
        character.setWeaponFindId(weaponCreateData.getWeaponOrmIndex());

        CharacterBaseData characterBaseData = CharacterBaseData.valueOf();
        characterBaseData.setQuantity(config.getQuality());
        characterBaseData.setCharacterWeaponIDData(character);
        characterBaseData.setNowMaxLv(entity.getEntityNowMaxHp());

        var data = CreateCharacterResponse.valueOf();
        data.setCharacterBaseData(characterBaseData);
        NetContext.getRouter().send(session, data, gatewayAttachment);
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
                        1,
                        _weaponCreateData);
        characterUser.setUserUID(session.getUid());
        logger.info("createCharacterPlayerUserEntity:{}", JsonUtils.object2String(characterUser));
        OrmContext.getAccessor().insert(characterUser);
        var data = new LoginCreateCharacterAnswer();
        NetContext.getRouter().send(session, data);
    }
}
