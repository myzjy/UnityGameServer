package com.gameServer.commonRefush.entity;

import com.zfoo.net.session.Session;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.entity.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/12/21 23:36
 */
public class RoomDataEntity implements IEntity<Integer> {
    /**
     * 当前房间所属者
     */
    public transient Session session = null;
    @Id
    private int id;

    @Override
    public Integer id() {
        return id;
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
}
