package com.gameServer.common.entity;

import com.zfoo.orm.model.IEntity;
import com.zfoo.orm.anno.EntityCache;
import com.zfoo.orm.anno.Id;
import com.zfoo.orm.anno.Index;

import java.util.Date;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/2/17 23:59
 */
@EntityCache
public class AccessGameTimeEntity implements IEntity<Integer> {
    @Id
    private int id;


    @Index(ascending = true, unique = true)
    private int timeID;
    private long time;
    /**
     * 当前数据创建时间
     */
    private String createAt;
    /**
     * 当前数据更新时间
     */
    private String updateAt;

    @Override
    public Integer id() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    /***
     * 设置服务器开始或者关闭时间 ，用于判断 或者显示
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * 服务器关闭或者开启时间
     *
     * @author zjy
     * @since 2023/2/17 23:59
     */
    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public static AccessGameTimeEntity valueOf(){
        return new AccessGameTimeEntity();
    }
}
