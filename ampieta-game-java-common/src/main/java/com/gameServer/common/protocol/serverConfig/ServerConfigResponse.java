package com.gameServer.common.protocol.serverConfig;

import com.gameServer.common.protocol.equipment.base.*;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * 获取基础配置表
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/18 1:48
 */
@Protocol(id = 1010)
public class ServerConfigResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1010;
    /**
     * 背包基础类list
     */
    private List<ItemBaseData> bagItemEntityList;
    /**
     * 装备基础相关 根据品节 卡等级 强化获取副属性条
     */
    private List<EquipmentConfigBaseData> equipmentConfigBaseDataList;
    /**
     * 圣遗物基础配置相关
     */
    private List<EquipmentBaseData> equipmentBaseDataList;
    /**
     * 圣遗物位置信息
     */
    private List<EquipmentPrimaryConfigBaseData> equipmentPrimaryConfigBaseDataList;
    /**
     * 圣遗物介绍
     */
    private List<EquipmentDesBaseData> equipmentDesBaseDataList;
    /**
     * 圣遗物位置名字
     */
    private List<EquipmentGrowthConfigBaseData> equipmentGrowthConfigBaseDataList;
    /**
     * 圣遗物副属性
     */
    private List<EquipmentGrowthViceConfigBaseData> equipmentGrowthViceConfigBaseDataList;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public static ServerConfigResponse ValueOf() {
        return new ServerConfigResponse();
    }

    public List<ItemBaseData> getBagItemEntityList() {
        return bagItemEntityList;
    }

    public void setBagItemEntityList(List<ItemBaseData> bagItemEntityList) {
        this.bagItemEntityList = bagItemEntityList;
    }

    public List<EquipmentConfigBaseData> getEquipmentConfigBaseDataList() {
        return equipmentConfigBaseDataList;
    }

    public void setEquipmentConfigBaseDataList(List<EquipmentConfigBaseData> equipmentConfigBaseDataList) {
        this.equipmentConfigBaseDataList = equipmentConfigBaseDataList;
    }

    public List<EquipmentPrimaryConfigBaseData> getEquipmentPrimaryConfigBaseDataList() {
        return equipmentPrimaryConfigBaseDataList;
    }

    public void setEquipmentPrimaryConfigBaseDataList(List<EquipmentPrimaryConfigBaseData> equipmentPrimaryConfigBaseDataList) {
        this.equipmentPrimaryConfigBaseDataList = equipmentPrimaryConfigBaseDataList;
    }

    public List<EquipmentBaseData> getEquipmentBaseDataList() {
        return equipmentBaseDataList;
    }

    public void setEquipmentBaseDataList(List<EquipmentBaseData> equipmentBaseDataList) {
        this.equipmentBaseDataList = equipmentBaseDataList;
    }

    public List<EquipmentDesBaseData> getEquipmentDesBaseDataList() {
        return equipmentDesBaseDataList;
    }

    public void setEquipmentDesBaseDataList(List<EquipmentDesBaseData> equipmentDesBaseDataList) {
        this.equipmentDesBaseDataList = equipmentDesBaseDataList;
    }

    public List<EquipmentGrowthConfigBaseData> getEquipmentGrowthConfigBaseDataList() {
        return equipmentGrowthConfigBaseDataList;
    }

    public void setEquipmentGrowthConfigBaseDataList(List<EquipmentGrowthConfigBaseData> equipmentGrowthConfigBaseDataList) {
        this.equipmentGrowthConfigBaseDataList = equipmentGrowthConfigBaseDataList;
    }

    public List<EquipmentGrowthViceConfigBaseData> getEquipmentGrowthViceConfigBaseDataList() {
        return equipmentGrowthViceConfigBaseDataList;
    }

    public void setEquipmentGrowthViceConfigBaseDataList(List<EquipmentGrowthViceConfigBaseData> equipmentGrowthViceConfigBaseDataList) {
        this.equipmentGrowthViceConfigBaseDataList = equipmentGrowthViceConfigBaseDataList;
    }
}
