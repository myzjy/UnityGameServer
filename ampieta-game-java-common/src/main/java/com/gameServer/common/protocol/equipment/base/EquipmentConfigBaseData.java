package com.gameServer.common.protocol.equipment.base;

import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/14 12 43
 */
@Protocol(id = 207)
public class EquipmentConfigBaseData {
    /**
     * quality
     */
    private int quality;
    /**
     * 强化到这个等级 强化获取额外属性条或者升级附属性条
     */
    private int lv1;
    /**
     * 强化到这个等级 强化获取额外属性条或者升级附属性条
     */
    private int lv2;
    /**
     * 强化到这个等级 强化获取额外属性条或者升级附属性条
     */
    private int lv3;
    /**
     * 强化到这个等级 强化获取额外属性条或者升级附属性条
     */
    private int lv4;

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getLv1() {
        return lv1;
    }

    public void setLv1(int lv1) {
        this.lv1 = lv1;
    }

    public int getLv2() {
        return lv2;
    }

    public void setLv2(int lv2) {
        this.lv2 = lv2;
    }

    public int getLv3() {
        return lv3;
    }

    public void setLv3(int lv3) {
        this.lv3 = lv3;
    }

    public int getLv4() {
        return lv4;
    }

    public void setLv4(int lv4) {
        this.lv4 = lv4;
    }

    public static EquipmentConfigBaseData valueOf() {
        return new EquipmentConfigBaseData();
    }
}
