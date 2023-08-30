package com.gameServer.commonRefush.protocol.cache.create;

import com.zfoo.net.router.attachment.AttachmentType;
import com.zfoo.net.router.attachment.IAttachment;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 创建体力数据库数据 回调
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/7 16 21
 */
//@Protocol(id = 1018)
public class CreatePhysicalPowerAnswer implements IPacket {
    public static final transient short PROTOCOL_ID = 1018;

    public static CreatePhysicalPowerAnswer ValueOf() {
        var data = new CreatePhysicalPowerAnswer();
        return data;
    }

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
    private String tips;

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
