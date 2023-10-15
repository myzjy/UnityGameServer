package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;
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
     * 当前 圣遗物 装备 id
     */
    private int equipmentId;
    /**
     * 主属性 词条
     */
    private EquipmentGlossaryData SubjectClauseEquipmentData;
    /**
     * 副 词条 多个词条
     */
    private List<EquipmentGlossaryData> AdverbStripEquipmentDataList;
    /**
     * 等级
     */
    private int equipmentLv;
    /**
     * 当前圣遗物最大等级
     */
    private int equipmentMaxLv;
    /**
     * 圣遗物 升级 的 当前经验
     */
    private int nowEquipmentExp;
    /**
     * 圣遗物 升级 的 最大经验
     */
    private int maxEquipmentExp;
    /**
     * list  包含 当前等级之前 圣遗物转换 经验值
     */
    private List<Integer> rankSwitchingExperienceList;
    /**
     * 当前装备所属 角色 id
     */
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
