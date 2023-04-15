package com.gameServer.commonRefush.protocol.physicalPower;

import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 使用体力相关道具 或者 使用
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/14 12 00
 */
@Protocol(id = 1025)
public class PhysicalPowerUsePropsRequest {

    /**
     * 使用体力 会被扣除
     */
    private int usePropNum;

    public static PhysicalPowerUsePropsRequest ValueOf() {
        var data = new PhysicalPowerUsePropsRequest();
        return data;
    }

    public int getUsePropNum() {
        return usePropNum;
    }

    public void setUsePropNum(int usePropNum) {
        this.usePropNum = usePropNum;
    }


}
