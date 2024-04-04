package com.gameServer.common.entity;

import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
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
    @Id
    private CharacterUserCompositeDataID dataID;

    @Override
    public CharacterUserCompositeDataID id() {
        return null;
    }

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
     * 当前防御力
     */
    private int nowDef;
    /**
     * 暴击率
     */
    private int LeveCriticalHitChance;
    /**
     * 元素精通
     */
    private int LevelElementMastery;
    /**
     * 元素充能效率
     */
    private int LevelChargingEfficiencyOfElements;
    /**
     * 暴击伤害
     */
    private int LevelCriticalHitDamage;
    /**
     * 元素伤害
     */
    private int elementHitDamage;
    /**
     * 角色自身 元素伤害 type
     */
    private int elementType;

    /**
     * 角色数据创建
     */
    public static CharacterPlayerUserEntity ValueOf() {
        var data = new CharacterPlayerUserEntity();
        return data;
    }

    public CharacterUserCompositeDataID getDataID() {
        return dataID;
    }

    public void setDataID(CharacterUserCompositeDataID dataID) {
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
}
