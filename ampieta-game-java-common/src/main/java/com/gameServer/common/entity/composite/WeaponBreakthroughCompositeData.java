package com.gameServer.common.entity.composite;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/5/7 23 26
 */
public class WeaponBreakthroughCompositeData implements Comparable<WeaponBreakthroughCompositeData>{
    @Override
    public int compareTo(WeaponBreakthroughCompositeData o) {
        return 0;
    }
    /**
     * 阶段武器等级
     */
    private int weaponLv;
    /**
     * 当前等级突破之后
     */
    private int weaponAddNum;

    public int getWeaponLv() {
        return weaponLv;
    }

    public void setWeaponLv(int weaponLv) {
        this.weaponLv = weaponLv;
    }

    public int getWeaponAddNum() {
        return weaponAddNum;
    }

    public void setWeaponAddNum(int weaponAddNum) {
        this.weaponAddNum = weaponAddNum;
    }

    public static WeaponBreakthroughCompositeData valueOf() {
        return new WeaponBreakthroughCompositeData();
    }
}
