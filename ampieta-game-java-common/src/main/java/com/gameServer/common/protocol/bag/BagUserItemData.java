package com.gameServer.common.protocol.bag;

import com.gameServer.common.entity.BagUserItemEntity;
import com.zfoo.net.packet.IPacket;
import com.zfoo.orm.anno.Id;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/12/31 0:47
 */
@Protocol(id = 200)
public class BagUserItemData implements IPacket {
    /**
     * 当前道具在数据库中的唯一id
     */
    @Id
    @Note("当前道具在数据库中的唯一id")
    private long _id;
    /**
     * 这个背包道具所属者是谁？
     */
    @Note("这个背包道具所属者是谁")
    private long masterUserId;
    /**
     * 这个道具如果是武器\圣遗物的话
     * 这个武器\圣遗物 被谁 穿戴
     */
    @Note("这个道具如果是武器/圣遗物的话")
    private int userPlayerId;
    /**
     * 道具的数量
     */
    @Note("道具的数量")
    private int nowItemNum;
    /**
     * 道具id
     */
    @Note("道具id")
    private int itemId;
    /**
     * 道具装备品质
     */
    @Note("道具装备品质")
    private int quality;
    /**
     * 资源路径
     */
    @Note("资源路径")
    private String resourcePath;
    /**
     * Icon
     */
    @Note("Icon")
    private String icon;
    /**
     * 是否为新
     */
    @Note("是否为新")
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

    public int getUserPlayerId() {
        return userPlayerId;
    }

    public void setUserPlayerId(int userPlayerId) {
        this.userPlayerId = userPlayerId;
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
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * 设置当前背包道具id
     *
     * @param itemId 数量
     * @author zjy
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long id() {
        return _id;
    }

    /**
     * 目前来说返回类型先设定成int
     * 数据长度后续不够在换其他类型
     *
     * @return 返回当前背包道具 唯一id
     * @author zjy
     */
    public long get_id() {
        return _id;
    }

    /**
     * 设置当前背包道具id
     *
     * @param _id 唯一id
     * @author zjy
     */
    public void set_id(long _id) {
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
