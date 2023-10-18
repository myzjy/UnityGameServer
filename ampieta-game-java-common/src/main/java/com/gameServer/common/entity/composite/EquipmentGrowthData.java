package com.gameServer.common.entity.composite;

/**
 * 属性值 圣遗物
 * @author zjy
 * @version 1.0
 * @since 2023/10/10 00 11
 */
public class EquipmentGrowthData {
    /**
     * POS type 具体 属性
     */
    private int posType;
    /**
     * 具体数据
     */
    private int growthViceNum;
    public static EquipmentGrowthData ValueOf(int posType,int growthViceNum){
        var data=new EquipmentGrowthData();
        data.setPosType(posType);
        data.setGrowthViceNum(growthViceNum);
        return data;
    }

    public int getPosType() {
        return posType;
    }

    public void setPosType(int posType) {
        this.posType = posType;
    }

    public int getGrowthViceNum() {
        return growthViceNum;
    }

    public void setGrowthViceNum(int growthViceNum) {
        this.growthViceNum = growthViceNum;
    }
}
