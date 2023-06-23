package com.gameServer.commonRefush.protocol.serverConfig;

import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.zfoo.protocol.IPacket;
import com.zfoo.storage.model.anno.Id;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/12/31 0:55
 */
public class ItemBaseData implements IPacket {
    public static final transient short PROTOCOL_ID = 201;
    /**
     * 道具id
     */
    @Id
    private int id;
    /**
     * 道具名字
     */
    private String name;
    private String icon;
    private int minNum;
    private int maxNum;
    private int type;
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
