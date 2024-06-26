package com.gameServer.common.ormEntity;

import com.gameServer.common.entity.composite.WeaponBreakthroughCompositeData;
import com.gameServer.common.entity.composite.WeaponProgressDataOrmEntity;
import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/11 23 43
 */
public class WeaponsDataConfigEntity implements IEntity<Integer> {
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
     * 武器主词条的所属属性type
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
    protected List<WeaponBreakthroughCompositeData> weaponBreakthrough;
    /**
     * 当前武器等级最大值
     */
    protected int maxLv;
    /**
     * 武器品质
     */
    protected int weaponQuality;
    /**
     * 武器进阶几次 固定的
     */
    protected int weaponOrderNum;
    /**
     *
     */
    protected int weaponReinforcementEqualOrder;
    /**
     * 背包里面 icon
     */
    protected String bagIconResource;
    /**
     * 武器强化等级
     */
    protected List<WeaponProgressDataOrmEntity> weaponProgressData;
    /**
     * 创建时间
     */
    private String createAt;
    /**
     * 更新时间
     */
    private String updateAt;

    @Override
    public Integer id() {
        return id;
    }

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

    public List<WeaponBreakthroughCompositeData> getWeaponBreakthrough() {
        return weaponBreakthrough;
    }

    public void setWeaponBreakthrough(List<WeaponBreakthroughCompositeData> weaponBreakthrough) {
        this.weaponBreakthrough = weaponBreakthrough;
    }

    public int getMaxLv() {
        return maxLv;
    }

    public void setMaxLv(int maxLv) {
        this.maxLv = maxLv;
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

    public int getWeaponMainInitType() {
        return weaponMainInitType;
    }

    public void setWeaponMainInitType(int weaponMainInitType) {
        this.weaponMainInitType = weaponMainInitType;
    }

    public static WeaponsDataConfigEntity valueOf() {
        return new WeaponsDataConfigEntity();
    }

    public int getWeaponQuality() {
        return weaponQuality;
    }

    public void setWeaponQuality(int weaponQuality) {
        this.weaponQuality = weaponQuality;
    }

    public int getWeaponOrderNum() {
        return weaponOrderNum;
    }

    public void setWeaponOrderNum(int weaponOrderNum) {
        this.weaponOrderNum = weaponOrderNum;
    }

    public int getWeaponReinforcementEqualOrder() {
        return weaponReinforcementEqualOrder;
    }

    public void setWeaponReinforcementEqualOrder(int weaponReinforcementEqualOrder) {
        this.weaponReinforcementEqualOrder = weaponReinforcementEqualOrder;
    }

    public String getBagIconResource() {
        return bagIconResource;
    }

    public void setBagIconResource(String bagIconResource) {
        this.bagIconResource = bagIconResource;
    }

    public List<WeaponProgressDataOrmEntity> getWeaponProgressData() {
        return weaponProgressData;
    }

    public void setWeaponProgressData(List<WeaponProgressDataOrmEntity> weaponProgressData) {
        this.weaponProgressData = weaponProgressData;
    }
}
