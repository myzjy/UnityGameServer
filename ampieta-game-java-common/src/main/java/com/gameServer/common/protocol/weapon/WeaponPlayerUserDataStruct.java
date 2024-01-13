package com.gameServer.common.protocol.weapon;

import com.zfoo.net.packet.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/13 22 22
 */
public class WeaponPlayerUserDataStruct implements IPacket {
    /**
     * 武器id
     */
    private int id;
    /**
     * 武器名字
     */
    private String weaponName;
    /**
     * 武器 type 武器所属类型
     */
    private int weaponType;
    /**
     * 当前武器所属技能
     */
    private int nowSkills;



}
