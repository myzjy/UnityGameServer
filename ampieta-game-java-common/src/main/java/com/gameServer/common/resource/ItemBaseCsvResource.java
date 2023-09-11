package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * 道具表相关
 */
@Storage
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
    /**
     * 介绍 id 集合
     * <p>
     * 例如:
     * <blockquote><pre>
     *     101;102;
     * </pre></blockquote><p>
     * 以分号切割
     * <p/>
     */
    private String des;

    private int quality;
    /**
     *
     */
    private String resourcePath;

    public int getQuality() {
        return quality;
    }


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

    public String getResourcePath() {
        return resourcePath;
    }

}
