package com.gameServer.common.entity.weapon;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/13 13 27
 */
public class WeaponUsePlayerDataEntity implements IEntity<Long> {
    /**
     * 下标
     */
    @Id
    private long id;

    @Override
    public Long id() {
        return id;
    }

    /**
     * 武器名字
     */
    private String weaponName;

    /**
     * 当前装备所属玩家
     */
    private long userUid;
    /**
     * 武器id
     */
    private int weaponId;
    /**
     * 武器当前等级
     */
    private int lv;
    /**
     * 当前最大等级
     */
    private int nowMaxLv;
    /**
     * 当前强化星阶
     */
    private int nowReinforcementEqualOrder;
    /**
     * 当前强化的星阶 最大
     */
    private int maxReinforcementEqualOrder;
    /**
     * 武器技能
     */
    private int weaponsSkill;
    /**
     * 武器 强化等阶 关联到 这把武器 所使用技能的数值 等阶越高 数值就越高
     */
    private int nowOrderNum;
    /**
     * 当前武器 强化等阶 最高等级
     */
    private int maxOrderNum;
    /**
     * 当前经验
     */
    private int nowLvExp;
    /**
     * 当前武器等级需要的最大经验
     */
    private int nowLvMaxExp;
    /**
     * 武器主属性数值
     */
    private int weaponValue;
    /**
     * 是否被锁
     */
    private boolean lock;
    /**
     * 创建时间
     */
    private String createAt;
    /**
     * 更新时间
     */
    private String updateAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public long getUserUid() {
        return userUid;
    }

    public void setUserUid(long userUid) {
        this.userUid = userUid;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
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

    public int getWeaponsSkill() {
        return weaponsSkill;
    }

    public void setWeaponsSkill(int weaponsSkill) {
        this.weaponsSkill = weaponsSkill;
    }

    public int getNowOrderNum() {
        return nowOrderNum;
    }

    public void setNowOrderNum(int nowOrderNum) {
        this.nowOrderNum = nowOrderNum;
    }

    public int getMaxOrderNum() {
        return maxOrderNum;
    }

    public void setMaxOrderNum(int maxOrderNum) {
        this.maxOrderNum = maxOrderNum;
    }

    public int getNowLvExp() {
        return nowLvExp;
    }

    public void setNowLvExp(int nowLvExp) {
        this.nowLvExp = nowLvExp;
    }

    public int getNowLvMaxExp() {
        return nowLvMaxExp;
    }

    public void setNowLvMaxExp(int nowLvMaxExp) {
        this.nowLvMaxExp = nowLvMaxExp;
    }

    public int getWeaponValue() {
        return weaponValue;
    }

    public void setWeaponValue(int weaponValue) {
        this.weaponValue = weaponValue;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public static WeaponUsePlayerDataEntity ValueOf() {
        return new WeaponUsePlayerDataEntity();
    }
}