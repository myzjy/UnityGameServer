package com.gameServer.commonRefush.protocol.user;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 在GameMain 界面请求User 数据 Exp Lv 金币 钻石 付费钻石 角色数据
 *
 * @author zjy
 * @version 1.0
 * @since 2023/7/14 15 42
 */
@Protocol(id = 1031)
public class GameMainUserInfoToRequest implements IPacket, IGatewayLoadBalancer {
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    private long uid;

    @Override
    public Object loadBalancerConsistentHashObject() {
        return uid;
    }

    public static GameMainUserInfoToRequest ValueOf(long uid) {
        var packet = new GameMainUserInfoToRequest();
        packet.setUid(uid);
        return packet;
    }
}
