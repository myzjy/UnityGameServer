package com.gameServer.common.protocol.login;

import com.gameServer.common.protocol.playerUser.PlayerSceneInfoData;
import com.gameServer.common.protocol.playerUser.UserMsgInfoData;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/17 19 49
 */
@Protocol(id=220)
public class LoginUserServerInfoData {
    /**
     * 角色在场景中得位置信息
     */
    private PlayerSceneInfoData playerSceneInfoData;
    /**
     * 玩家得一些信息 金币 砖石 付费 砖石
     */
    private UserMsgInfoData userMsgInfoData;

    public PlayerSceneInfoData getPlayerSceneInfoData() {
        return playerSceneInfoData;
    }

    public void setPlayerSceneInfoData(PlayerSceneInfoData playerSceneInfoData) {
        this.playerSceneInfoData = playerSceneInfoData;
    }

    public UserMsgInfoData getUserMsgInfoData() {
        return userMsgInfoData;
    }

    public void setUserMsgInfoData(UserMsgInfoData userMsgInfoData) {
        this.userMsgInfoData = userMsgInfoData;
    }

    public static LoginUserServerInfoData valueOf() {
        return new LoginUserServerInfoData();
    }
}
