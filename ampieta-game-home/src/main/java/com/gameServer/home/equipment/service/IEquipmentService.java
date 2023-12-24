package com.gameServer.home.equipment.service;

import com.gameServer.common.ormEntity.*;
import com.gameServer.common.protocol.equipment.base.*;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/15 14 28
 */
public interface IEquipmentService {
    /**
     * 获取 数据库中 存放
     *
     * @param uid 玩家id
     * @return 数据
     */
    List<EquipmentUserDataOrmEntity> GetAllTheUserToEquipmentUserDataOrm(long uid);

    /**
     * 根据品阶返回 品阶数据
     *
     * @param quality 圣遗物 品阶
     * @return 圣遗物 每个品阶 能够到达的最大等级
     */
    EquipmentConfigDataEntity FindQualityGetEquipmentConfigData(int quality);

    /**
     * 根据数据库中的 EquipmentBaseDataEntity 创建 数据
     *
     * @param entity 基础
     * @return 圣遗物 名字 品质 这个装备的属性 可获取属性
     */
    EquipmentBaseData CreateEquipmentBaseData(EquipmentBaseDataEntity entity);

    /**
     * @param entity 圣遗物介绍基础信息
     */
    EquipmentDesBaseData CreateEquipmentDesBaseData(EquipmentDesBaseDataEntity entity);

    /**
     * @param entity 配置
     * @return 返回 圣遗物位置信息
     */
    EquipmentPrimaryConfigBaseData CreateEquipmentPrimaryConfigBaseData(EquipmentPrimaryConfigDataEntity entity);

    /**
     * 圣遗物位置名字
     *
     * @param entity 圣遗物 位置 放入 数据库 缓存
     * @return 圣遗物位置名字
     */
    EquipmentGrowthConfigBaseData CreateEquipmentGrowthConfigBaseData(EquipmentGrowthConfigEntity entity);

    /**
     * @param entity 属性-属性所属pos对应-副属性的初始值数组
     * @return 圣遗物副属性
     */
    EquipmentGrowthViceConfigBaseData CreateEquipmentGrowthViceConfigBaseData(EquipmentGrowthViceConfigDataEntity entity);

    /**
     * 圣遗物 每个品阶 能够到达的最大等级
     * @param entity 圣遗物 每个品阶 能够到达的最大等级
     * @return 圣遗物 每个品阶 能够到达的最大等级
     */
    EquipmentConfigBaseData CreateEquipmentConfigBaseData(EquipmentConfigDataEntity entity);
}
