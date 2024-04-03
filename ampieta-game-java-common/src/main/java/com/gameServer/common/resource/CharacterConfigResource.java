package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * 角色 1级的 基础属性
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/2 18 12
 */
@Storage
public class CharacterConfigResource {
    @Id
    @Index
    private int CId;
    /**
     * 品质
     */
    private int quality;
    /**
     * 角色id
     */
    private int characterId;
    /**
     * 角色名
     */
    private String characterName;
    /**
     * 角色资源名
     */
    private String characterRes;
    /**
     * 1级的攻击属性
     */
    private int Level1Atk;
    /**
     * 1级的暴击率
     */
    private int Level1CriticalHitChance;
    /**
     * 1级的生命值
     */
    private int Level1HpValue;
    /**
     * 1级防御力
     */
    private int Level1Def;
    /**
     * 1级元素精通
     */
    private int Level1ElementMastery;
    /**
     * 1级元素充能效率
     */
    private int Level1ChargingEfficiencyOfElements;
    /**
     * 1级暴击伤害
     */
    private int Level1CriticalHitDamage;

    public int getCId() {
        return CId;
    }

    public int getQuality() {
        return quality;
    }

    public int getCharacterId() {
        return characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getCharacterRes() {
        return characterRes;
    }

    public int getLevel1Atk() {
        return Level1Atk;
    }

    public int getLevel1CriticalHitChance() {
        return Level1CriticalHitChance;
    }

    public int getLevel1HpValue() {
        return Level1HpValue;
    }

    public int getLevel1Def() {
        return Level1Def;
    }

    public int getLevel1ElementMastery() {
        return Level1ElementMastery;
    }

    public int getLevel1ChargingEfficiencyOfElements() {
        return Level1ChargingEfficiencyOfElements;
    }

    public int getLevel1CriticalHitDamage() {
        return Level1CriticalHitDamage;
    }
}
