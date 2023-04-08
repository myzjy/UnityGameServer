package com.gameServer.commonRefush.protocol.cache.create;

import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 创建体力数据库数据  rpc 请求
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/7 16 44
 */
//@Protocol(id = 1017)
public class CreatePhysicalPowerAsk implements IPacket {
    public static final transient short PROTOCOL_ID = 1017;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    /**
     * 等级
     */
    private int lv;
    /**
     * 玩家uid
     */
    private long uid;

    /**
     * 创建体力
     */
    public static CreatePhysicalPowerAsk ValueOf(int lv, long uid) {
        CreatePhysicalPowerAsk data = new CreatePhysicalPowerAsk();
        data.setLv(lv);
        data.setUid(uid);
        return data;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
