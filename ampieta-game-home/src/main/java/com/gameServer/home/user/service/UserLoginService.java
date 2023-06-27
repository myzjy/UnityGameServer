package com.gameServer.home.user.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.resource.AccesGameTimeResource;
import com.gameServer.commonRefush.resource.ConfigResource;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/6/20 10 55
 */
@Component
public class UserLoginService implements IUserLoginService {
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
        return data;
    }

    @Override
    public void UpDataPhysicalPowerEntityCaches(PhysicalPowerEntity entity) {
        physicalPowerEntityIEntityCaches.update(entity);
    }

    @Override
    public PlayerUserEntity LoadPlayerUserEntity(long UserID) {
        var entity = UserModelDict.load(UserID);
        return entity;
    }

    @Override
    public void UpdatePlayerUserEntity(PlayerUserEntity entity) {
        UserModelDict.update(entity);
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
