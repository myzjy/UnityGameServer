package com.gameServer.gameRoom.boomEvent.createRoom.controller;

import com.gameServer.commonRefush.protocol.create.room.CreateRoomResponse;
import com.gameServer.gameRoom.roomEvent.CreateRoomEvent;
import com.zfoo.event.model.anno.EventReceiver;
import com.zfoo.net.NetContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 创建房间控制器
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/20 23:39
 */
@Component
public class CreateRoomController {
    private static final Logger logger = LoggerFactory.getLogger(CreateRoomController.class);

    @EventReceiver
    public void onCreateRoomEvent(CreateRoomEvent event) {

        NetContext.getRouter().send(event.getOtherSession(), CreateRoomResponse.ValueOf());
    }

}
