package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/20 23 49
 */
@Protocol(id = 206)
public class EquipmentData implements IPacket {
    /**
     * 当前圣遗物装备id
     */
    @Note("当前圣遗物装备id")
    private int equipmentId;
    /**
     * 主属性词条
     */
    @Note("主属性词条")
    private EquipmentGlossaryData SubjectClauseEquipmentData;
    /**
     * 副词条多个词条
     */
    @Note("副词条多个词条")
    private List<EquipmentGlossaryData> AdverbStripEquipmentDataList;
    /**
     * 等级
     */
    @Note("等级")
    private int equipmentLv;
    /**
     * 当前圣遗物最大等级
     */
    @Note("当前圣遗物最大等级")
    private int equipmentMaxLv;
    /**
     * 圣遗物升级的当前经验
     */
    @Note("圣遗物升级的当前经验")
    private int nowEquipmentExp;
    /**
     * 圣遗物升级的最大经验
     */
    @Note("圣遗物升级的最大经验")
    private int maxEquipmentExp;
    /**
     * list包含当前等级之前圣遗物转换经验值
     */
    @Note("list包含当前等级之前圣遗物转换经验值")
    private List<Integer> rankSwitchingExperienceList;
    /**
     * 当前装备所属角色id
     */
    @Note("当前装备所属角色id")
    private int useTheRole;

    public static EquipmentData ValueOf(EquipmentGlossaryData subjectClauseData,
                                        List<EquipmentGlossaryData> AdverbStripEquipmentDataList,
                                        int equipmentLv, int equipmentMaxLv,
                                        int nowEquipmentExp, int maxEquipmentExp,
                                        int useTheRole,int equipmentId) {
        var data = new EquipmentData();
        data.setSubjectClauseEquipmentData(subjectClauseData);
        data.setAdverbStripEquipmentDataList(AdverbStripEquipmentDataList);
        data.setEquipmentLv(equipmentLv);
        data.setEquipmentMaxLv(equipmentMaxLv);
        data.setNowEquipmentExp(nowEquipmentExp);
        data.setMaxEquipmentExp(maxEquipmentExp);
        data.setUseTheRole(useTheRole);
        data.setEquipmentId(equipmentId);
        return data;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public EquipmentGlossaryData getSubjectClauseEquipmentData() {
        return SubjectClauseEquipmentData;
    }

    public void setSubjectClauseEquipmentData(EquipmentGlossaryData subjectClauseEquipmentData) {
        SubjectClauseEquipmentData = subjectClauseEquipmentData;
    }

    public List<EquipmentGlossaryData> getAdverbStripEquipmentDataList() {
        return AdverbStripEquipmentDataList;
    }

    public void setAdverbStripEquipmentDataList(List<EquipmentGlossaryData> adverbStripEquipmentDataList) {
        AdverbStripEquipmentDataList = adverbStripEquipmentDataList;
    }

    public int getEquipmentLv() {
        return equipmentLv;
    }

    public void setEquipmentLv(int equipmentLv) {
        this.equipmentLv = equipmentLv;
    }

    public int getEquipmentMaxLv() {
        return equipmentMaxLv;
    }

    public void setEquipmentMaxLv(int equipmentMaxLv) {
        this.equipmentMaxLv = equipmentMaxLv;
    }

    public int getNowEquipmentExp() {
        return nowEquipmentExp;
    }

    public void setNowEquipmentExp(int nowEquipmentExp) {
        this.nowEquipmentExp = nowEquipmentExp;
    }

    public int getMaxEquipmentExp() {
        return maxEquipmentExp;
    }

    public void setMaxEquipmentExp(int maxEquipmentExp) {
        this.maxEquipmentExp = maxEquipmentExp;
    }

    public List<Integer> getRankSwitchingExperienceList() {
        return rankSwitchingExperienceList;
    }

    public void setRankSwitchingExperienceList(List<Integer> rankSwitchingExperienceList) {
        this.rankSwitchingExperienceList = rankSwitchingExperienceList;
    }

    public int getUseTheRole() {
        return useTheRole;
    }

    public void setUseTheRole(int useTheRole) {
        this.useTheRole = useTheRole;
    }
}
