package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 记录 武器id 数据库中 整体的武器id
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 22 55
 */
@Protocol(id = 217)
public class CharacterWeaponIDData implements IPacket {
    /**
     * 武器id
     */
    private int weaponId;
    /**
     * 武器id --> 对应 WeaponUsePlayerDataEntity 中的 id
     */
    private int weaponFindId;

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getWeaponFindId() {
        return weaponFindId;
    }

    public void setWeaponFindId(int weaponFindId) {
        this.weaponFindId = weaponFindId;
    }

    public static CharacterWeaponIDData valueOf() {
        return new CharacterWeaponIDData();
    }
}
