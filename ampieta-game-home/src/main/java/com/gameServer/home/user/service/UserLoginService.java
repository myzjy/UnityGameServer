package com.gameServer.home.user.service;

import com.gameServer.common.entity.AccessGameTimeEntity;
import com.gameServer.common.entity.PhysicalPowerEntity;
import com.gameServer.common.entity.PlayerUserEntity;
import com.gameServer.common.entity.config.ConfigResourceEntity;
import com.gameServer.common.resource.AccesGameTimeResource;
import com.gameServer.common.resource.ConfigResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.anno.EntityCacheAutowired;
import com.zfoo.orm.cache.IEntityCache;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
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
    @StorageAutowired
    private StorageInt<Integer, AccesGameTimeResource> accesGameTimeResourceStorage;
    /**
     * 用户数据
     */
    @EntityCacheAutowired
    private IEntityCache<Long, PlayerUserEntity> UserModelDict;
    @EntityCacheAutowired
    private IEntityCache<Long, PhysicalPowerEntity> physicalPowerEntityIEntityCaches;
    @EntityCacheAutowired
    private IEntityCache<Integer, AccessGameTimeEntity> accessGameTimeEntityIEntityCaches;

    @Override
    public ConfigResourceEntity GetConfigResourceData(int lv) {
        var resourceData = OrmContext.getAccessor().load(lv, ConfigResourceEntity.class);
        return resourceData;
    }

    @Override
    public int ConfigResourceLength() {
        var list = OrmContext.getQuery(ConfigResourceEntity.class).queryAll();
        var length = list.size();
        return length;
    }

    @Override
    public PlayerUserEntity LoadPlayerUserEntity(long UserID) {
        var entity
                = OrmContext.getAccessor().load(UserID, PlayerUserEntity.class);
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
    public void InsertPlayerUserEntity(PlayerUserEntity entity) {
        OrmContext.getAccessor().insert(entity);
        UserModelDict.update(entity);
        logger.info("插入 PlayerUser 数据库 PlayerUserEntity:{}", JsonUtils.object2String(entity));
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
    public IEntityCache<Long, PlayerUserEntity> AllPlayerUserEntityCaches() {
        return UserModelDict;
    }

    @Override
    public AccessGameTimeEntity FindAccessGameTimeEntity(int itemId) {
        var data = accessGameTimeEntityIEntityCaches.load(itemId);
        if (data == null) {
            data = OrmContext.getAccessor().load(itemId, AccessGameTimeEntity.class);
        }
        return data;
    }

    @Override
    public void InsertAccessGameTimeEntity(AccessGameTimeEntity entity) {
        OrmContext.getAccessor().insert(entity);
        accessGameTimeEntityIEntityCaches.update(entity);
    }

    @Override
    public void UpdateAccessGameTimeEntity(AccessGameTimeEntity entity) {
        OrmContext.getAccessor().update(entity);
        accessGameTimeEntityIEntityCaches.update(entity);
    }
}
