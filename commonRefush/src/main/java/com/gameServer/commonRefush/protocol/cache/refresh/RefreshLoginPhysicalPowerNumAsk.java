package com.gameServer.commonRefush.protocol.cache.refresh;

import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 当我登录的时候，刷新体力
 * 如果体力值满了，就不需要调用这个rpc请求
 *
 * @author zjy
 * @version 1.0
 * @since 2023/3/28 23:34
 */
//@Protocol(id = 1015)
public class RefreshLoginPhysicalPowerNumAsk implements IPacket {
    public static final transient short PROTOCOL_ID = 1015;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    private long userId;

    public static RefreshLoginPhysicalPowerNumAsk ValueOf(long userId) {
        RefreshLoginPhysicalPowerNumAsk value = new RefreshLoginPhysicalPowerNumAsk();
        value.setUserId(userId);
        return value;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
