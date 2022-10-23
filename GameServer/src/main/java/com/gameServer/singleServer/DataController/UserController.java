package com.gameServer.singleServer.DataController;

import com.gameServer.singleServer.model.UserModel;

import org.springframework.stereotype.Component;
import org.w3c.dom.events.Event;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * 用户控制器
 *
 * @author zjy
 * @version 0.1
 */
@Component
public class UserController {
    /**
     * 用户数据
     */
    public Dictionary<Integer, UserModel> UserModelDict = new Hashtable<>();

    public void  atDataBasicInformation(){

    }

}
