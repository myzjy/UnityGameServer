package com.gameServer.common.protocol.equipment.base;

import com.zfoo.protocol.anno.Protocol;

/**
 * 圣遗物位置名字
 * @author zjy
 * @version 1.0
 * @since 2023/11/14 17 09
 */
@Protocol(id = 211)
public class EquipmentGrowthConfigBaseData {
    private int id;
    /**
     * 圣遗物位置
     */
    private int locationOfEquipmentType;
    /**
     * 位置的名字
     */
    private String posName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationOfEquipmentType() {
        return locationOfEquipmentType;
    }

    public void setLocationOfEquipmentType(int locationOfEquipmentType) {
        this.locationOfEquipmentType = locationOfEquipmentType;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public static EquipmentGrowthConfigBaseData valueOf() {
        return new EquipmentGrowthConfigBaseData();
    }
}
