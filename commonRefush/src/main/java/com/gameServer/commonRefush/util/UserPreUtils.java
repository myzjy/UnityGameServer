package com.gameServer.commonRefush.util;

import com.zfoo.net.session.Session;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/9 14 30
 */
public abstract class UserPreUtils {
    private static Session _serverClientSession;

    public static void set(Session serverClientSession) {
        _serverClientSession = serverClientSession;
    }

    public static Session get_serverClientSession() {
        return _serverClientSession;
    }
}
