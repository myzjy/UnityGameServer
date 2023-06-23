package com.gameServer.commonRefush.entity;

import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.entity.IEntity;

/**
 * 背包所属者 当前道具
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/5 23:54
 */
public class BagUserItemEntity implements IEntity<Integer> {
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
     * 当前道具是否
     */
    private boolean isNew;

    public static BagUserItemEntity ValueOf(int _id, int itemId, int masterUserId, int nowItemNum, boolean isNew) {
        BagUserItemEntity bagUserItem = new BagUserItemEntity();
        bagUserItem.set_id(_id);
        bagUserItem.setItemId(itemId);
        bagUserItem.setNowItemNum(nowItemNum);
        bagUserItem.setMasterUserId(masterUserId);
        bagUserItem.setNew(isNew);
        return bagUserItem;
    }

    /**
     * @return 返回当前道具所属玩家
     * @author zjy
     * @version 1.0
     */
    public long getMasterUserId() {
        return masterUserId;
    }

    /**
     * 设置当前背包道具所属玩家
     *
     * @param masterUserId 赋值玩家id
     * @author zjy
     * @version 1.0
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


    @Override
    public Integer id() {
        return _id;
    }

    @Override
    public long gvs() {
        return IEntity.super.gvs();
    }

    @Override
    public void svs(long vs) {
        IEntity.super.svs(vs);
    }

    @Override
    public boolean empty() {
        return IEntity.super.empty();
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

    /**
     * 返回道具是是最新获取，
     * 是否最新被点击过
     *
     * @return 返回布尔值
     * @author zjy
     * @version 1.0
     */
    public boolean isNew() {
        return isNew;
    }

    /**
     * 对道具进行设置
     *
     * @param aNew 是否最新
     * @author zjy
     * @version 1.0
     */
    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    @Override
    public String toString() {
        return "BagUserItemEntity{" +
                "_id=" + _id +
                ", masterUserId=" + masterUserId +
                ", nowItemNum=" + nowItemNum +
                ", itemId=" + itemId +
                ", isNew=" + isNew +
                '}';
    }
}
