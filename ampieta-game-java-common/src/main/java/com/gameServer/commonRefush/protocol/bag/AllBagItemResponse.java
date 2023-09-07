package com.gameServer.commonRefush.protocol.bag;

import com.zfoo.net.packet.IPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * 回调所有道具
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 22:27
 */
public class AllBagItemResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1008;

    private List<BagUserItemData> list;

    public static AllBagItemResponse ValueOf(List<BagUserItemData> list) {
        var value = new AllBagItemResponse();
        value.list = new ArrayList<>();
        value.list.addAll(list);
        return value;
    }

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public List<BagUserItemData> getList() {
        return list;
    }

    public void setList(List<BagUserItemData> list) {
        this.list = list;
    }

}
