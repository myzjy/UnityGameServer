package com.gameServer.home.equipment.service;

import com.gameServer.common.ormEntity.EquipmentConfigDataEntity;
import com.gameServer.common.ormEntity.EquipmentUserDataOrmEntity;
import com.gameServer.common.resource.EquipmentConfigResource;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/15 14 28
 */
public interface IEquipmentService {
    /**
     *  获取 数据库中 存放
     * @param uid
     * @return
     */
    List<EquipmentUserDataOrmEntity> GetAllTheUserToEquipmentUserDataOrm(long uid);

    /**
     * 根据品阶返回 品阶数据
     * @param quality
     * @return
     */
    EquipmentConfigDataEntity FindQualityGetEquipmentConfigData(int quality);
}
