package com.gameServer.common.cache.character;

/**
 * 登录的时候 没有角色 给一个默认的角色 RPC
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/1 23 03
 */
public class LoginCreateCharacterAsk {
    /**
     * 需要创建角色的 id
     */
    private long playerId;

    public static LoginCreateCharacterAsk valueOf() {
        return new LoginCreateCharacterAsk();
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}
