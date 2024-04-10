package com.gameServer.common.cache.weapon;

import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/6 22 57
 */
@Protocol(id = 6000)
public class CreateWeaponDefaultAsk {
    /**
     * 需要创建武器的 id
     */
    private long playerId;
    /**
     * 武器 type
     */
    private int weaponType;
    /**
     * 是否 有 装备角色
     */
    private int userPlayerId;
    private long uid;

    public static CreateWeaponDefaultAsk valueOf() {
        return new CreateWeaponDefaultAsk();
    }

    public static CreateWeaponDefaultAsk valueOf(int playerId, int weaponType, int userPlayerId, long uid) {
        var data = new CreateWeaponDefaultAsk();
        data.setPlayerId(playerId);
        data.setWeaponType(weaponType);
        data.setUserPlayerId(userPlayerId);
        data.setUid(uid);
        return data;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    public int getUserPlayerId() {
        return userPlayerId;
    }

    public void setUserPlayerId(int userPlayerId) {
        this.userPlayerId = userPlayerId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
