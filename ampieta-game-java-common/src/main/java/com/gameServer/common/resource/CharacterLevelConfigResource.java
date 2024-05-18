package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * 角色 抵达可以突破的等级 的 属性 突破前和突破后
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/2 18 12
 */
@Storage
public class CharacterLevelConfigResource {
    @Id
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
     * 等级
     */
    private int LevelNum;
    /**
     * 当前等级没有装备武器攻击属性
     */
    private int LevelNoAtk;
    /**
     * 当前等级的攻击属性
     */
    private float LevelAtk;
    /**
     * 当前等级的生命值
     */
    private int LevelHpValue;
    /**
     * 当前等级防御力
     */
    private float LevelDef;
    /**
     * 当前等级突破之后的攻击属性
     */
    private float LevelAtkUp;
    /**
     * 当前等级没有装备武器 突破之后的攻击属性
     */
    private int LevelNoAtkUp;
    /**
     * 当前等级的突破生命值
     */
    private int LevelHpValueUp;
    /**
     * 当前等级突破防御力
     */
    private int LevelDefUp;
    /**
     * 当前等级突破之后的攻击属提升百分比
     */
    private int LevelAtkUpNum;
    /**
     * 当前等级每个等级攻击力提升数值
     */
    private float LevelAtkUpNumPro;
    /**
     * 当个突破限制等级每个等级防御力提升数值
     */
    private float LevelDefUpNum;
    /**
     * 当个突破限制等级每个等级生命值提升数值
     */
    private float LevelHpUpNum;

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

    public int getLevelNoAtk() {
        return LevelNoAtk;
    }

    public float getLevelAtk() {
        return LevelAtk;
    }

    public int getLevelHpValue() {
        return LevelHpValue;
    }

    public float getLevelDef() {
        return LevelDef;
    }

    public float getLevelAtkUp() {
        return LevelAtkUp;
    }

    public int getLevelNoAtkUp() {
        return LevelNoAtkUp;
    }

    public int getLevelHpValueUp() {
        return LevelHpValueUp;
    }

    public int getLevelDefUp() {
        return LevelDefUp;
    }

    public int getLevelAtkUpNum() {
        return LevelAtkUpNum;
    }

    public int getLevelNum() {
        return LevelNum;
    }

    public float getLevelAtkUpNumPro() {
        return LevelAtkUpNumPro;
    }

    public float getLevelDefUpNum() {
        return LevelDefUpNum;
    }

    public float getLevelHpUpNum() {
        return LevelHpUpNum;
    }
}
