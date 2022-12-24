package com.gameServer.commonRefush.protocol.bag;

import com.gameServer.commonRefush.entity.BagUserItemEntity;
import com.zfoo.protocol.IPacket;

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

    public List<BagUserItemEntity> list;

    public static AllBagItemResponse ValueOf(List<BagUserItemEntity> list) {
        var value = new AllBagItemResponse();
        value.list = new ArrayList<>();
        value.list.addAll(list);
        return value;
    }

}
