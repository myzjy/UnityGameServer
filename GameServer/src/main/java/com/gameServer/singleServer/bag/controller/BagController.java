package com.gameServer.singleServer.bag.controller;

import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.model.Session;
import org.springframework.stereotype.Component;

/**
 * 背包控制器 对于背包相关操作
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 19:49
 */
@Component
public class BagController {
    @PacketReceiver
    public void AtAllBagItemResponse(Session session) {

    }
}