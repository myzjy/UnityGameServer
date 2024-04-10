package com.gameServer.common.cache.character;

import com.zfoo.protocol.anno.Protocol;

/**
 * 登录的时候 没有角色 给一个默认的角色 RPC
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/1 23 03
 */
@Protocol(id = 6002)
public class LoginCreateCharacterAsk {
    /**
     * 需要创建角色的 id
     */
    private int playerId;
    /**
     * 武器 orm index
     */
    private int weaponIndex;

    public static LoginCreateCharacterAsk valueOf() {
        return new LoginCreateCharacterAsk();
    }

    public static LoginCreateCharacterAsk valueOf(int playerId, int weaponIndex) {
        var data = new LoginCreateCharacterAsk();
        data.setPlayerId(playerId);
        data.setWeaponIndex(weaponIndex);
        return data;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getWeaponIndex() {
        return weaponIndex;
    }

    public void setWeaponIndex(int weaponIndex) {
        this.weaponIndex = weaponIndex;
    }
}
