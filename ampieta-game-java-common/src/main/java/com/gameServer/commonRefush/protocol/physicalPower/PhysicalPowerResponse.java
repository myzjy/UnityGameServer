package com.gameServer.commonRefush.protocol.physicalPower;

import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/13 00 16
 */
@Protocol(id = 1024)
public class PhysicalPowerResponse implements IPacket {
    /**
     * 当前体力
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

    public static PhysicalPowerResponse ValueOf(int nowPhysicalPower, int residueTime, int maximumStrength, int maximusResidueEndTime, long residueNowTime) {
        var data = new PhysicalPowerResponse();
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

    /**
     * 设置当前体力
     *
     * @param nowPhysicalPower 当前体力
     */
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
