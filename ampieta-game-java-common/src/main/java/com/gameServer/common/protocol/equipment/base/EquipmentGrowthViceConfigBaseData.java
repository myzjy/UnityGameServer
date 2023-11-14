package com.gameServer.common.protocol.equipment.base;

import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * 圣遗物副属性
 *
 * @author zjy
 * @version 1.0
 * @since 2023/11/14 17 04
 */
@Protocol(id = 210)
public class EquipmentGrowthViceConfigBaseData {
    /**
     * id
     */
    private int viceId;
    /**
     * 详细属性
     */
    private String viceName;
    /**
     * 属性所属pos对应
     */
    private int posGrowthType;
    /**
     * 副属性的初始值数组
     */
    private List<String> initNums;

    public static EquipmentGrowthViceConfigBaseData valueOf() {
        return new EquipmentGrowthViceConfigBaseData();
    }

    public int getViceId() {
        return viceId;
    }

    public void setViceId(int viceId) {
        this.viceId = viceId;
    }

    public String getViceName() {
        return viceName;
    }

    public void setViceName(String viceName) {
        this.viceName = viceName;
    }

    public int getPosGrowthType() {
        return posGrowthType;
    }

    public void setPosGrowthType(int posGrowthType) {
        this.posGrowthType = posGrowthType;
    }

    public List<String> getInitNums() {
        return initNums;
    }

    public void setInitNums(List<String> initNums) {
        this.initNums = initNums;
    }
}
