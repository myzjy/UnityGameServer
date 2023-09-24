package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * 背包中请求 圣遗物 的 所有数据 返回
 *
 * @author zjy
 * @version 1.0
 * @since 2023/9/24 23 53
 */
@Protocol(id = 1038)
public class EquipmentAllDataResponse implements IPacket {
    /**
     * 圣遗物 List
     */
    private List<EquipmentData> equipmentDataList;

    public static EquipmentAllDataResponse ValueOf(List<EquipmentData> equipmentDataList) {
        var data = new EquipmentAllDataResponse();
        data.setEquipmentDataList(equipmentDataList);
        return data;
    }

    public List<EquipmentData> getEquipmentDataList() {
        return equipmentDataList;
    }

    public void setEquipmentDataList(List<EquipmentData> equipmentDataList) {
        this.equipmentDataList = equipmentDataList;
    }
}
