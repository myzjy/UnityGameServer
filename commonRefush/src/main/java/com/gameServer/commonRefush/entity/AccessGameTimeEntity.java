package com.gameServer.commonRefush.entity;

import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.anno.Index;
import com.zfoo.orm.model.entity.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/2/17 23:59
 */
@EntityCache
public class AccessGameTimeEntity implements IEntity<Integer> {
    /**
     * @version 1.0
     * @author zjy
     */
    @Id
    private int id;


    @Index(ascending = true, unique = true)
    private int timeID;
    /**
     * 服务器关闭或者开启时间
     *
     * @version 1.0
     * @author zjy
     */
    private String time;

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

    public String getTime() {
        return time;
    }

    /***
     * 设置服务器开始或者关闭时间 ，用于判断 或者显示
     */
    public void setTime(String time) {
        this.time = time;
    }

    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }
}
