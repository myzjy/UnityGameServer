package com.gameServer.common.protocol.bag;

import com.gameServer.common.entity.BagUserItemEntity;
import com.zfoo.net.packet.IPacket;

import java.util.ArrayList;
import java.util.List;

//@Protocol(id = 1015)
public class UserBagItemResponse implements IPacket {
    private List<BagUserItemEntity> list = new ArrayList<>();

    public static UserBagItemResponse ValueOf() {
        var bagData = new UserBagItemResponse();
        bagData.list = new ArrayList<>();
        return bagData;
    }
    public static UserBagItemResponse ValueOf(List<BagUserItemEntity> list) {
        var bagData = new UserBagItemResponse();
        bagData.list = new ArrayList<>();
        bagData.list.addAll(list);
        return bagData;
    }
    public List<BagUserItemEntity> getList() {
        return list;
    }

    public void setList(List<BagUserItemEntity> list) {
        this.list = list;
    }
}
