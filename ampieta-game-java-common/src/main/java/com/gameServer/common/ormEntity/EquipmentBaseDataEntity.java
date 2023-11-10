package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.anno.Index;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/10 10 13
 */
public class EquipmentBaseDataEntity implements IEntity<Integer> {
    @Id
    @Index(ascending = false, unique = false)
    private int id;
    /**
     * 介绍id
     */
    private int desId;
    /**
     * 品阶
     */
    private int quality;
    /**
     * 装备只能装配在什么位置
     */
    private int equipmentPosType;
    /**
     * 圣遗物的名字
     */
    private String equipmentName;
    /**
     * 主属性集合可以获取那些属性
     */
    private String mainAttributes;
    /**
     * 当前数据创建时间
     */
    private String createAt;
    /**
     * 当前数据更新时间
     */
    private String updateAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDesId() {
        return desId;
    }

    public void setDesId(int desId) {
        this.desId = desId;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getEquipmentPosType() {
        return equipmentPosType;
    }

    public void setEquipmentPosType(int equipmentPosType) {
        this.equipmentPosType = equipmentPosType;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getMainAttributes() {
        return mainAttributes;
    }

    public void setMainAttributes(String mainAttributes) {
        this.mainAttributes = mainAttributes;
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

    public static EquipmentBaseDataEntity valueOf() {
        return new EquipmentBaseDataEntity();
    }

    @Override
    public Integer id() {
        return id;
    }
}
