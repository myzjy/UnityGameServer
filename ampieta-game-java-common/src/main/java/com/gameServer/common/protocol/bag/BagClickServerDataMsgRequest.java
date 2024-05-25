package com.gameServer.common.protocol.bag;

import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 背包点击道具 之类的返回具体信息面板
 * @author zjy
 * @version 1.0
 * @since 2024/3/12 10 36
 */
@Protocol(id=1041)
public class BagClickServerDataMsgRequest {
    /**
     * 点击的名字
     */
    @Note("点击的名字")
    private String clickNameHandler;
    /**
     * 根据所选type返回协议不一样
     */
    @Note("根据所选type返回协议不一样")
    private int clickType;
    /**
     * 具体是那个玩家发送
     */
    @Note("具体是那个玩家发送")
    private long masterUserId;
    /**
     * 根据不同的武器道具id服务器处理不一样
     */
    @Note("根据不同的武器道具id服务器处理不一样")
    private int id;

    public String getClickNameHandler() {
        return clickNameHandler;
    }

    public void setClickNameHandler(String clickNameHandler) {
        this.clickNameHandler = clickNameHandler;
    }

    public int getClickType() {
        return clickType;
    }

    public void setClickType(int clickType) {
        this.clickType = clickType;
    }

    public long getMasterUserId() {
        return masterUserId;
    }

    public void setMasterUserId(long masterUserId) {
        this.masterUserId = masterUserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
