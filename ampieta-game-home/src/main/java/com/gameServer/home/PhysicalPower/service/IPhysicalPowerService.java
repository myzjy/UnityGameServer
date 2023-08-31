package com.gameServer.home.PhysicalPower.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.resource.ConfigResource;

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



    /**
     * 剩余恢复1点体力时间 进行处理
     *
     * @param entity           Entity
     * @param differenceToTime 离线时间
     * @param config           配置
     * @param userEntity       用户数据
     * @return 返回处理过后的 Entity
     */
    PhysicalPowerEntity PhysicalPowerGetResidueTime(PhysicalPowerEntity entity,
                                                    int differenceToTime,
                                                    ConfigResource config,
                                                    PlayerUserEntity userEntity);

    /**
     * 剩余体力完全恢复 时间 进行处理
     *
     * @param entity           当前体力 Entity
     * @param differenceToTime 离线时间
     * @param userEntity 用户数据
     * @param config  配置
     * @return 返回处理过后的 Entity
     */
    PhysicalPowerEntity PhysicalPowerGetResidueEndTime(PhysicalPowerEntity entity,
                                                       int differenceToTime,
                                                       ConfigResource config,
                                                       PlayerUserEntity userEntity);

    /**
     * 刷新 体力 数据库
     * @param uid 玩家 UID
     */
    boolean RefreshLoginPhysicalPower(long uid);

    /**
     * Create
     * @param lv 玩家等级
     * @param uid 玩家 UID
     */
    void CreatePhysicalPower(int lv,long uid);
}
