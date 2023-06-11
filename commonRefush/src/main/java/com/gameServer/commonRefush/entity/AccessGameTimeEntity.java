package com.gameServer.commonRefush.entity;

import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.anno.Index;
import com.zfoo.orm.model.entity.IEntity;

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
    private Date time;

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

    public Date getTime() {
        return time;
    }

    /***
     * 设置服务器开始或者关闭时间 ，用于判断 或者显示
     */
    public void setTime(Date time) {
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
}
