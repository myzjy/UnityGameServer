package com.gameServer.common.protocol.skill;

import com.gameServer.common.attribute.grounding.SkillBaseData;
import com.zfoo.net.packet.IPacket;

/**
 * 技能
 * @author zjy
 * @version 1.0
 * @since 2023/9/12 00 04
 */
public class SkillData  implements IPacket {
    private SkillBaseData skillBaseData;
    /**
     * 技能type
     * 表示 技能类别
     * 对敌人(全体、单体、debuff[已方、敌方]),对已方(只能对自己、已方[单体、全体]、buff[只能单体、全体])
     */
    private int skillType;
    /**
     * 技能基础百分比
     */
    private int baseSKillPercentage;
}
