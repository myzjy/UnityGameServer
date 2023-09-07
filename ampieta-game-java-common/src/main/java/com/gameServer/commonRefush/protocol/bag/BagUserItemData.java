package com.gameServer.commonRefush.protocol.bag;

import com.gameServer.commonRefush.entity.BagUserItemEntity;
import com.zfoo.net.packet.IPacket;
import com.zfoo.orm.anno.Id;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/12/31 0:47
 */
@Protocol(id = 200)
public class BagUserItemData implements IPacket {
    /**
     * 当前道具 在数据库中的唯一id
     *
     * @author zjy
     * @version 1.0
     */
    @Id
    private int _id;
    /**
     * 这个背包道具所属者是谁？
     *
     * @author zjy
     * @version 1.0
     * @
     */
    private long masterUserId;
    /**
     * 道具的数量
     *
     * @author zjy
     * @version 1.0
     */
    private int nowItemNum;
    /**
     * 道具id
     *
     * @author zjy
     * @version 1.0
     */
    private int itemId;


    /**
     * 道具 装备 品质
     *
     * @author zjy
     * @version 1.0
     */
    private int quality;
    /**
     * 资源路径
     */
    private String resourcePath;
    /**
     * Icon
     */
    private String icon;

    /**
     * 是否为新
     *
     * @author zjy
     * @version 1.0
     */
    private boolean itemNew;

    public static BagUserItemData ValueOf(int _id, int itemId, long masterUserId, int nowItemNum, boolean isNew) {
        BagUserItemData bagUserItem = new BagUserItemData();
        bagUserItem.set_id(_id);
        bagUserItem.setMasterUserId(masterUserId);
        bagUserItem.setItemId(itemId);
        bagUserItem.setNowItemNum(nowItemNum);
        bagUserItem.setItemNew(isNew);
        return bagUserItem;
    }

    public static BagUserItemData ValueOf(BagUserItemEntity _entity) {
        BagUserItemData bagUserItem = new BagUserItemData();
        bagUserItem.set_id(_entity.get_id());
        bagUserItem.setItemId(_entity.getItemId());
        bagUserItem.setMasterUserId(_entity.getMasterUserId());
        bagUserItem.setNowItemNum(_entity.getNowItemNum());
        bagUserItem.setItemNew(_entity.isNew());
        return bagUserItem;
    }

    public boolean isItemNew() {
        return itemNew;
    }

    public void setItemNew(boolean itemNew) {
        this.itemNew = itemNew;
    }

    /**
     * @return 返回当前道具所属玩家
     * @author zjy
     */
    public long getMasterUserId() {
        return masterUserId;
    }

    /**
     * 设置当前背包道具所属玩家
     *
     * @param masterUserId 赋值玩家id
     * @author zjy
     */
    public void setMasterUserId(long masterUserId) {
        this.masterUserId = masterUserId;
    }

    /**
     * @return 返回当前背包道具数量
     * @author zjy
     * @version 1.0
     */
    public int getNowItemNum() {
        return nowItemNum;
    }

    /**
     * 设置当前背包道具数量
     *
     * @param nowItemNum 数量
     * @author zjy
     * @version 1.0
     */
    public void setNowItemNum(int nowItemNum) {
        this.nowItemNum = nowItemNum;
    }

    /**
     * @return 返回当前背包道具 id
     * @author zjy
     * @version 1.0
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * 设置当前背包道具id
     *
     * @param itemId 数量
     * @author zjy
     * @version 1.0
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer id() {
        return _id;
    }

    /**
     * 目前来说返回类型先设定成int
     * 数据长度后续不够在换其他类型
     *
     * @return 返回当前背包道具 唯一id
     * @author zjy
     * @version 1.0
     */
    public int get_id() {
        return _id;
    }

    /**
     * 设置当前背包道具id
     *
     * @param _id 唯一id
     * @author zjy
     * @version 1.0
     */
    public void set_id(int _id) {
        this._id = _id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
}
