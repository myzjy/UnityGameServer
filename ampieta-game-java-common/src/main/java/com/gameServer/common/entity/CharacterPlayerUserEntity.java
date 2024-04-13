package com.gameServer.common.entity;

import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.gameServer.common.entity.composite.CharacterUserWeaponCompositeDataID;
import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/16 23 17
 */
public class CharacterPlayerUserEntity implements IEntity<CharacterUserCompositeDataID> {
    /**
     * 角色id + uid
     */
    private CharacterUserCompositeDataID dataID;
    @Id
    private CharacterUserCompositeDataID id;

    @Override
    public CharacterUserCompositeDataID id() {
        return id;
    }

    /**
     *
     */
    private int nowLv;
    private int nowMaxLv;
    /**
     * 当前角色 HP 没有加上 装备词条
     */
    private int entityHp;
    /**
     * 当前角色 最大 HP 没有加上 装备词条
     */
    private int entityMaxHp;
    /**
     * 当前角色 HP 加上 装备词条 得出来的
     */
    private int entityNowHp;
    /**
     * 当前角色 最大 HP 加上 装备词条
     */
    private int entityNowMaxHp;
    /**
     * 当前角色 攻击力 没有加上 装备词条
     */
    private int entityAtk;
    /**
     * 当前角色 最大 攻击力 没有加上 装备词条
     */
    private int entityMaxAtk;
    /**
     * 当前角色 攻击力 加上 装备词条 得出来的
     */
    private int entityNowAtk;
    /**
     * 当前角色 最大 攻击力 加上 装备词条
     */
    private int entityNowMaxAtk;
    /**
     * 当前防御力
     */
    private int nowDef;
    /**
     * 暴击率
     */
    private int leveCriticalHitChance;
    /**
     * 元素精通
     */
    private int levelElementMastery;
    /**
     * 元素充能效率
     */
    private int levelChargingEfficiencyOfElements;
    /**
     * 暴击伤害
     */
    private int levelCriticalHitDamage;
    /**
     * 元素伤害
     */
    private int elementHitDamage;
    /**
     * 角色自身 元素伤害 type
     */
    private int elementType;
    /**
     * 当前 角色 所属 玩家 uid
     */
    private long userUID;
    /**
     * 当前强化星阶
     */
    private int nowReinforcementEqualOrder;
    /**
     * 当前强化的星阶 最大
     */
    private int maxReinforcementEqualOrder;
    /**
     * 武器 穿戴
     */
    private CharacterUserWeaponCompositeDataID weaponCompositeDataID;

    /**
     * 角色数据创建
     */
    public static CharacterPlayerUserEntity ValueOf() {
        return new CharacterPlayerUserEntity();
    }

    public CharacterUserCompositeDataID getDataID() {
        return dataID;
    }

    public void setDataID(CharacterUserCompositeDataID dataID) {
        this.id = dataID;
        this.dataID = dataID;
    }

    public int getEntityHp() {
        return entityHp;
    }

    public void setEntityHp(int entityHp) {
        this.entityHp = entityHp;
    }

    public int getEntityMaxHp() {
        return entityMaxHp;
    }

    public void setEntityMaxHp(int entityMaxHp) {
        this.entityMaxHp = entityMaxHp;
    }

    public int getEntityNowHp() {
        return entityNowHp;
    }

    public void setEntityNowHp(int entityNowHp) {
        this.entityNowHp = entityNowHp;
    }

    public int getEntityNowMaxHp() {
        return entityNowMaxHp;
    }

    public void setEntityNowMaxHp(int entityNowMaxHp) {
        this.entityNowMaxHp = entityNowMaxHp;
    }

    public int getNowDef() {
        return nowDef;
    }

    public void setNowDef(int nowDef) {
        this.nowDef = nowDef;
    }

    public int getLeveCriticalHitChance() {
        return leveCriticalHitChance;
    }

    public void setLeveCriticalHitChance(int leveCriticalHitChance) {
        this.leveCriticalHitChance = leveCriticalHitChance;
    }

    public int getLevelElementMastery() {
        return levelElementMastery;
    }

    public void setLevelElementMastery(int levelElementMastery) {
        this.levelElementMastery = levelElementMastery;
    }

    public int getLevelChargingEfficiencyOfElements() {
        return levelChargingEfficiencyOfElements;
    }

    public void setLevelChargingEfficiencyOfElements(int levelChargingEfficiencyOfElements) {
        this.levelChargingEfficiencyOfElements = levelChargingEfficiencyOfElements;
    }

    public int getLevelCriticalHitDamage() {
        return levelCriticalHitDamage;
    }

    public void setLevelCriticalHitDamage(int levelCriticalHitDamage) {
        this.levelCriticalHitDamage = levelCriticalHitDamage;
    }

    public int getElementHitDamage() {
        return elementHitDamage;
    }

    public void setElementHitDamage(int elementHitDamage) {
        this.elementHitDamage = elementHitDamage;
    }

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public int getEntityAtk() {
        return entityAtk;
    }

    public void setEntityAtk(int entityAtk) {
        this.entityAtk = entityAtk;
    }

    public int getEntityMaxAtk() {
        return entityMaxAtk;
    }

    public void setEntityMaxAtk(int entityMaxAtk) {
        this.entityMaxAtk = entityMaxAtk;
    }

    public int getEntityNowAtk() {
        return entityNowAtk;
    }

    public void setEntityNowAtk(int entityNowAtk) {
        this.entityNowAtk = entityNowAtk;
    }

    public int getEntityNowMaxAtk() {
        return entityNowMaxAtk;
    }

    public void setEntityNowMaxAtk(int entityNowMaxAtk) {
        this.entityNowMaxAtk = entityNowMaxAtk;
    }

    public CharacterUserWeaponCompositeDataID getWeaponCompositeDataID() {
        return weaponCompositeDataID;
    }

    public void setWeaponCompositeDataID(CharacterUserWeaponCompositeDataID weaponCompositeDataID) {
        this.weaponCompositeDataID = weaponCompositeDataID;
    }

    public long getUserUID() {
        return userUID;
    }

    public void setUserUID(long userUID) {
        this.userUID = userUID;
    }

    public int getNowLv() {
        return nowLv;
    }

    public void setNowLv(int nowLv) {
        this.nowLv = nowLv;
    }

    public int getNowMaxLv() {
        return nowMaxLv;
    }

    public void setNowMaxLv(int nowMaxLv) {
        this.nowMaxLv = nowMaxLv;
    }

    public int getNowReinforcementEqualOrder() {
        return nowReinforcementEqualOrder;
    }

    public void setNowReinforcementEqualOrder(int nowReinforcementEqualOrder) {
        this.nowReinforcementEqualOrder = nowReinforcementEqualOrder;
    }

    public int getMaxReinforcementEqualOrder() {
        return maxReinforcementEqualOrder;
    }

    public void setMaxReinforcementEqualOrder(int maxReinforcementEqualOrder) {
        this.maxReinforcementEqualOrder = maxReinforcementEqualOrder;
    }
}
