package com.unitygameServer.DataController;

import com.unitygameServer.commonRefush.protocol.login.LoginRequest;
import com.zfoo.event.model.anno.EventReceiver;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.model.AttributeType;
import com.zfoo.net.session.model.Session;

import java.util.Dictionary;
import java.util.Enumeration;

/**
 * 登录控制器
 * @author zjy
 * @version 0.1
 * @since 11:23 下午
 */
public class LoginController {


    /**
     * @apiNote 登录调用
     * */
    @PacketReceiver
    public void Login(Session session, LoginRequest request){
        var uid=(long)session.getAttribute(AttributeType.UID);
        var sid=session.getSid();

    }
    private void loginCon(){

    }
}
