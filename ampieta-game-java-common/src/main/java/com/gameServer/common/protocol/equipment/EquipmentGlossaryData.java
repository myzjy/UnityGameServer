package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;

/**
 * 圣遗物的词条
 * @author zjy
 * @version 1.0
 * @since 2023/9/20 23 54
 */
public class EquipmentGlossaryData implements IPacket {
    /**
     * 圣遗物 副属性 词条 type
     */
    private int equipmentType;
    /**
     * 数值
     */
    private int equipmentNum;
}
