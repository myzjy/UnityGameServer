package com.gameServer.commonRefush.entity;

import com.zfoo.orm.model.entity.IEntity;

/**
 * 道具基础类
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/11/16 23:48
 */
public class ItemBoxBasEntity implements IEntity<Integer> {
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
    public Integer id() {
        return itemId;
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

    public String getDeatiertring() {
        return deatiertring;
    }

    public void setDeatiertring(String deatiertring) {
        this.deatiertring = deatiertring;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * 道具名字
     */
    private String name;
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

    /**
     * 创建时间
     */
    private String createAt;
    /**
     * 更新时间
     */
    private String updateAt;

    public static ItemBoxBasEntity ValueOf() {
        var entity = new ItemBoxBasEntity();
    
    }

}
