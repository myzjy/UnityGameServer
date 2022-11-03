package com.gameServer.commonRefush.protocol.register;

import com.zfoo.protocol.IPacket;

/**
 * 注册服务器控制器返回
 *
 * @author zjy
 * @version 1.0
 * @since 2022/11/3 23:59
 */
public class RegisterResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1006;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    /**
     * 是否注册成功
     */
    private boolean mRegister;

    public static RegisterResponse valueOf(boolean mRegister) {
        var register = new RegisterResponse();
        register.mRegister = mRegister;
        return register;
    }

    public boolean getMRegister() {
        return mRegister;
    }

    public void setMRegister(boolean mRegister) {
        this.mRegister = mRegister;
    }
}
