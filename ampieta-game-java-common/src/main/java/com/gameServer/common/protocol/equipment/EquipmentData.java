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
     * 主属性 词条
     */
    private EquipmentGlossaryData SubjectClauseEquipmentData;
    /**
     * 副 词条 多个词条
     */
    private EquipmentGlossaryData[] AdverbStripEquipmentDataList;
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
    public static EquipmentData ValueOf(){
        return new EquipmentData();
    }

    public EquipmentGlossaryData getSubjectClauseEquipmentData() {
        return SubjectClauseEquipmentData;
    }

    public void setSubjectClauseEquipmentData(EquipmentGlossaryData subjectClauseEquipmentData) {
        SubjectClauseEquipmentData = subjectClauseEquipmentData;
    }

    public EquipmentGlossaryData[] getAdverbStripEquipmentDataList() {
        return AdverbStripEquipmentDataList;
    }

    public void setAdverbStripEquipmentDataList(EquipmentGlossaryData[] adverbStripEquipmentDataList) {
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
}
