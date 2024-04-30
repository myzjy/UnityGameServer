package com.gameServer.common.protocol.physicalPower;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/13 00 16
 */
@Protocol(id = 1024)
public class PhysicalPowerResponse implements IPacket {
    /**
     * 数据 体力
     */
    private PhysicalPowerInfoData physicalPowerInfoData;

    public static PhysicalPowerResponse ValueOf(int nowPhysicalPower, int residueTime, int maximumStrength, int maximusResidueEndTime, long residueNowTime) {
        var data = new PhysicalPowerResponse();
        var physicalPowerInfo = PhysicalPowerInfoData.ValueOf(nowPhysicalPower, residueTime, maximumStrength, maximumStrength, residueNowTime);
        data.setPhysicalPowerInfoData(physicalPowerInfo);
        return data;
    }

    public PhysicalPowerInfoData getPhysicalPowerInfoData() {
        return physicalPowerInfoData;
    }

    public void setPhysicalPowerInfoData(PhysicalPowerInfoData physicalPowerInfoData) {
        this.physicalPowerInfoData = physicalPowerInfoData;
    }
}
