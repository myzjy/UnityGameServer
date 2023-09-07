package com.gameServer.commonRefush.protocol.register;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 注册服务器控制器返回
 *
 * @author zjy
 * @version 1.0
 * @since 2022/11/3 23:59
 */
@Protocol(id = 1006)
public class RegisterResponse implements IPacket {

    /**
     * 是否注册成功
     */
    private boolean mRegister;
    /**
     * 注册时，没有注册成功时，错误消息
     */
    private String error;
    
    public static RegisterResponse valueOf(boolean mRegister, String message) {
        var register = new RegisterResponse();
        register.mRegister = mRegister;
        register.setError(message);
        return register;
    }


    public boolean getMRegister() {
        return mRegister;
    }

    public void setMRegister(boolean mRegister) {
        this.mRegister = mRegister;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
