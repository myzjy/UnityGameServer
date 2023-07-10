package com.gameServer.home.user.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.resource.AccesGameTimeResource;
import com.gameServer.commonRefush.resource.ConfigResource;
import com.gameServer.home.PhysicalPower.controller.PhysicalPowerUsePropsController;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/6/20 10 55
 */
@Component
public class UserLoginService implements IUserLoginService {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginService.class);
    @ResInjection
    private Storage<Integer, ConfigResource> configResourceStorage;
    @ResInjection
    private Storage<Integer, AccesGameTimeResource> accesGameTimeResourceStorage;
    /**
     * 用户数据
     */
    @EntityCachesInjection
    private IEntityCaches<Long, PlayerUserEntity> UserModelDict;
    @EntityCachesInjection
    private IEntityCaches<Long, PhysicalPowerEntity> physicalPowerEntityIEntityCaches;

    @Override
    public ConfigResource GetConfigResourceData(int lv) {
        var resourceData = configResourceStorage.get(lv);
        return resourceData;
    }

    @Override
    public PhysicalPowerEntity GetToUserIDPhysicalPowerEntity(long UserID) {
        var data = physicalPowerEntityIEntityCaches.load(UserID);
        if(data==null) {
//        OrmContext
        }
        return data;
    }

    @Override
    public void UpDataPhysicalPowerEntityCaches(PhysicalPowerEntity entity) {
        physicalPowerEntityIEntityCaches.update(entity);
        logger.info("[玩家：{}] 更新 PhysicalPowerEntity 缓存 ", entity.getId());

    }

    @Override
    public PlayerUserEntity LoadPlayerUserEntity(long UserID) {
        var entity = UserModelDict.load(UserID);
        return entity;
    }

    @Override
    public void UpdatePlayerUserEntity(PlayerUserEntity entity) {
        UserModelDict.update(entity);
        logger.info("[玩家：{}] 更新 PlayerUser 缓存 ", entity.getId());
        OrmContext.getAccessor().update(entity);
        logger.info("[玩家：{}] 更新 PlayerUser 数据库", entity.getId());
    }

    @Override
    public boolean IsAcesGameTimeResource() {
        if (accesGameTimeResourceStorage == null) {
            return true;
        }
        return false;
    }

    @Override
    public Collection<AccesGameTimeResource> GetAccesTimeAll() {
        return accesGameTimeResourceStorage.getAll();
    }

    @Override
    public IEntityCaches<Long, PlayerUserEntity> AllPlayerUserEntityCaches() {
        return UserModelDict;
    }
}
