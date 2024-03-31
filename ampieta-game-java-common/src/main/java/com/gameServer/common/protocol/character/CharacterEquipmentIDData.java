package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 21 35
 */
@Protocol(id = 218)
public class CharacterEquipmentIDData implements IPacket {
    /**
     * 装备 id
     */
    private int equipmentId;
    /**
     * 在 数据库中存放的id
     */
    private int equipmentFindId;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getEquipmentFindId() {
        return equipmentFindId;
    }

    public void setEquipmentFindId(int equipmentFindId) {
        this.equipmentFindId = equipmentFindId;
    }

    public static CharacterEquipmentIDData ValueOf() {
        return new CharacterEquipmentIDData();
    }

    /**
     * init now data
     *
     * @param equipmentId 装备 id
     * @param equipmentFindId 在 数据库中存放的id
     * @return 返回 数据
     */
    public static CharacterEquipmentIDData valueOf(int equipmentId, int equipmentFindId) {
        var data = new CharacterEquipmentIDData();
        data.setEquipmentFindId(equipmentFindId);
        data.setEquipmentId(equipmentId);
        return data;
    }
}
