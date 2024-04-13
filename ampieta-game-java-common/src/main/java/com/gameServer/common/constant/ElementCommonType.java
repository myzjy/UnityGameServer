package com.gameServer.common.constant;

/**
 * 通用的 元素 伤害 type
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/13 23 20
 */
public enum ElementCommonType {
    WindElemental(1, "风元素"),
    FireElemental(2, "火元素"),
    WaterElement(3, "水元素"),
    ThunderElemental(4, "雷元素"),
    ;
    private int type;
    private String message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ElementCommonType(int type, String message) {
        setType(type);
        setMessage(message);
    }
}
