package com.gameServer.common.protocol.bag;

import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 默认传递 的数据
 * @author zjy
 * @version 1.0
 * @since 2024/3/12 23 31
 */
@Protocol(id = 215)
public class BagClickMsgDataPanel {
    @Note("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
