package com.gameServer.home.equipment.service;

import com.gameServer.common.ormEntity.EquipmentUserDataOrmEntity;

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
}
