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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
