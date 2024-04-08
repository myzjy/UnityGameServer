package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/6 14 23
 */

@Storage
public class EquipmentPrimaryConfigResource {
    @Id
    private int id;
    /**
     * 品阶
     */
    private int primaryQuality;
    /**
     * 圣遗物属性位置
     */
    private int growthPosInt;
    /**
     * 属性名字
     */
    private String growthPosName;
    /**
     * 1级初始属性
     */
    private float primaryGrowthInts;
    /**
     * 品阶的最大等级的最大属性值
     */
    private float primaryGrowthMaxInt;
    /**
     * 属性位置所属名字
     */
    private String primaryGrowthName;

    public int getId() {
        return id;
    }

    public int getPrimaryQuality() {
        return primaryQuality;
    }

    public int getGrowthPosInt() {
        return growthPosInt;
    }

    public String getGrowthPosName() {
        return growthPosName;
    }

    public float getPrimaryGrowthInts() {
        return primaryGrowthInts;
    }

    public float getPrimaryGrowthMaxInt() {
        return primaryGrowthMaxInt;
    }

    public String getPrimaryGrowthName() {
        return primaryGrowthName;
    }
}
