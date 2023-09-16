package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;

/**
 * 角色基础信息
 * @author zjy
 * @version 1.0
 * @since 2023/9/16 22 57
 */
public class CharacterBaseData implements IPacket {
    /**
     * 角色id 获取 数据库中得基础信息
     */
    private long roleID;
    private int lv;
    private int nowExp;
    private int noeMaxExp;

}
