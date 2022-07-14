package com.unitygameServer.serverGame.singleServer.DataController;

import com.unitygameServer.serverGame.commonRefush.protocol.login.LoginRequest;
import com.unitygameServer.serverGame.singleServer.model.UserModel;
import com.zfoo.event.manager.EventBus;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.model.AttributeType;
import com.zfoo.net.session.model.Session;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.util.math.HashUtils;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * 登录控制器
 * @author zjy
 * @version 0.1
 * @since 11:23 下午
 */
public class LoginController {
    /**
     *  用户数据
     * */
    public Dictionary<Integer, UserModel> UserModelDict=new Hashtable<>();

    /**
     * @apiNote 登录调用
     * */
    @PacketReceiver
    public void LoginOut(Session session, LoginRequest request){
        //拿到uid
        var uid=(long)session.getAttribute(AttributeType.UID);
        var sid=session.getSid();

    }
    @PacketReceiver
    private void LoginRequest(Session session, LoginRequest request){
        var account= StringUtils.trim(request.getAccount()) ;
        var password=request.getPassword();

//        EventBus.execute(HashUtils.fnvHash())
    }
}
