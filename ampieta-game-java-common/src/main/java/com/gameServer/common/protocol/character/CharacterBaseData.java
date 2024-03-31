package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.awt.*;
import java.util.List;

/**
 * 角色基础信息
 *
 * @author zjy
 * @version 1.0
 * @since 2023/9/16 22 57
 */
@Protocol(id = 216)
public class CharacterBaseData implements IPacket {
    private long id;
    /**
     * 角色id 获取 数据库中得基础信息
     */
    private long roleID;
    /**
     * 等级
     */
    private int lv;
    /**
     * 当前最大 的等级
     */
    private int nowMaxLv;
    /**
     * 经验
     */
    private int nowExp;
    /**
     * 当前最大经验
     */
    private int noeMaxExp;
    /**
     * 等级 星级数量
     */
    private int lvQuantity;
    /**
     * 等级 最大 星级数量
     */
    private int maxLvQuantity;
    /**
     * 装备圣遗物 id 数据库中的id
     */
    private List<CharacterEquipmentIDData> equipmentList;
    /**
     * 角色装备 武器
     */
    private CharacterWeaponIDData characterWeaponIDData;

    public static CharacterBaseData valueOf() {
        return new CharacterBaseData();
    }

    /**
     * 进行创建值
     *
     * @param roleID        角色id 获取 数据库中得基础信息
     * @param lv            等级
     * @param nowExp        当前经验
     * @param noeMaxExp     最大经验
     * @param lvQuantity    星级
     * @param maxLvQuantity 最大星级
     * @return 角色基础数据结构
     */
    public static CharacterBaseData ValueOf(long roleID, int lv, int nowExp, int noeMaxExp, int lvQuantity, int maxLvQuantity, int nowMaxLv) {
        var data = new CharacterBaseData();
        data.setRoleID(roleID);
        data.setLv(lv);
        data.setNowExp(nowExp);
        data.setNoeMaxExp(noeMaxExp);
        data.setLvQuantity(lvQuantity);
        data.setMaxLvQuantity(maxLvQuantity);
        data.setNowMaxLv(nowMaxLv);
        return data;
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

    public int getNoeMaxExp() {
        return noeMaxExp;
    }

    public void setNoeMaxExp(int noeMaxExp) {
        this.noeMaxExp = noeMaxExp;
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
}
