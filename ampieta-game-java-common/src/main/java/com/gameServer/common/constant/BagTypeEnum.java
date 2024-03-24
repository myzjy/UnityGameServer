package com.gameServer.common.constant;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/3/24 22 25
 */
public enum BagTypeEnum {
    None(0, "错误"),
    Weapon(1, "武器");
    public int type;
    public String msg;

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    BagTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static BagTypeEnum GetType(int type) {
        if (type == BagTypeEnum.Weapon.getType()) {
            return BagTypeEnum.Weapon;
        }
        return BagTypeEnum.None;
    }
}
