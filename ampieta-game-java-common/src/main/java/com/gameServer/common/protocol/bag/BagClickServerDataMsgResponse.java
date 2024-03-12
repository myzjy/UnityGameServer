package com.gameServer.common.protocol.bag;

import com.zfoo.protocol.anno.Protocol;

/**
 * 假如 request type 为0 此协议 会返回
 * 不为0 返回其他协议
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/12 22 01
 */
@Protocol(id = 1042)
public class BagClickServerDataMsgResponse {
    /**
     * 返回我调用到面板 类型
     */
    private int msgPalType;
    /**
     * 消息所属者
     */
    private long msgMasterId;
    /**
     * 当前道具 武器 等所属玩家 id
     */
    private long subMasterId;
    /**
     * 具体的面板相关信息
     */
    private BagClickMsgDataPanel data;
}
