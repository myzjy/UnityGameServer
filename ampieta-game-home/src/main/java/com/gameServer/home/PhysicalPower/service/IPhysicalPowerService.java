package com.gameServer.home.PhysicalPower.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;

/**
 * 体力相关service
 */
public interface IPhysicalPowerService {
    /**
     * 根据id去查找 数据库中的体力
     */
    PhysicalPowerEntity FindOnePhysicalPower(long uid);

    /**
     * 更新数据库 体力
     *
     * @param entity 体力缓存 更新到数据库中
     */
    void UpdatePhysicalPowerEntityOrm(PhysicalPowerEntity entity);
}
