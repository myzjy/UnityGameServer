package com.gameServer.commonRefush.resource;

import com.zfoo.storage.model.anno.Id;
import com.zfoo.storage.model.anno.Index;
import com.zfoo.storage.model.anno.Resource;


/**
 * 道具表相关
 */
@Resource
public class ItemBaseCsvResource {


    /**
     * 道具id
     */
    @Id
    @Index
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

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public int getMinNum() {
        return minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public int getType() {
        return type;
    }


    public String getDes() {
        return des;
    }

}
