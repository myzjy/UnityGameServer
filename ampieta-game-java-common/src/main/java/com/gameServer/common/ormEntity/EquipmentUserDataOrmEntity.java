package com.gameServer.common.ormEntity;

import com.gameServer.common.entity.composite.EquipmentGrowthData;
import com.zfoo.orm.model.IEntity;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/26 17 22
 */
public class EquipmentUserDataOrmEntity implements IEntity<Long> {
    /**
     * 所属玩家 UID
     */
    private long Uid;
    /**
     * id
     */
    private long id;
    /**
     * 圣遗物所属 id
     */
    private int equipmentID;
    /**
     * 圣遗物所属 套装id
     */
    private int equipmentSuitID;
    /**
     * 当前等级
     */
    private int lv;
    /**
     * 当前经验
     */
    private int nowExp;
    /**
     * 当前 圣遗物所属的 角色 id
     */
    private int nowPlayerUserId;
    /**
     * 名字
     */
    private String viceName;
    /**
     * 当前圣遗物 主属性
     */
    private EquipmentGrowthData thisPrimaryAttributes;
    /**
     * 副属性的 具体数值 数组
     */
    private List<EquipmentGrowthData> initNums;

    @Override
    public Long id() {
        return id;
    }

    public long getUid() {
        return Uid;
    }

    public void setUid(long uid) {
        Uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }

    public int getEquipmentSuitID() {
        return equipmentSuitID;
    }

    public void setEquipmentSuitID(int equipmentSuitID) {
        this.equipmentSuitID = equipmentSuitID;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getNowExp() {
        return nowExp;
    }

    public void setNowExp(int nowExp) {
        this.nowExp = nowExp;
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

    public int getNowPlayerUserId() {
        return nowPlayerUserId;
    }

    public void setNowPlayerUserId(int nowPlayerUserId) {
        this.nowPlayerUserId = nowPlayerUserId;
    }

    public EquipmentGrowthData getThisPrimaryAttributes() {
        return thisPrimaryAttributes;
    }

    public void setThisPrimaryAttributes(EquipmentGrowthData thisPrimaryAttributes) {
        this.thisPrimaryAttributes = thisPrimaryAttributes;
    }

    /**
     * 创建 圣遗物 数据库格式
     *
     * @param id                      id
     * @param uid                     这个圣遗物所属 玩家id
     * @param equipmentID
     * @param equipmentSuitID
     * @param nowExp
     * @param lv
     * @param viceName
     * @param equipmentGrowthDataList 副属性
     * @param nowPlayerUserId         当前圣遗物所属 角色 id
     * @param thisPrimaryAttributes 主属性
     * @return
     */
    public static EquipmentUserDataOrmEntity ValueOf(long id, long uid, int equipmentID, int equipmentSuitID,
                                                     int nowExp, int lv, String viceName,
                                                     List<EquipmentGrowthData> equipmentGrowthDataList,
                                                     int nowPlayerUserId,EquipmentGrowthData thisPrimaryAttributes) {
        var data = new EquipmentUserDataOrmEntity();
        data.setId(id);
        data.setUid(uid);
        data.setEquipmentID(equipmentID);
        data.setEquipmentSuitID(equipmentSuitID);
        data.setLv(lv);
        data.setNowExp(nowExp);
        data.setViceName(viceName);
        data.setInitNums(equipmentGrowthDataList);
        data.setNowPlayerUserId(nowPlayerUserId);
        data.setThisPrimaryAttributes(thisPrimaryAttributes);
        return data;
    }
}
