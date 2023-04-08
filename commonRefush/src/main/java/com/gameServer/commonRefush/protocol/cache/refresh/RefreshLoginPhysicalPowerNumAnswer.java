package com.gameServer.commonRefush.protocol.cache.refresh;

import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.zfoo.net.packet.common.Error;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * rpc获取 体力计算 回调
 *
 * @author zjy
 * @version 1.0
 * @since 2023/3/28 17 25
 */
@Protocol(id = 1016)
public class RefreshLoginPhysicalPowerNumAnswer implements IPacket {
//    private PlayerUserEntity playerUserEntity;
    private Error error;

//    public static RefreshLoginPhysicalPowerNumAnswer ValueOf(PlayerUserEntity Entity) {
//        RefreshLoginPhysicalPowerNumAnswer data = new RefreshLoginPhysicalPowerNumAnswer();
//        data.setPlayerUserEntity(Entity);
//        return data;
//    }
    public static RefreshLoginPhysicalPowerNumAnswer ValueOf() {
        RefreshLoginPhysicalPowerNumAnswer data = new RefreshLoginPhysicalPowerNumAnswer();
//        data.setPlayerUserEntity(Entity);
        return data;
    }
    public static RefreshLoginPhysicalPowerNumAnswer ValueOf(Error error) {
        RefreshLoginPhysicalPowerNumAnswer data = new RefreshLoginPhysicalPowerNumAnswer();
        data.setError(error);
        return data;
    }
//
//    /**
//     * 返回出去体力计算完成缓存
//     */
//    public PlayerUserEntity getPlayerUserEntity() {
//        return playerUserEntity;
//    }
//
//    public void setPlayerUserEntity(PlayerUserEntity playerUserEntity) {
//        this.playerUserEntity = playerUserEntity;
//    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
