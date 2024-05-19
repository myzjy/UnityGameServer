package com.gameServer.common.protocol.character;

import com.gameServer.common.ormEntity.CharacterConfigEntity;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;
import com.zfoo.storage.anno.Id;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/5/19 12 55
 */
@Protocol(id = 224)
public class CharacterConfigData implements IPacket {
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
     * 当前等级没有装备武器攻击属性
     */
    private int Level1NoWAtk;
    /**
     * 角色默认武器id
     */
    private int characterDefaultWeaponId;
    /**
     * 角色默认武器是什么类型
     */
    private int weaponType;
    /**
     * 当前强化的星阶 最大
     */
    private int maxReinforcementEqualOrder;
    /**
     * 角色创建出来的初始等级
     */
    private int lvInit;
    /**
     * 角色创建出来初始最大等级
     */
    private int initLvMax;
    /**
     * 角色元素伤害类型
     */
    private int elementType;
    /**
     * 缩小头像
     */
    private String bagSideIcon;
    /**
     * 头像
     */
    private String bagClickIcon;

    public int getCId() {
        return CId;
    }

    public void setCId(int CId) {
        this.CId = CId;
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

    public int getLevel1NoWAtk() {
        return Level1NoWAtk;
    }

    public void setLevel1NoWAtk(int level1NoWAtk) {
        Level1NoWAtk = level1NoWAtk;
    }

    public int getCharacterDefaultWeaponId() {
        return characterDefaultWeaponId;
    }

    public void setCharacterDefaultWeaponId(int characterDefaultWeaponId) {
        this.characterDefaultWeaponId = characterDefaultWeaponId;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    public int getMaxReinforcementEqualOrder() {
        return maxReinforcementEqualOrder;
    }

    public void setMaxReinforcementEqualOrder(int maxReinforcementEqualOrder) {
        this.maxReinforcementEqualOrder = maxReinforcementEqualOrder;
    }

    public int getLvInit() {
        return lvInit;
    }

    public void setLvInit(int lvInit) {
        this.lvInit = lvInit;
    }

    public int getInitLvMax() {
        return initLvMax;
    }

    public void setInitLvMax(int initLvMax) {
        this.initLvMax = initLvMax;
    }

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public String getBagSideIcon() {
        return bagSideIcon;
    }

    public void setBagSideIcon(String bagSideIcon) {
        this.bagSideIcon = bagSideIcon;
    }

    public String getBagClickIcon() {
        return bagClickIcon;
    }

    public void setBagClickIcon(String bagClickIcon) {
        this.bagClickIcon = bagClickIcon;
    }

    public static CharacterConfigData valueOf() {
        return new CharacterConfigData();
    }


    public static CharacterConfigData valueOf(CharacterConfigEntity entity) {
        var data = valueOf();
        data.setCId(entity.getId());
        data.setQuality(entity.getQuality());
        data.setCharacterId(entity.getCharacterId());
        data.setCharacterName(entity.getCharacterName());
        data.setCharacterRes(entity.getCharacterRes());
        data.setLevel1Atk(entity.getLevel1Atk());
        data.setLevel1ChargingEfficiencyOfElements(entity.getLevel1ChargingEfficiencyOfElements());
        data.setLevel1CriticalHitChance(entity.getLevel1CriticalHitChance());
        data.setLevel1CriticalHitDamage(entity.getLevel1CriticalHitDamage());
        data.setLevel1Def(entity.getLevel1Def());
        data.setLevel1HpValue(entity.getLevel1HpValue());
        data.setLevel1ElementMastery(entity.getLevel1ElementMastery());
        data.setCharacterDefaultWeaponId(entity.getCharacterDefaultWeaponId());
        data.setWeaponType(entity.getWeaponType());
        data.setLevel1NoWAtk(entity.getLevel1NoWAtk());
        data.setMaxReinforcementEqualOrder(entity.getMaxReinforcementEqualOrder());
        data.setLvInit(entity.getLvInit());
        data.setInitLvMax(entity.getInitLvMax());
        data.setElementType(entity.getElementType());
        data.setBagClickIcon(entity.getBagClickIcon());
        data.setBagSideIcon(entity.getBagSideIcon());

        return data;
    }
}
