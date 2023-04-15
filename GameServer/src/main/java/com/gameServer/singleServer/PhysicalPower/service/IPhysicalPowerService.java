package com.gameServer.singleServer.PhysicalPower.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;

/**
 * 体力相关service
 */
public interface IPhysicalPowerService {
    /**
     * 根据id去查找 数据库中的体力
     */
    PhysicalPowerEntity FindOnePhysicalPower(long uid);
}
