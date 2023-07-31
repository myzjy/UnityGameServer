package com.gameServer.commonRefush.protocol.Puzzle;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;

/**
 * 地图所有总信息
 *
 * @author zjy
 * @version 1.0
 * @since 2023/7/31 23 45
 */
public class PuzzleAllConfigRequest implements IPacket, IGatewayLoadBalancer {
    /**
     * 事件ID 也可以说是 活动id
     * 传递为0 的时候 就是代表主线地图之类关卡相关
     */
    private int eventId;

    @Override
    public Object loadBalancerConsistentHashObject() {
        return null;
    }
    public static PuzzleAllConfigRequest ValueOf(int eventId){
        var packet=new PuzzleAllConfigRequest();
        packet.setEventId(eventId);
        return packet;
    }
    

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
