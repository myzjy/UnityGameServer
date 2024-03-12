package com.gameServer.common.protocol.bag;

import com.zfoo.protocol.anno.Protocol;

/**
 * 背包点击道具 之类的返回具体信息面板
 * @author zjy
 * @version 1.0
 * @since 2024/3/12 10 36
 */
@Protocol(id=1041)
public class BagClickServerDataMsgRequest {
    private String clickNameHandler;
}
