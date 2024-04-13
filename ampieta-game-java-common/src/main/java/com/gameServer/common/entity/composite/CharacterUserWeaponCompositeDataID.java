package com.gameServer.common.entity.composite;

/**
 * 角色 身上装备的武器 结构：武器id + 武器类型 + 玩家获取的武器在数据库中的orm index
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/5 23 51
 */
public class CharacterUserWeaponCompositeDataID implements Comparable<CharacterUserWeaponCompositeDataID> {
    @Override
    public int compareTo(CharacterUserWeaponCompositeDataID o) {
        return 0;
    }

    /**
     * 武器 id
     */
    private int weaponId;
    /**
     * 武器 类型
     */
    private int weaponType;
    /**
     * 武器 下标
     */
    private long weaponOrmIndex;

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    public long getWeaponOrmIndex() {
        return weaponOrmIndex;
    }

    public void setWeaponOrmIndex(long weaponOrmIndex) {
        this.weaponOrmIndex = weaponOrmIndex;
    }

    public static CharacterUserWeaponCompositeDataID valueOf() {
        return new CharacterUserWeaponCompositeDataID();
    }
}
