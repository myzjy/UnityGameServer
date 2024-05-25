package com.gameServer.common.protocol.equipment.base;

import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 圣遗物位置信息
 *
 * @author zjy
 * @version 1.0
 * @since 2023/11/14 14 41
 */
@Protocol(id = 208)
public class EquipmentPrimaryConfigBaseData {
    /**
     * id
     */
    @Note("id")
    private int id;
    /**
     * 圣遗物品阶
     */
    @Note("圣遗物品阶")
    private int primaryQuality;
    /**
     * 圣遗物属性位置
     */
    @Note("圣遗物属性位置")
    private int growthPosInt;
    /**
     * 属性位置所属名字
     */
    @Note("属性位置所属名字")
    private String primaryGrowthName;
    /**
     * 1级初始属性
     */
    @Note("1级初始属性")
    private String primaryGrowthInts;
    /**
     * 品阶的最大等级的最大属性值
     */
    @Note("品阶的最大等级的最大属性值")
    private String primaryGrowthMaxInt;
    /**
     * 属性名字
     */
    @Note("属性名字")
    private String growthPosName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrimaryQuality() {
        return primaryQuality;
    }

    public void setPrimaryQuality(int primaryQuality) {
        this.primaryQuality = primaryQuality;
    }

    public int getGrowthPosInt() {
        return growthPosInt;
    }

    public void setGrowthPosInt(int growthPosInt) {
        this.growthPosInt = growthPosInt;
    }

    public String getPrimaryGrowthName() {
        return primaryGrowthName;
    }

    public void setPrimaryGrowthName(String primaryGrowthName) {
        this.primaryGrowthName = primaryGrowthName;
    }

    public String getPrimaryGrowthInts() {
        return primaryGrowthInts;
    }

    public void setPrimaryGrowthInts(String primaryGrowthInts) {
        this.primaryGrowthInts = primaryGrowthInts;
    }

    public String getPrimaryGrowthMaxInt() {
        return primaryGrowthMaxInt;
    }

    public void setPrimaryGrowthMaxInt(String primaryGrowthMaxInt) {
        this.primaryGrowthMaxInt = primaryGrowthMaxInt;
    }

    public String getGrowthPosName() {
        return growthPosName;
    }

    public void setGrowthPosName(String growthPosName) {
        this.growthPosName = growthPosName;
    }

    public static EquipmentPrimaryConfigBaseData valueOf() {
        return new EquipmentPrimaryConfigBaseData();
    }

}
