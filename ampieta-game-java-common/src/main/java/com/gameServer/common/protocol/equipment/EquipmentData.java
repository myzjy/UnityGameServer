package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/20 23 49
 */
public class EquipmentData implements IPacket {
    /**
     * 主属性 词条
     */
    private EquipmentGlossaryData SubjectClauseEquipmentData;
    /**
     * 副 词条 多个词条
     */
    private EquipmentGlossaryData[] AdverbStripEquipmentDatas;
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


}
