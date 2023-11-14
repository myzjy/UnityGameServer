package com.gameServer.common.protocol.equipment.base;

import com.zfoo.protocol.anno.Protocol;

/**
 * 圣遗物介绍
 * @author zjy
 * @version 1.0
 * @since 2023/11/14 17 17
 */
@Protocol(id = 212)
public class EquipmentDesBaseData {
    /**
     * 介绍id
     */
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

    public void setDesId(int desId) {
        this.desId = desId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesStr() {
        return desStr;
    }

    public void setDesStr(String desStr) {
        this.desStr = desStr;
    }

    public String getStoryDesStr() {
        return storyDesStr;
    }

    public void setStoryDesStr(String storyDesStr) {
        this.storyDesStr = storyDesStr;
    }

    public static EquipmentDesBaseData valueOf() {
        return new EquipmentDesBaseData();
    }
}
