package com.gameServer.common.protocol.weapon;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * 武器 玩家获取到
 *
 * @author zjy
 * @version 1.0
 * @since 2024/1/14 00 55
 */
@Protocol(id = 1040)
public class WeaponPlayerUserDataResponse implements IPacket {
    /**
     * 当前玩家调用查询到的是谁的装备
     */
    @Note("当前玩家调用查询到的是谁的装备")
    private long usePlayerUid;
    /**
     * 玩家武器数据
     */
    @Note("玩家武器数据")
    private List<WeaponPlayerUserDataStruct> weaponPlayerUserDataStructList;

    public List<WeaponPlayerUserDataStruct> getWeaponPlayerUserDataStructList() {
        return weaponPlayerUserDataStructList;
    }

    public void setWeaponPlayerUserDataStructList(List<WeaponPlayerUserDataStruct> weaponPlayerUserDataStructList) {
        this.weaponPlayerUserDataStructList = weaponPlayerUserDataStructList;
    }

    public long getUsePlayerUid() {
        return usePlayerUid;
    }

    public void setUsePlayerUid(long usePlayerUid) {
        this.usePlayerUid = usePlayerUid;
    }

    public static WeaponPlayerUserDataResponse ValueOf() {
        return new WeaponPlayerUserDataResponse();
    }
}
