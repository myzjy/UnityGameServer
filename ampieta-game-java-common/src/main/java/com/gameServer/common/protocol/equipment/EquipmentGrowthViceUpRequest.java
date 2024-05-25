package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * 当前 进行 强化 request
 * @author zjy
 * @version 1.0
 * @since 2023/10/8 15 27
 */
@Protocol(id = 1039)
public class EquipmentGrowthViceUpRequest implements IPacket {
    /**
     * 当前强化的装备id
     */
    @Note("当前强化的装备id")
    private int userEquipmentID;
    /**
     * 当前强化装备的材料喂食装备使用exp
     */
    @Note("当前强化装备的材料喂食装备使用exp")
    private List<Integer> materialEquipmentList;

    public static EquipmentGrowthViceUpRequest ValueOf(int userEquipmentID, List<Integer> materialEquipmentList) {
        var data = new EquipmentGrowthViceUpRequest();
        return data;
    }

    public int getUserEquipmentID() {
        return userEquipmentID;
    }

    public void setUserEquipmentID(int userEquipmentID) {
        this.userEquipmentID = userEquipmentID;
    }

    public List<Integer> getMaterialEquipmentList() {
        return materialEquipmentList;
    }

    public void setMaterialEquipmentList(List<Integer> materialEquipmentList) {
        this.materialEquipmentList = materialEquipmentList;
    }
}
