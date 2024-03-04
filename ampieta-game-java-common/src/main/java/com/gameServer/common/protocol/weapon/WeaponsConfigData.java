package com.gameServer.common.protocol.weapon;

import com.zfoo.protocol.anno.Protocol;
import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/3/2 00 02
 */
@Protocol(id = 214)
public class WeaponsConfigData {
    @Id
    @Index
    protected int id;
    /**
     * 武器名字
     */
    protected String weaponName;
    /**
     * 武器类型
     */
    protected int weaponType;
    /**
     * 武器技能
     */
    protected int weaponSkills;
    /**
     * 武器1级初始值
     */
    protected String weaponInitValue;
    /**
     * 武器强化1-20级每级强化数字
     */
    protected String weaponInitProgress;
    /**
     * icon资源
     */
    protected String iconResource;
    /**
     * 武器升级到特定等21级会突破在之后会加数值
     */
    protected String weaponBreakthrough;
    /**
     * 当前武器等级最大值
     */
    protected int maxLv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getWeaponSkills() {
        return weaponSkills;
    }

    public void setWeaponSkills(int weaponSkills) {
        this.weaponSkills = weaponSkills;
    }

    public String getWeaponInitValue() {
        return weaponInitValue;
    }

    public void setWeaponInitValue(String weaponInitValue) {
        this.weaponInitValue = weaponInitValue;
    }

    public String getWeaponInitProgress() {
        return weaponInitProgress;
    }

    public void setWeaponInitProgress(String weaponInitProgress) {
        this.weaponInitProgress = weaponInitProgress;
    }

    public String getIconResource() {
        return iconResource;
    }

    public void setIconResource(String iconResource) {
        this.iconResource = iconResource;
    }

    public String getWeaponBreakthrough() {
        return weaponBreakthrough;
    }

    public void setWeaponBreakthrough(String weaponBreakthrough) {
        this.weaponBreakthrough = weaponBreakthrough;
    }

    public int getMaxLv() {
        return maxLv;
    }

    public void setMaxLv(int maxLv) {
        this.maxLv = maxLv;
    }
}
