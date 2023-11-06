package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/6 14 39
 */
public class EquipmentPrimaryConfigDataEntity implements IEntity<Integer> {
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
    private int primaryGrowthInts;
    /**
     * 品阶的最大等级的最大属性值
     */
    private int primaryGrowthMaxInt;
    /**
     * 属性位置所属名字
     */
    private String primaryGrowthName;
    /**
     * 当前数据创建时间
     */
    private String createAt;
    /**
     * 当前数据更新时间
     */
    private String updateAt;

    public static EquipmentPrimaryConfigDataEntity ValueOf() {
        return new EquipmentPrimaryConfigDataEntity();
    }

    @Override
    public Integer id() {
        return id;
    }

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

    public String getGrowthPosName() {
        return growthPosName;
    }

    public void setGrowthPosName(String growthPosName) {
        this.growthPosName = growthPosName;
    }

    public int getPrimaryGrowthInts() {
        return primaryGrowthInts;
    }

    public void setPrimaryGrowthInts(int primaryGrowthInts) {
        this.primaryGrowthInts = primaryGrowthInts;
    }

    public int getPrimaryGrowthMaxInt() {
        return primaryGrowthMaxInt;
    }

    public void setPrimaryGrowthMaxInt(int primaryGrowthMaxInt) {
        this.primaryGrowthMaxInt = primaryGrowthMaxInt;
    }

    public String getPrimaryGrowthName() {
        return primaryGrowthName;
    }

    public void setPrimaryGrowthName(String primaryGrowthName) {
        this.primaryGrowthName = primaryGrowthName;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
