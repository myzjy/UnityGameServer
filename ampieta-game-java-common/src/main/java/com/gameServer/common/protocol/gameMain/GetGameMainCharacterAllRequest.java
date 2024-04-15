package com.gameServer.common.protocol.gameMain;

import com.zfoo.protocol.anno.Protocol;

/**
 * 先请求队伍列表
 * 在进入 gameMain 场景请求 协议
 * 获取所有角色
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/15 19 54
 */
@Protocol(id = 1049)
public class GetGameMainCharacterAllRequest {
}
