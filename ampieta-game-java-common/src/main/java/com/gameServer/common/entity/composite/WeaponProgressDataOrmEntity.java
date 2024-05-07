package com.gameServer.common.entity.composite;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/5/7 23 15
 */
public class WeaponProgressDataOrmEntity implements Comparable<WeaponProgressDataOrmEntity> {
    @Override
    public int compareTo(WeaponProgressDataOrmEntity o) {
        return 0;
    }

    /**
     * 阶段武器等级
     */
    private int weaponLv;
    /**
     * 当前等级突破之后
     */
    private String weaponAddNum;

    public int getWeaponLv() {
        return weaponLv;
    }

    public void setWeaponLv(int weaponLv) {
        this.weaponLv = weaponLv;
    }

    public String getWeaponAddNum() {
        return weaponAddNum;
    }

    public void setWeaponAddNum(String weaponAddNum) {
        this.weaponAddNum = weaponAddNum;
    }

    public static WeaponProgressDataOrmEntity valueOf() {
        return new WeaponProgressDataOrmEntity();
    }
}
