package com.gameServer.common.protocol.equipment.base;

import com.zfoo.protocol.anno.Protocol;

/**
 * 圣遗物 名字 品质 这个装备的属性 可获取属性
 * @author zjy
 * @version 1.0
 * @since 2023/11/14 16 33
 */
@Protocol(id = 209)
public class EquipmentBaseData {
    /**
     * 介绍id
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
    public int getDesId() {
        return desId;
    }

    public void setDesId(int desId) {
        this.desId = desId;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getEquipmentPosType() {
        return equipmentPosType;
    }

    public void setEquipmentPosType(int equipmentPosType) {
        this.equipmentPosType = equipmentPosType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getMainAttributes() {
        return mainAttributes;
    }

    public void setMainAttributes(String mainAttributes) {
        this.mainAttributes = mainAttributes;
    }
    public static EquipmentBaseData valueOf(){
        return new EquipmentBaseData();
    }
}
