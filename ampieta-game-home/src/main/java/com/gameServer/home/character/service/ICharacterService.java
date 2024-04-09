package com.gameServer.home.character.service;

import com.gameServer.common.entity.CharacterPlayerUserEntity;
import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.gameServer.common.entity.composite.CharacterUserWeaponCompositeDataID;

/**
 * 角色 service 接口
 */
public interface ICharacterService {
    /**
     * @param weaponId       武器 id
     * @param weaponType     武器 type
     * @param weaponOrmIndex 武器下标
     * @return 武器 id 加 type index
     */
    CharacterUserWeaponCompositeDataID createCharacterUserWeaponCompositeDataID(int weaponId, int weaponType, int weaponOrmIndex);

    /**
     *
     * @param dataID 角色id + uid
     * @param entityHp 当前角色 HP 没有加上 装备词条
     * @param entityMaxHp 当前角色 最大 HP 没有加上 装备词条
     * @param entityNowHp 当前角色 HP 加上 装备词条 得出来的
     * @param entityNowMaxHp 当前角色 最大 HP 加上 装备词条
     * @param entityAtk 当前角色 攻击力 没有加上 装备词条
     * @param entityMaxAtk 当前角色 最大 攻击力 没有加上 装备词条
     * @param entityNowAtk 当前角色 攻击力 加上 装备词条 得出来的
     * @param entityNowMaxAtk 当前角色 最大 攻击力 加上 装备词条
     * @param nowDef 当前防御力
     * @param leveCriticalHitChance 暴击率
     * @param levelElementMastery 元素精通
     * @param levelChargingEfficiencyOfElements 元素充能效率
     * @param levelCriticalHitDamage 暴击伤害
     * @param elementHitDamage 元素伤害
     * @param elementType 角色自身 元素伤害 type
     * @param weaponCompositeDataID 武器 穿戴
     * @return 玩家所拥有的角色
     */
    CharacterPlayerUserEntity createCharacterPlayerUserEntity(CharacterUserCompositeDataID dataID,
                                                              int entityHp, int entityMaxHp,
                                                              int entityNowHp, int entityNowMaxHp,
                                                              int entityAtk, int entityMaxAtk,
                                                              int entityNowAtk, int entityNowMaxAtk,
                                                              int nowDef, int leveCriticalHitChance,
                                                              int levelElementMastery, int levelChargingEfficiencyOfElements,
                                                              int levelCriticalHitDamage, int elementHitDamage,
                                                              int elementType, CharacterUserWeaponCompositeDataID weaponCompositeDataID);
}
