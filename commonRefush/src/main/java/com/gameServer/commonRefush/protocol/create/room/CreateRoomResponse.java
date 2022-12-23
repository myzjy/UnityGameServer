package com.gameServer.commonRefush.protocol.create.room;

import com.zfoo.protocol.IPacket;

/**
 * 创建房间返回
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/21 19:38
 */
public class CreateRoomResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1011;
    /**
     * 房间id
     */
    private int roomId;

    public static CreateRoomResponse ValueOf() {
        return new CreateRoomResponse();
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

}
