package com.gameServer.common.protocol.bag;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 获取背包的所有物品
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 19:55
 */
@Protocol(id = 1007)
public class AllBagItemRequest implements IPacket {
    /**
     * 调用协议 之后 在我点击
     */
    @Note("调用协议 之后 在我点击")
    private int type;
    /**
     * 背包的协议号 字符 在 字段 type 一样的情况下
     * 当前字段  额外 协议字符 不一样 返回不一样
     */
    @Note("背包的协议号")
    private String msgProtocol;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsgProtocol() {
        return msgProtocol;
    }

    public void setMsgProtocol(String msgProtocol) {
        this.msgProtocol = msgProtocol;
    }

    public static AllBagItemRequest ValueOf() {
        return new AllBagItemRequest();
    }
}
