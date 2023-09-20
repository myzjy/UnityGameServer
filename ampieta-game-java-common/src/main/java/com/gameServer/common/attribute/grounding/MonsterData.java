package com.gameServer.common.attribute.grounding;

import com.gameServer.common.protocol.skill.SkillData;

/**
 * 基础怪物
 * @author zjy
 * @version 1.0
 * @since 2023/9/11 10 49
 */
public class MonsterData {
    /**
     * 怪物id 最基础的id
     */
    private int monsterId;
    /**
     * 基础属性 Hp 根据数组，决定几条命
     */
    private int[] baseHp;
    /**
     * 怪物 资源 根据阶段 更改 资源
     */
    private String[] baseMonsterResources;

    /**
     * 技能id
     */
    private SkillData[] skillBaseDatas;
    /**
     * 是不是boss
     */
    private boolean isBoss;

}
