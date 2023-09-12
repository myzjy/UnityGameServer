package com.gameServer.common.attribute.grounding;

import java.util.Dictionary;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/11 13 10
 */
public class SkillBaseData {
    /**
     * 技能id
     */
    private int skillId;
    /**
     * 技能type 伤害系数 等
     */
    private Dictionary<Integer,Integer> skillDict;
    /**
     * 技能名字
     */
    private String skillName;
    /**
     * 技能介绍
     */
    private String skillDesc;
    /**
     * 技能type
     */
    private int skillType;

}
