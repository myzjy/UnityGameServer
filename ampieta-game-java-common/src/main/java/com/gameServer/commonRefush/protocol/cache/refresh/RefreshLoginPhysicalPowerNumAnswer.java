package com.gameServer.commonRefush.protocol.cache.refresh;

import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.AttachmentType;
import com.zfoo.net.router.attachment.IAttachment;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * rpc获取 体力计算 回调
 *
 * @author zjy
 * @version 1.0
 * @since 2023/3/28 17 25
 */
//@Protocol(id = 1016)
public class RefreshLoginPhysicalPowerNumAnswer implements IPacket {
    public static final transient short PROTOCOL_ID = 1016;

    public static RefreshLoginPhysicalPowerNumAnswer ValueOf() {
        RefreshLoginPhysicalPowerNumAnswer data = new RefreshLoginPhysicalPowerNumAnswer();
//        data.setPlayerUserEntity(Entity);
        return data;
    }

    private String tips;
    private Error error;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public static RefreshLoginPhysicalPowerNumAnswer ValueOf(Error error) {
        RefreshLoginPhysicalPowerNumAnswer data = new RefreshLoginPhysicalPowerNumAnswer();
        data.setError(error);
        return data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
