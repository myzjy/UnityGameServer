package com.gameServer.common.protocol.physicalPower;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/30 23 16
 */
@Protocol(id = 223)
public class PhysicalPowerInfoData implements IPacket {
    /**
     * 当前体力
     */
    @Note("当前体力")
    private int nowPhysicalPower;
    /**
     * 一点体力增长剩余时间
     * <p> 注意这里不是时间戳赋值</p>
     */
    @Note("一点体力增长剩余时间")
    private int residueTime;
    /**
     * 当前体力实时时间会跟着剩余时间一起变化
     */
    @Note("当前体力实时时间会跟着剩余时间一起变化")
    private long residueNowTime;
    /**
     * 最大体力用于限制这个值会随着等级增长
     */
    @Note("最大体力用于限制这个值会随着等级增长")
    private int maximumStrength;
    /**
     * 我恢复到最大体力的结束时间
     * <p>这里不是时间戳<p/>
     */
    @Note("我恢复到最大体力的结束时间")
    private int maximusResidueEndTime;

    public static PhysicalPowerInfoData ValueOf(int nowPhysicalPower, int residueTime, int maximumStrength, int maximusResidueEndTime, long residueNowTime) {
        var data = new PhysicalPowerInfoData();
        data.setNowPhysicalPower(nowPhysicalPower);
        data.setMaximumStrength(maximumStrength);
        data.setResidueTime(residueTime);
        data.setMaximusResidueEndTime(maximusResidueEndTime);
        data.setResidueNowTime(residueNowTime);
        return data;
    }

    public int getNowPhysicalPower() {
        return nowPhysicalPower;
    }

    public void setNowPhysicalPower(int nowPhysicalPower) {
        this.nowPhysicalPower = nowPhysicalPower;
    }

    public int getResidueTime() {
        return residueTime;
    }

    public void setResidueTime(int residueTime) {
        this.residueTime = residueTime;
    }

    public long getResidueNowTime() {
        return residueNowTime;
    }

    public void setResidueNowTime(long residueNowTime) {
        this.residueNowTime = residueNowTime;
    }

    public int getMaximumStrength() {
        return maximumStrength;
    }

    public void setMaximumStrength(int maximumStrength) {
        this.maximumStrength = maximumStrength;
    }

    public int getMaximusResidueEndTime() {
        return maximusResidueEndTime;
    }

    public void setMaximusResidueEndTime(int maximusResidueEndTime) {
        this.maximusResidueEndTime = maximusResidueEndTime;
    }
}
