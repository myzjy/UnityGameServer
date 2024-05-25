package com.gameServer.common.protocol.weapon;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
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
     * 在
     */
    @Note("id")
    private long id;
    /**
     * 武器名字
     */
    @Note("武器名字")
    private String weaponName;
    /**
     * 武器 type 武器所属类型
     */
    @Note("武器 type 武器所属类型")
    private int weaponType;
    /**
     * 当前武器所属技能
     */
    @Note("当前武器所属技能")
    private int nowSkills;
    /**
     * 武器主词条的数值
     */
    @Note("武器主词条的数值")
    private int weaponMainValue;
    /**
     * 武器主词条的所属type
     */
    @Note("武器主词条的所属type")
    private int weaponMainValueType;
    /**
     * 武器获取到的第一时间
     */
    @Note("武器获取到的第一时间")
    private String haveTimeAt;
    /**
     * 当前武器强化到的等级
     */
    @Note("当前武器强化到的等级")
    private int nowLv;
    /**
     * 当前武器能强化到的最大等级
     * 强化到特定等级会有突破
     * 突破之后这个最大等级就会变化
     * 直到没有可以突破等级
     */
    @Note("当前武器能强化到的最大等级")
    private int nowMaxLv;
    /**
     * 当前 等级 已经强化 到的经验
     */
    @Note("当前 等级 已经强化 到的经验")
    private int nowLvExp;
    /**
     * 当前等级已知的可以强化的最大经验
     */
    @Note("当前等级已知的可以强化的最大经验")
    private int nowLvMaxExp;
    /**
     * 武器Icon
     */
    @Note("武器Icon")
    private String weaponIcons;
    /**
     * 武器模型所属资源名
     */
    @Note("武器模型所属资源名")
    private String weaponModelNameIcons;
    /**
     * 背包里面的武器icon
     */
    @Note("背包里面的武器icon")
    private String bagWeaponIcon;
    /**
     * 武器 id
     */
    @Note("武器 id")
    private int weaponId;

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

    public String getBagWeaponIcon() {
        return bagWeaponIcon;
    }

    public void setBagWeaponIcon(String bagWeaponIcon) {
        this.bagWeaponIcon = bagWeaponIcon;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public static WeaponPlayerUserDataStruct ValueOf() {
        return new WeaponPlayerUserDataStruct();
    }
}
