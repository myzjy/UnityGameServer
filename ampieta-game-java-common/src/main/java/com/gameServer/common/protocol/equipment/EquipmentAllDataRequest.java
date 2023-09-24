package com.gameServer.common.protocol.equipment;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 背包中请求 圣遗物 的 所有数据 请求
 *
 * @author zjy
 * @version 1.0
 * @since 2023/9/24 23 52
 */
@Protocol(id = 1037)
public class EquipmentAllDataRequest implements IPacket {
    /**
     * 请求的背包Type
     */
    private int bagType;

    public int getBagType() {
        return bagType;
    }

    public void setBagType(int bagType) {
        this.bagType = bagType;
    }
}
