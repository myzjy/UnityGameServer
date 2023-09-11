package com.gameServer.common.protocol.bag;

import java.util.ArrayList;
import java.util.List;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

@Protocol(id=1034)
public class  UseTheBagItemEffectResponse implements IPacket {

    private List<BagUserItemData> bagUserItemDataList=new ArrayList<>();

    public static UseTheBagItemEffectResponse ValueOf(){
        var packet=new UseTheBagItemEffectResponse();
        packet.bagUserItemDataList=new ArrayList<>();
        return packet;
    }

    public List<BagUserItemData> getBagUserItemDataList() {
        return bagUserItemDataList;
    }

    public void setBagUserItemDataList(List<BagUserItemData> bagUserItemDataList) {
        this.bagUserItemDataList = bagUserItemDataList;
    }
}
