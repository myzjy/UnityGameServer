package com.gameServer.common.protocol.physicalPower;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 体力恢复返回
 *
 * @author zjy
 * @version 1.0
 * @since 2023/6/27 19 12
 */
@Protocol(id = 1030)
public class PhysicalPowerSecondsResponse implements IPacket {
    /**
     * 数据体力
     */
    @Note("数据体力")
    private PhysicalPowerInfoData physicalPowerInfoData;

    /**
     * @param nowPhysicalPower      返回 体力
     * @param residueTime           一点体力增长剩余时间
     * @param residueNowTime        当前体力实时时间 会跟着剩余时间一起变化
     * @param maximumStrength       最大体力 用于限制 这个值会随着 等级增长
     * @param maximusResidueEndTime 我恢复到最大体力的结束时间
     * @return 体力恢复返回
     */
    public static PhysicalPowerSecondsResponse ValueOf(
            int nowPhysicalPower, int residueTime, long residueNowTime, int maximumStrength, int maximusResidueEndTime) {
        var data = new PhysicalPowerSecondsResponse();
        var packet = new PhysicalPowerInfoData();
        packet.setNowPhysicalPower(nowPhysicalPower);
        packet.setResidueTime(residueTime);
        packet.setResidueNowTime(residueNowTime);
        packet.setMaximumStrength(maximumStrength);
        packet.setMaximusResidueEndTime(maximusResidueEndTime);
        data.setPhysicalPowerInfoData(packet);
        return data;
    }

    public PhysicalPowerInfoData getPhysicalPowerInfoData() {
        return physicalPowerInfoData;
    }

    public void setPhysicalPowerInfoData(PhysicalPowerInfoData physicalPowerInfoData) {
        this.physicalPowerInfoData = physicalPowerInfoData;
    }
}
