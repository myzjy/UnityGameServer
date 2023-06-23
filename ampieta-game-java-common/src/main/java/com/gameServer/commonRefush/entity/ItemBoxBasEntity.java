package com.gameServer.commonRefush.entity;

import com.zfoo.orm.model.entity.IEntity;

/**
 * 道具基础类
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/11/16 23:48
 */
public class ItemBoxBasEntity implements IEntity<Long> {
    /**
     * 道具id
     */
    private int itemId;
    /**
     * 资源名字
     */
    private String resources;
    /**
     * 介绍
     */
    private String deatiertring;

    @Override
    public Long id() {
        return null;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
