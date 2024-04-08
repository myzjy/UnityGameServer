package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/2 23 29
 */
@Storage
public class WeaponsDataConfigResource {
    @Id
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
    /**
     * 武器品质
     */
    protected int weaponQuality;

    public int getId() {
        return id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public int getWeaponSkills() {
        return weaponSkills;
    }

    public String getWeaponInitValue() {
        return weaponInitValue;
    }

    public String getWeaponInitProgress() {
        return weaponInitProgress;
    }

    public String getIconResource() {
        return iconResource;
    }

    public String getWeaponBreakthrough() {
        return weaponBreakthrough;
    }

    public int getMaxLv() {
        return maxLv;
    }

    public int getWeaponQuality() {
        return weaponQuality;
    }
}
