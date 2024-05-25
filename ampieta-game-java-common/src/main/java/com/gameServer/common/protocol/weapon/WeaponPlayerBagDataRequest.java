package com.gameServer.common.protocol.weapon;

import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/3/19 23 05
 */
@Protocol(id = 1043)
public class WeaponPlayerBagDataRequest {
    /**
     * 服务器 id？
     */
    @Note("服务器 id？")
    private long serverId;
    /**
     * 玩家id
     */
    @Note("玩家id")
    private long uid;

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public static WeaponPlayerBagDataRequest valueOf(long serverId, long uid) {
        var data = new WeaponPlayerBagDataRequest();
        data.serverId = serverId;
        data.uid = uid;
        return data;
    }
}
