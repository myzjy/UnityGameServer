package com.gameServer.gameBoot;

import com.gameServer.common.ormEntity.CharacterConfigEntity;
import com.gameServer.common.resource.CharacterConfigResource;
import com.gameServer.common.resource.CharacterLevelConfigResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/4 00 07
 */
@Component
public class CharacterOrmManager {
    @StorageAutowired
    private StorageInt<Integer, CharacterConfigResource> characterConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, CharacterLevelConfigResource> characterLevelConfigResourceStorageInt;

    public void UpdateCharacterConfigResource() {
        var dict = characterConfigResourceStorageInt.getAll();
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getCId(), CharacterConfigEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            if (entity != null) {
                entity.setUpdateAt(update);
            } else {
                entity = CharacterConfigEntity.valueOf();
                entity.setCreateAt(TimeUtils.timeToString(TimeUtils.now()));
                entity.setUpdateAt(TimeUtils.timeToString(TimeUtils.now()));
            }
        }
    }
}
