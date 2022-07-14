package com.unitygameServer.serverGame.singleServer.DataController;

import com.unitygameServer.serverGame.singleServer.model.UserModel;
import org.w3c.dom.events.Event;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * 用户控制器
 * @author zjy
 * @version 0.1
 */
public class UserController {
    /**
     *  用户数据
     * */
    public Dictionary<Integer, UserModel> UserModelDict=new Hashtable<>();

    public void Init(Event _event) {
        boolean bubbles = _event.getBubbles();

    }
}
