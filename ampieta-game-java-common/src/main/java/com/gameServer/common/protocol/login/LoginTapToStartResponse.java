package com.gameServer.common.protocol.login;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/2/17 23:15
 */
@Protocol(id = 1014)
public class LoginTapToStartResponse implements IPacket {
    /**
     * 当不能进入游戏时，提示相关信息
     *
     * @author zjy
     * @version 1.0
     */
    private String message;
    /**
     * 是否能进入游戏
     *
     * @author zjy
     * @version 1.0
     */
    private boolean accessGame;


    public static LoginTapToStartResponse ValueOf(String _message, boolean _accessGame) {
        var data = new LoginTapToStartResponse();
        data.setMessage(_message);
        data.setAccessGame(_accessGame);
        return data;
    }

    /**
     * 当不能进入游戏时，提示相关信息
     *
     * @return 信息
     * @author zjy
     * @version 1.0
     */
    public String getMessage() {
        return message;
    }

    /**
     * 当不能进入游戏时，提示相关信息
     *
     * @param message 能否点击开始游戏 不能的时候 相关提示
     * @author zjy
     * @version 1.0
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 是否能进入游戏
     *
     * @return 是否能进入
     * @author zjy
     * @version 1.0
     */
    public boolean isAccessGame() {
        return accessGame;
    }

    /**
     * 是否能进入游戏
     *
     * @param accessGame 能否点击开始游戏
     * @author zjy
     * @version 1.0
     */
    public void setAccessGame(boolean accessGame) {
        this.accessGame = accessGame;
    }
}
