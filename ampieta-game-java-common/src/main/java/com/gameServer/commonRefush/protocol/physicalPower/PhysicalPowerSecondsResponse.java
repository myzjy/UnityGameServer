package com.gameServer.commonRefush.protocol.physicalPower;

import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

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
     * 返回 体力
     */
    private int nowPhysicalPower;
    /**
     * 一点体力增长剩余时间
     * <p> 注意这里不是时间戳赋值</p>
     */
    private int residueTime;
    /**
     * 当前体力实时时间 会跟着剩余时间一起变化
     */
    private long residueNowTime;
    /**
     * 最大体力 用于限制 这个值会随着 等级增长
     */
    private int maximumStrength;
    /**
     * 我恢复到最大体力的结束时间
     * <p>这里不是时间戳<p/>
     */
    private int maximusResidueEndTime;

    public int getNowPhysicalPower() {
        return nowPhysicalPower;
    }

    /**
     * @param nowPhysicalPower      返回 体力
     * @param residueTime           一点体力增长剩余时间
     * @param residueNowTime        当前体力实时时间 会跟着剩余时间一起变化
     * @param maximumStrength       最大体力 用于限制 这个值会随着 等级增长
     * @param maximusResidueEndTime 我恢复到最大体力的结束时间
     * @return 体力恢复返回
     */
    public static PhysicalPowerSecondsResponse ValueOf(int nowPhysicalPower, int residueTime, long residueNowTime, int maximumStrength, int maximusResidueEndTime) {
        var data = new PhysicalPowerSecondsResponse();
        data.setNowPhysicalPower(nowPhysicalPower);
        data.setResidueTime(residueTime);
        data.setResidueNowTime(residueNowTime);
        data.setMaximumStrength(maximumStrength);
        data.setMaximusResidueEndTime(maximusResidueEndTime);
        return data;
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
