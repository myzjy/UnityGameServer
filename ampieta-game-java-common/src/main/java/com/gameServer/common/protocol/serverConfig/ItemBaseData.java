package com.gameServer.common.protocol.serverConfig;

import com.gameServer.common.resource.ItemBaseCsvResource;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/12/31 0:55
 */
@Protocol(id = 201)
public class ItemBaseData implements IPacket {
    public static final transient short PROTOCOL_ID = 201;
    /**
     * 道具id
     */
    @Note("道具id")
    private int id;
    /**
     * 道具名字
     */
    @Note("道具名字")
    private String name;
    /**
     * 道具Icon
     */
    @Note("道具Icon")
    private String icon;
    /**
     * 最小数量
     */
    @Note("最小数量")
    private int minNum;
    @Note("最大数量")
    private int maxNum;
    @Note("item类型")
    private int type;
    @Note("介绍")
    private String des;

    public static ItemBaseData ValueOf(ItemBaseCsvResource csvResource) {
        var data = new ItemBaseData();
        data.des = csvResource.getDes();
        data.icon = csvResource.getIcon();
        data.id = csvResource.getId();
        data.minNum = csvResource.getMinNum();
        data.maxNum = csvResource.getMaxNum();
        data.name = csvResource.getName();
        data.type = csvResource.getType();
        return data;
    }

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
