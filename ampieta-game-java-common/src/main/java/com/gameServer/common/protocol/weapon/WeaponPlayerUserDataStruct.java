package com.gameServer.common.protocol.weapon;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.Objects;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/13 22 22
 */
@Protocol(id = 213)
public class WeaponPlayerUserDataStruct implements IPacket {
    /**
     * 武器id
     */
    private long id;
    /**
     * 武器名字
     */
    private String weaponName;
    /**
     * 武器 type 武器所属类型
     */
    private int weaponType;
    /**
     * 当前武器所属技能
     */
    private int nowSkills;
    /**
     * 武器主词条的数值
     */
    private int weaponMainValue;
    /**
     * 武器主词条的所属type
     */
    private int weaponMainValueType;
    /**
     * 武器获取到的第一时间
     */
    private String haveTimeAt;
    /**
     * 当前武器强化到的等级
     */
    private int nowLv;
    /**
     * 当前武器能强化到的最大等级
     * 强化到特定等级会有突破
     * 突破之后这个最大等级就会变化
     * 直到没有可以突破等级
     */
    private int nowMaxLv;
    /**
     * 当前 等级 已经强化 到的经验
     */
    private int nowLvExp;
    /**
     * 当前等级已知的可以强化的最大经验
     */
    private int nowLvMaxExp;
    /**
     * 武器Icon
     */
    private String weaponIcons;
    /**
     * 武器模型所属资源名
     */
    private String weaponModelNameIcons;

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

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    public int getNowSkills() {
        return nowSkills;
    }

    public void setNowSkills(int nowSkills) {
        this.nowSkills = nowSkills;
    }

    public int getWeaponMainValue() {
        return weaponMainValue;
    }

    public void setWeaponMainValue(int weaponMainValue) {
        this.weaponMainValue = weaponMainValue;
    }

    public int getWeaponMainValueType() {
        return weaponMainValueType;
    }

    public void setWeaponMainValueType(int weaponMainValueType) {
        this.weaponMainValueType = weaponMainValueType;
    }

    public String getHaveTimeAt() {
        return haveTimeAt;
    }

    public void setHaveTimeAt(String haveTimeAt) {
        this.haveTimeAt = haveTimeAt;
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

    public String getWeaponIcons() {
        return weaponIcons;
    }

    public void setWeaponIcons(String weaponIcons) {
        this.weaponIcons = weaponIcons;
    }

    public String getWeaponModelNameIcons() {
        return weaponModelNameIcons;
    }

    public void setWeaponModelNameIcons(String weaponModelNameIcons) {
        this.weaponModelNameIcons = weaponModelNameIcons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WeaponPlayerUserDataStruct that = (WeaponPlayerUserDataStruct) o;
        return id == that.id && weaponType == that.weaponType && nowSkills == that.nowSkills && weaponMainValue == that.weaponMainValue && weaponMainValueType == that.weaponMainValueType && nowLv == that.nowLv && nowMaxLv == that.nowMaxLv && nowLvExp == that.nowLvExp && nowLvMaxExp == that.nowLvMaxExp && Objects.equals(weaponName, that.weaponName) && Objects.equals(haveTimeAt, that.haveTimeAt) && Objects.equals(weaponIcons, that.weaponIcons) && Objects.equals(weaponModelNameIcons, that.weaponModelNameIcons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weaponName, weaponType, nowSkills, weaponMainValue, weaponMainValueType, haveTimeAt, nowLv, nowMaxLv, nowLvExp, nowLvMaxExp, weaponIcons, weaponModelNameIcons);
    }

    public static WeaponPlayerUserDataStruct ValueOf() {
        return new WeaponPlayerUserDataStruct();
    }
}
