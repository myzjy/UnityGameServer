package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * 角色基础信息 传递的都是 初略 计算完的 信息
 *
 * @author zjy
 * @version 1.0
 * @since 2023/9/16 22 57
 */
@Protocol(id = 216)
public class CharacterBaseData implements IPacket {
    @Note("这个角色index")
    private long id;
    /**
     * 角色id获取数据库中得基础信息
     */
    @Note("角色id获取数据库中得基础信息")
    private long roleID;
    /**
     * 角色品质
     */
    @Note("角色品质")
    private int quantity;
    /**
     * 等级
     */
    @Note("等级")
    private int lv;
    /**
     * 当前最大的等级
     */
    @Note("当前最大的等级")
    private int nowMaxLv;
    /**
     * 经验
     */
    @Note("经验")
    private int nowExp;
    /**
     * 当前最大经验
     */
    @Note("当前最大经验")
    private int nowMaxExp;
    /**
     * 等级星级数量
     */
    @Note("等级星级数量")
    private int lvQuantity;
    /**
     * 等级最大星级数量
     */
    @Note("等级最大星级数量")
    private int maxLvQuantity;
    /**
     * 装备圣遗物id数据库中的id
     */
    @Note("装备圣遗物id数据库中的id")
    private List<CharacterEquipmentIDData> equipmentList;
    /**
     * 角色装备武器
     */
    @Note("角色装备武器")
    private CharacterWeaponIDData characterWeaponIDData;
    /**
     * 角色元素伤害类型
     */
    @Note("角色元素伤害类型")
    private int elementType;
    /**
     * 元素伤害数据百分比的
     */
    @Note("元素伤害数据百分比的")
    private float elementNum;

    public static CharacterBaseData valueOf() {
        return new CharacterBaseData();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getNowExp() {
        return nowExp;
    }

    public void setNowExp(int nowExp) {
        this.nowExp = nowExp;
    }

    public int getNowMaxExp() {
        return nowMaxExp;
    }

    public void setNowMaxExp(int nowMaxExp) {
        this.nowMaxExp = nowMaxExp;
    }

    public int getLvQuantity() {
        return lvQuantity;
    }

    public void setLvQuantity(int lvQuantity) {
        this.lvQuantity = lvQuantity;
    }

    public int getMaxLvQuantity() {
        return maxLvQuantity;
    }

    public void setMaxLvQuantity(int maxLvQuantity) {
        this.maxLvQuantity = maxLvQuantity;
    }

    public int getNowMaxLv() {
        return nowMaxLv;
    }

    public void setNowMaxLv(int nowMaxLv) {
        this.nowMaxLv = nowMaxLv;
    }

    public List<CharacterEquipmentIDData> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<CharacterEquipmentIDData> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public CharacterWeaponIDData getCharacterWeaponIDData() {
        return characterWeaponIDData;
    }

    public void setCharacterWeaponIDData(CharacterWeaponIDData characterWeaponIDData) {
        this.characterWeaponIDData = characterWeaponIDData;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getElementType() {
        return elementType;
    }

    public void setElementType(int elementType) {
        this.elementType = elementType;
    }

    public float getElementNum() {
        return elementNum;
    }

    public void setElementNum(float elementNum) {
        this.elementNum = elementNum;
    }
}
