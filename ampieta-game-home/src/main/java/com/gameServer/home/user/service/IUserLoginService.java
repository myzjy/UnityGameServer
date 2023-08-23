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

    PlayerUserEntity LoadPlayerUserEntity(long UserID);

    void UpdatePlayerUserEntity(PlayerUserEntity entity);

    /**
     * 插入
     * @param entity
     */
    void InsertPlayerUserEntity(PlayerUserEntity entity);

    boolean IsAcesGameTimeResource();

    Collection<AccesGameTimeResource> GetAccesTimeAll();

    /**
     * 玩家数据
     *
     * @return
     */
    IEntityCaches<Long, PlayerUserEntity> AllPlayerUserEntityCaches();
}
