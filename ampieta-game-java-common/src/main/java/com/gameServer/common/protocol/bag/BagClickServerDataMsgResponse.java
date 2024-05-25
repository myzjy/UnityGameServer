package com.gameServer.common.protocol.bag;

import com.zfoo.orm.anno.Id;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;
import com.zfoo.storage.anno.Index;

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
    @Id
    @Index
    @Note("id")
    private int id;
    /**
     * 返回我调用到面板类型
     */
    @Note("返回我调用到面板类型")
    private int msgPalType;
    /**
     * 消息所属者
     */
    @Note("消息所属者")
    private long msgMasterId;
    /**
     * 当前道具武器等所属玩家 id
     */
    @Note("当前道具武器等所属玩家")
    private long subMasterId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMsgPalType() {
        return msgPalType;
    }

    public void setMsgPalType(int msgPalType) {
        this.msgPalType = msgPalType;
    }

    public long getMsgMasterId() {
        return msgMasterId;
    }

    public void setMsgMasterId(long msgMasterId) {
        this.msgMasterId = msgMasterId;
    }

    public long getSubMasterId() {
        return subMasterId;
    }

    public void setSubMasterId(long subMasterId) {
        this.subMasterId = subMasterId;
    }

    public BagClickMsgDataPanel getData() {
        return data;
    }

    public void setData(BagClickMsgDataPanel data) {
        this.data = data;
    }

    /**
     * 具体的面板相关信息
     */
    private BagClickMsgDataPanel data;
}
