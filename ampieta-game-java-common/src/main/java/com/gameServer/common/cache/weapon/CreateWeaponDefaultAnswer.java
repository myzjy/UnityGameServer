package com.gameServer.common.cache.weapon;

import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/6 22 56
 */
@Protocol(id = 6001)
public class CreateWeaponDefaultAnswer {

    private int weaponIndex;

    public int getWeaponIndex() {
        return weaponIndex;
    }

    public void setWeaponIndex(int weaponIndex) {
        this.weaponIndex = weaponIndex;
    }

}
