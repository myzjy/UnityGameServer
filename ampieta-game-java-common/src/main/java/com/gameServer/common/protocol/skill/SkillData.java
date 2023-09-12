package com.gameServer.common.protocol.skill;

import com.zfoo.net.packet.IPacket;

/**
 * 技能
 * @author zjy
 * @version 1.0
 * @since 2023/9/12 00 04
 */
public class SkillData  implements IPacket {
    private int skillID;
    private String skillName;
    private String skillDesc;
    private int skillType;
}
