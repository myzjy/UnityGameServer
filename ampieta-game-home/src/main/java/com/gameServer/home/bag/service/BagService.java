package com.gameServer.home.bag.service;

import com.gameServer.common.entity.BagUserItemEntity;
import com.gameServer.common.entity.ItemBoxBaseEntity;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.anno.EntityCacheAutowired;
import com.zfoo.orm.cache.IEntityCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/28 14 17
 */
@Component
public class BagService implements IBagService {
    private static final Logger logger = LoggerFactory.getLogger(BagService.class);
    /**
     * 背包基础
     */
    @EntityCacheAutowired
    private IEntityCache<Integer, ItemBoxBaseEntity> itemBoxBaseEntityIEntityCaches;

   
    @Override
    public ItemBoxBaseEntity loadItemBoxBaseEntity(int itemId) {
        var entity = itemBoxBaseEntityIEntityCaches.load(itemId);
        return entity;
    }

    @Override
    public List<BagUserItemEntity> FindBagMasterUserIdEntityOrm(long uid) {
        List<BagUserItemEntity> items = OrmContext.getQuery(BagUserItemEntity.class).eq("masterUserId",uid).queryAll();

        return null;
    }
}
