package com.gameServer.common.entity;

import com.gameServer.common.entity.composite.EquipmentGrowthData;
import com.zfoo.orm.model.IEntity;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/9 16 46
 */
public class EquipmentGrowthViceDataEntity implements IEntity<Long> {
    /**
     * 在数据库中存放
     */
    private long ormId;
    private int equipmentId;
    private long uid;
    /**
     * 名字
     */
    private String viceName;
    /**
     * 副属性的初始值数组
     */
    private List<EquipmentGrowthData> initNums;
    public static EquipmentGrowthViceDataEntity ValueOf(){
        var data=new EquipmentGrowthViceDataEntity();
        return data;
    }
    @Override
    public Long id() {
        return ormId;
    }

    public long getOrmId() {
        return ormId;
    }

    public void setOrmId(long ormId) {
        this.ormId = ormId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getViceName() {
        return viceName;
    }

    public void setViceName(String viceName) {
        this.viceName = viceName;
    }

    public List<EquipmentGrowthData> getInitNums() {
        return initNums;
    }

    public void setInitNums(List<EquipmentGrowthData> initNums) {
        this.initNums = initNums;
    }
}
