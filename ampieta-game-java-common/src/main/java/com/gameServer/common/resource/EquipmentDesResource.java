package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/10 14 23
 */
@Storage
public class EquipmentDesResource {
    /**
     * 介绍id
     */
    @Id
    private int desId;
    /**
     * 这个介绍 圣遗物的名字
     */
    private String name;
    /**
     * 介绍
     */
    private String desStr;
    /**
     * 故事
     */
    private String storyDesStr;

    public int getDesId() {
        return desId;
    }

    public String getName() {
        return name;
    }

    public String getDesStr() {
        return desStr;
    }

    public String getStoryDesStr() {
        return storyDesStr;
    }
}
