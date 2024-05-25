package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 圣遗物的词条
 * @author zjy
 * @version 1.0
 * @since 2023/9/20 23 54
 */
@Protocol(id = 205)
public class EquipmentGlossaryData implements IPacket {
    /**
     * 圣遗物副属性词条type
     */
    @Note("圣遗物副属性词条type")
    private int equipmentType;
    /**
     * 数值
     */
    @Note("数值")
    private int equipmentNum;

    /**
     * 词条创建
     * @param equipmentType type
     * @param equipmentNum num
     * @return 词条创建
     */
    public static EquipmentGlossaryData ValueOf(int equipmentType,int equipmentNum){
        var data=new EquipmentGlossaryData();
        data.setEquipmentType(equipmentType);
        data.setEquipmentNum(equipmentNum);
        return data;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(int equipmentNum) {
        this.equipmentNum = equipmentNum;
    }
}
