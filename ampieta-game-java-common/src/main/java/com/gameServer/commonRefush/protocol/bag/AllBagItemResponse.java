package com.gameServer.commonRefush.protocol.bag;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 回调所有道具
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 22:27
 */
@Protocol(id = 1008)
public class AllBagItemResponse implements IPacket {

    private List<BagUserItemData> list;

    public static AllBagItemResponse ValueOf(List<BagUserItemData> list) {
        var value = new AllBagItemResponse();
        value.list = new ArrayList<>();
        value.list.addAll(list);
        return value;
    }

    public List<BagUserItemData> getList() {
        return list;
    }

    public void setList(List<BagUserItemData> list) {
        this.list = list;
    }

}
