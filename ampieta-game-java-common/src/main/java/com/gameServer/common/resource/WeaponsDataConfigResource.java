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
    /**
     * 武器ID
     */
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
     * 武器主属性的 type
     */
    protected int weaponMainInitType;
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
    /**
     * 武器可以进阶几次  除开最低品质的 一般来说都是 5次
     */
    protected int orderNum;
    /**
     * 武器强化到一定等级可以突破 这个过程几次
     */
    protected int reinforcementEqualOrder;
    /**
     * 背包里面 icon
     */
    protected String bagIconResource;
    /**
     * 武器强化20级之每个阶段所强化数值
     */
    protected String weaponProgress;

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

    public int getOrderNum() {
        return orderNum;
    }

    public int getWeaponMainInitType() {
        return weaponMainInitType;
    }

    public int getReinforcementEqualOrder() {
        return reinforcementEqualOrder;
    }

    public String getBagIconResource() {
        return bagIconResource;
    }

    public String getWeaponProgress() {
        return weaponProgress;
    }
}
