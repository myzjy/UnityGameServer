package com.gameServer.common.constant;

/**
 * 属性 type
 */
public enum EquipmentPosGrowthType {
    ATK(1, "攻击力百分比", "攻击力"),
    AttackValue(2,"攻击力数值","攻击力"),
    CriticalHitChance(3, "暴击率", "暴击率"),
    HpValue(4,"生命值数值","生命值"),
    Vitality(5,"生命值百分比","生命值"),
    DefensiveValue(6,"防御力数值","防御力"),
    Defensive(7,"防御力百分比","防御力"),
    ElementMastery(8,"元素精通","元素精通"),
    ChargingEfficiencyOfElements(9,"元素充能效率","元素充能效率"),
    CriticalHitDamage(10,"暴击伤害","暴击伤害")
    ;
    /**
     * 属性 type
     */
    int type;
    /**
     * 属性 名字 介绍
     */
    String growthStr;
    /**
     * 属性名字
     */
    String name;

    EquipmentPosGrowthType(int type, String growthStr, String name) {
        setType(type);
        setGrowthStr(growthStr);
        setName(name);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGrowthStr() {
        return growthStr;
    }

    public void setGrowthStr(String growthStr) {
        this.growthStr = growthStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
