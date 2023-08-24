package com.gameServer.home.user.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.resource.AccesGameTimeResource;
import com.gameServer.commonRefush.resource.ConfigResource;
import com.zfoo.orm.cache.IEntityCaches;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/6/20 10 56
 */
@Component
public interface IUserLoginService {
    ConfigResource GetConfigResourceData(int lv);

    int ConfigResourceLength();


    /**
     * 读取 先读取 数据库中的 数据 如果数据库中没有 读取缓存数据 读取到，并插入数据
     *
     * @param UserID 玩家 uid
     * @return 返回查找到的玩家数据
     */
    PlayerUserEntity LoadPlayerUserEntity(long UserID);

    /**
     * 更新缓存 玩家数据缓存 同时更新数据库中的玩家数据
     *
     * @param entity 修改过之后的玩家数据
     */
    void UpdatePlayerUserEntity(PlayerUserEntity entity);

    /**
     * 插入
     *
     * @param entity
     */
    void InsertPlayerUserEntity(PlayerUserEntity entity);

    /**
     * 服务器开启时间配置 是否有
     *
     * @return 返回是有相关配置
     */
    boolean IsAcesGameTimeResource();

    /**
     * 返回服务器时间配置集合
     *
     * @return 返回服务器开始时间集合
     */
    Collection<AccesGameTimeResource> GetAccesTimeAll();

    /**
     * 玩家数据
     *
     * @return 返回玩家数据 缓存集合
     */
    IEntityCaches<Long, PlayerUserEntity> AllPlayerUserEntityCaches();
}
