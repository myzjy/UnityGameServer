package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/3 00 56
 */
@Storage
public class EquipmentResource {
    /**
     * 圣遗物id
     */
    @Id
    private int id;
    /**
     * 圣遗物介绍id
     */
    private int desId;
    /**
     * 品阶
     */
    private int quality;
    /**
     * 装备只能装配在什么位置
     */
    private int equipmentPosType;
    /**
     * 圣遗物的名字
     */
    private String equipmentName;
    /**
     * 主属性集合可以获取那些属性
     */
    private String mainAttributes;

    public int getId() {
        return id;
    }

    public int getDesId() {
        return desId;
    }

    public int getQuality() {
        return quality;
    }

    public int getEquipmentPosType() {
        return equipmentPosType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getMainAttributes() {
        return mainAttributes;
    }
}
