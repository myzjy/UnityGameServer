package com.gameServer.common.util;

import com.zfoo.net.util.security.AesUtils;
import com.zfoo.protocol.model.Triple;
import com.zfoo.protocol.util.RandomUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/15 21:42
 */
public abstract class TokenUtils {
    private static final long TOKEN_EXPIRE_TIME = 100 * TimeUtils.MILLIS_PER_WEEK;

    /**
     * 加密方式：用户id，加盐，过期时间戳
     */
    public static String set(long userId) {

        var salt = RandomUtils.randomString(8);
        var now = TimeUtils.now() + TOKEN_EXPIRE_TIME;
        var source = userId + StringUtils.VERTICAL_BAR + salt + StringUtils.VERTICAL_BAR + now;
        return AesUtils.getEncryptString(source);
    }


    public static Triple<Long, String, Long> get(String token) {
        var source = AesUtils.getDecryptString(token);
        var splits = source.split(StringUtils.VERTICAL_BAR_REGEX);
        var userId = Long.parseLong(splits[0]);
        var salt = splits[1];
        var expireTime = Long.parseLong(splits[2]);
        return new Triple<>(userId, salt, expireTime);
    }
}
