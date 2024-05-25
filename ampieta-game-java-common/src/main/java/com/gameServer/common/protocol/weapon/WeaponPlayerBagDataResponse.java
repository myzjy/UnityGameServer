package com.gameServer.common.protocol.weapon;

import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/3/19 23 16
 */
@Protocol(id = 1044)
public class WeaponPlayerBagDataResponse {
    @Note("玩家id")
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
