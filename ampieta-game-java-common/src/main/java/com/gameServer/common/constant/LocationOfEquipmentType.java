package com.gameServer.common.constant;

/**
 * 圣遗物 的 位置 名字
 *
 * @author zjy
 * @version 1.0
 * @since 2023/10/27 16 19
 */
public enum LocationOfEquipmentType {
    ;
    /**
     * 圣遗物所属位置 是什么 类型的
     */
    int sacredRelicsPos;
    String sacredRelicsName;

    LocationOfEquipmentType(int type, String sacredRelicsName) {
        setSacredRelicsPos(type);
        setSacredRelicsName(sacredRelicsName);
    }

    public int getSacredRelicsPos() {
        return sacredRelicsPos;
    }

    public void setSacredRelicsPos(int sacredRelicsPos) {
        this.sacredRelicsPos = sacredRelicsPos;
    }

    public String getSacredRelicsName() {
        return sacredRelicsName;
    }

    public void setSacredRelicsName(String sacredRelicsName) {
        this.sacredRelicsName = sacredRelicsName;
    }
}
