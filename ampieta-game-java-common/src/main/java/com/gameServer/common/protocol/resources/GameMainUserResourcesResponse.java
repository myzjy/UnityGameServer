package com.gameServer.common.protocol.resources;

import com.gameServer.common.protocol.playerUser.UserMsgInfoData;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/26 00 03
 */
public class GameMainUserResourcesResponse {
    /**
     * user 相关 资源信息
     */
    private UserMsgInfoData userMsgInfoData;

    public UserMsgInfoData getUserMsgInfoData() {
        return userMsgInfoData;
    }

    public void setUserMsgInfoData(UserMsgInfoData userMsgInfoData) {
        this.userMsgInfoData = userMsgInfoData;
    }

    public static GameMainUserResourcesResponse valueOf() {
        return new GameMainUserResourcesResponse();
    }
}
