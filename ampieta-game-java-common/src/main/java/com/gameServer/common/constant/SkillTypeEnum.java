package com.gameServer.common.constant;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/12 19 22
 */
public enum SkillTypeEnum {
    ToTheEnemyMonomerLasting(1,"对敌单体")
    ;
    /**
     * 技能type
     */
    private int type;
    /**
     * 技能type 介绍
     */
    private String skillTypeDesc;

    SkillTypeEnum(int type,String desc) {
        setType(type);
        setSkillTypeDesc(desc);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSkillTypeDesc() {
        return skillTypeDesc;
    }

    public void setSkillTypeDesc(String skillTypeDesc) {
        this.skillTypeDesc = skillTypeDesc;
    }
}
