package com.gameServer.home.bag.service;

import com.gameServer.commonRefush.entity.ItemBoxBaseEntity;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    @EntityCachesInjection
    private IEntityCaches<Integer, ItemBoxBaseEntity> itemBoxBaseEntityIEntityCaches;

   
    @Override
    public ItemBoxBaseEntity loadItemBoxBaseEntity(int itemId) {
        var entity = itemBoxBaseEntityIEntityCaches.load(itemId);
        return entity;
    }

}
