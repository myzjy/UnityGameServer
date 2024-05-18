package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/4 00 21
 */
public class CharacterLevelConfigEntity implements IEntity<Integer> {
    @Id
    private int id;
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
    private String LevelAtkUpNumPro;
    /**
     * 当个突破限制等级每个等级防御力提升数值
     */
    private String LevelDefUpNum;
    /**
     * 当个突破限制等级每个等级生命值提升数值
     */
    private String LevelHpUpNum;
    /**
     * 当前数据创建时间
     */
    private String createAt;
    /**
     * 当前数据更新时间
     */
    private String updateAt;

    @Override
    public Integer id() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterRes() {
        return characterRes;
    }

    public void setCharacterRes(String characterRes) {
        this.characterRes = characterRes;
    }

    public int getLevelNum() {
        return LevelNum;
    }

    public void setLevelNum(int levelNum) {
        LevelNum = levelNum;
    }

    public int getLevelNoAtk() {
        return LevelNoAtk;
    }

    public void setLevelNoAtk(int levelNoAtk) {
        LevelNoAtk = levelNoAtk;
    }

    public float getLevelAtk() {
        return LevelAtk;
    }

    public void setLevelAtk(float levelAtk) {
        LevelAtk = levelAtk;
    }

    public int getLevelHpValue() {
        return LevelHpValue;
    }

    public void setLevelHpValue(int levelHpValue) {
        LevelHpValue = levelHpValue;
    }

    public float getLevelDef() {
        return LevelDef;
    }

    public void setLevelDef(float levelDef) {
        LevelDef = levelDef;
    }

    public float getLevelAtkUp() {
        return LevelAtkUp;
    }

    public void setLevelAtkUp(float levelAtkUp) {
        LevelAtkUp = levelAtkUp;
    }

    public int getLevelNoAtkUp() {
        return LevelNoAtkUp;
    }

    public void setLevelNoAtkUp(int levelNoAtkUp) {
        LevelNoAtkUp = levelNoAtkUp;
    }

    public int getLevelHpValueUp() {
        return LevelHpValueUp;
    }

    public void setLevelHpValueUp(int levelHpValueUp) {
        LevelHpValueUp = levelHpValueUp;
    }

    public int getLevelDefUp() {
        return LevelDefUp;
    }

    public void setLevelDefUp(int levelDefUp) {
        LevelDefUp = levelDefUp;
    }

    public int getLevelAtkUpNum() {
        return LevelAtkUpNum;
    }

    public void setLevelAtkUpNum(int levelAtkUpNum) {
        LevelAtkUpNum = levelAtkUpNum;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getLevelAtkUpNumPro() {
        return LevelAtkUpNumPro;
    }

    public void setLevelAtkUpNumPro(String levelAtkUpNumPro) {
        LevelAtkUpNumPro = levelAtkUpNumPro;
    }

    public String getLevelDefUpNum() {
        return LevelDefUpNum;
    }

    public void setLevelDefUpNum(String levelDefUpNum) {
        LevelDefUpNum = levelDefUpNum;
    }

    public String getLevelHpUpNum() {
        return LevelHpUpNum;
    }

    public void setLevelHpUpNum(String levelHpUpNum) {
        LevelHpUpNum = levelHpUpNum;
    }

    public static CharacterLevelConfigEntity valueOf() {
        return new CharacterLevelConfigEntity();
    }
}
