package com.gameServer.common.entity.composite;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/14 00 50
 */
public class WeaponUserCompositeDataID implements Comparable<WeaponUserCompositeDataID> {
    /**
     * 武器id
     */
    private int weaponID;
    private long uid;

    @Override
    public int compareTo(WeaponUserCompositeDataID o) {
        return 0;
    }
}
