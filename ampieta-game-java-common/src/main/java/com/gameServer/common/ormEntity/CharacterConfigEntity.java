package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/4 00 17
 */
public class CharacterConfigEntity implements IEntity<Integer> {

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

    public void setId(int CId) {
        this.id = CId;
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

    public int getLevel1Atk() {
        return Level1Atk;
    }

    public void setLevel1Atk(int level1Atk) {
        Level1Atk = level1Atk;
    }

    public int getLevel1CriticalHitChance() {
        return Level1CriticalHitChance;
    }

    public void setLevel1CriticalHitChance(int level1CriticalHitChance) {
        Level1CriticalHitChance = level1CriticalHitChance;
    }

    public int getLevel1HpValue() {
        return Level1HpValue;
    }

    public void setLevel1HpValue(int level1HpValue) {
        Level1HpValue = level1HpValue;
    }

    public int getLevel1Def() {
        return Level1Def;
    }

    public void setLevel1Def(int level1Def) {
        Level1Def = level1Def;
    }

    public int getLevel1ElementMastery() {
        return Level1ElementMastery;
    }

    public void setLevel1ElementMastery(int level1ElementMastery) {
        Level1ElementMastery = level1ElementMastery;
    }

    public int getLevel1ChargingEfficiencyOfElements() {
        return Level1ChargingEfficiencyOfElements;
    }

    public void setLevel1ChargingEfficiencyOfElements(int level1ChargingEfficiencyOfElements) {
        Level1ChargingEfficiencyOfElements = level1ChargingEfficiencyOfElements;
    }

    public int getLevel1CriticalHitDamage() {
        return Level1CriticalHitDamage;
    }

    public void setLevel1CriticalHitDamage(int level1CriticalHitDamage) {
        Level1CriticalHitDamage = level1CriticalHitDamage;
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

    public static CharacterConfigEntity valueOf() {
        return new CharacterConfigEntity();
    }
}
