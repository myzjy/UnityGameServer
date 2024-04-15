package com.gameServer.common.protocol.gameMain;

import com.zfoo.protocol.anno.Protocol;

/**
 * 进入游戏获取 自定义的 队伍list 和 已出战 队伍
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/11 18 14
 */
@Protocol(id = 1049)
public class GetGameMainTeamCharacterRequest {
    public static GetGameMainTeamCharacterRequest valueOf() {
        return new GetGameMainTeamCharacterRequest();
    }
}
