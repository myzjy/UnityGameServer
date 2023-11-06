package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.anno.Index;
import com.zfoo.orm.model.IEntity;

/**
 * 圣遗物 位置 放入 数据库
 *
 * @author zjy
 * @version 1.0
 * @since 2023/11/3 17 27
 */
public class EquipmentGrowthConfigEntity implements IEntity<Integer> {
    @Id
    @Index(ascending = false, unique = false)
    private int id;
    /**
     * 圣遗物位置
     */
    private int locationOfEquipmentType;
    /**
     * 位置的名字
     */
    private String posName;
    /**
     * 当前数据创建时间
     */
    private String createAt;
    /**
     * 当前数据更新时间
     */
    private String updateAt;
    public static EquipmentGrowthConfigEntity ValueOf(){
        return new EquipmentGrowthConfigEntity();
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

    public int getLocationOfEquipmentType() {
        return locationOfEquipmentType;
    }

    public void setLocationOfEquipmentType(int locationOfEquipmentType) {
        this.locationOfEquipmentType = locationOfEquipmentType;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
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
