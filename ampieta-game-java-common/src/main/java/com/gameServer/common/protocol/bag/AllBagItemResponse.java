package com.gameServer.common.protocol.bag;

import com.gameServer.common.protocol.weapon.WeaponPlayerUserDataStruct;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 回调所有道具
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 22:27
 */
@Protocol(id = 1008)
public class AllBagItemResponse implements IPacket {
    /**
     * 武器
     */
    @Note("武器")
    private List<WeaponPlayerUserDataStruct> weaponUserList;
    @Note("返回协议号")
    private String protocolStr;
    public static AllBagItemResponse ValueOf() {
        return new AllBagItemResponse();
    }
    public static AllBagItemResponse ValueOf(List<WeaponPlayerUserDataStruct> list) {
        var value = new AllBagItemResponse();
        value.weaponUserList = new ArrayList<>();
        return value;
    }

    public List<WeaponPlayerUserDataStruct> getWeaponUserList() {
        return weaponUserList;
    }

    public void setWeaponUserList(List<WeaponPlayerUserDataStruct> weaponUserList) {
        this.weaponUserList = weaponUserList;
    }

    public String getProtocolStr() {
        return protocolStr;
    }

    public void setProtocolStr(String protocolStr) {
        this.protocolStr = protocolStr;
    }
}
