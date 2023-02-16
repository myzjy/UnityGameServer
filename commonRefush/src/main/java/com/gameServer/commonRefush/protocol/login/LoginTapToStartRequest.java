package com.gameServer.commonRefush.protocol.login;

import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 当玩家点击登录游戏 请求，可以作为拦截
 *
 * @author zjy
 * @version 1.0
 * @since 2023/2/11 1:09
 */
@Protocol(id = 1013)
public class LoginTapToStartRequest implements IPacket {

    public static LoginTapToStartRequest valueOf() {
        var Request = new LoginTapToStartRequest();

        return Request;
    }

}
