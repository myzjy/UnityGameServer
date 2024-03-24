package com.gameServer.common.constant;

/**
 * 背包 装备 Enum
 * @author zjy
 * @version 1.0
 * @since 2023/7/30 00 14
 */
public enum BagItemType {
    Weapons(1,"武器"),
    Jewelry(2,"首饰"),
    Clothes(3,"上衣"),
    Shoes(4,"鞋子"),
    Hat(5,"帽子")
    ;

    /**
     * 枚举的Code
     */
    private int code;
    /**
     * 枚举的 信息
     */
    private String codeMessage;
    public int getCode(){
        return code;
    }
    public String getCodeMessage(){
        return codeMessage;
    }
    BagItemType(int code, String codeMessage) {
        this.code = code;
        this.codeMessage = codeMessage;
    }
    
}
