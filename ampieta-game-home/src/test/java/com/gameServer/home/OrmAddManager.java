package com.gameServer.home;

import com.gameServer.commonRefush.entity.ItemBoxBasEntity;
import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.gameServer.commonRefush.resource.PuzzleResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/26 19 40
 */
@Component
public class OrmAddManager {
    @ResInjection
    public Storage<Integer, ItemBaseCsvResource> itemBaseCsvResourceStorage;

    /**
     * 更新 数据库中资料
     */
    public void UpdateItemBaseCsvResource() {
        var dict = itemBaseCsvResourceStorage.getAll();
        for (var data :
                dict) {
            //设置
            var entity = OrmContext.getAccessor().load(data.getId(), ItemBoxBasEntity.class);
            if (entity != null && entity.empty()) {

            } else {
                var newEntity = new ItemBoxBasEntity();
                newEntity.setItemId(data.getId());
            }
        }

    }
}
