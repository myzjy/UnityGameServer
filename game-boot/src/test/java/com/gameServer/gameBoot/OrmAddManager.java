package com.gameServer.gameBoot;

import com.gameServer.commonRefush.entity.ItemBoxBaseEntity;
import com.gameServer.commonRefush.ormEntity.StageDataEntity;
import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.gameServer.commonRefush.resource.StageResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/26 19 40
 */
@Component
public class OrmAddManager {
    private static final Logger logger = LoggerFactory.getLogger(OrmAddManager.class);
    
    @ResInjection
    private Storage<Integer, ItemBaseCsvResource> itemBaseCsvResourceStorage;
    @ResInjection
    private Storage<Integer, StageResource> stageResourceStorage;

    /**
     * 插入数据 stage
     */
    public void UpdateStageCsvResource() {
        var stageDict = stageResourceStorage.getAll();
        logger.info("stageDict.count:{}",stageDict.stream().count());
        for (var data : stageDict) {
            var entity = OrmContext.getAccessor().load(data.getId(), StageDataEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            if (entity != null) {
                var updateData = StageDataEntity.ValueOf();
                if( entity.getCreateAt()==null){
                    updateData.setCreateAt(update);
                }
                updateData.setUpdatedAt(update);
                updateData.setId(entity.getId());
                updateData.setOrder(entity.getOrder());
                updateData.setMissionId(entity.getMissionId());
                updateData.setLockMessage(entity.getLockMessage());
                updateData.setStandbyRole(entity.getStandbyRole());
                updateData.setPuzzleId(entity.getPuzzleId());
                OrmContext.getAccessor().update(updateData);
            } else {
                /**
                 * 数据库里面没有
                 */
                var createData = StageDataEntity.ValueOf();
                createData.setCreateAt(update);
                createData.setUpdatedAt(update);
                createData.setId(data.getId());
                createData.setOrder(data.getOrder());
                createData.setMissionId(data.getMissionId());
                createData.setLockMessage(data.getLockMessage());
                createData.setStandbyRole(data.getStandbyRole());
                createData.setPuzzleId(data.getPuzzleId());
                OrmContext.getAccessor().insert(createData);
            }

        }

    }

    /**
     * 更新 数据库中资料
     */
    public void UpdateItemBaseCsvResource() {
        var dict = itemBaseCsvResourceStorage.getAll();
        for (var data :
                dict) {
            //设置
            var entity = OrmContext.getAccessor().load(data.getId(), ItemBoxBaseEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());

            if (entity != null) {
                var newEntity = getItemBoxBasEntity(data, update);
                OrmContext.getAccessor().update(newEntity);
            } else {
                var newEntity = getBoxBasEntity(data, update);
                OrmContext.getAccessor().insert(newEntity);
            }
        }

    }

    private ItemBoxBaseEntity getBoxBasEntity(ItemBaseCsvResource data, String update) {
        var newEntity = ItemBoxBaseEntity.ValueOf();
        newEntity.setIcon(data.getIcon());
        newEntity.setItemId(data.getId());
        newEntity.setResources(data.getResourcePath());
        newEntity.setDes(data.getDes());
        newEntity.setName(data.getName());
        newEntity.setMaxNum(data.getMaxNum());
        newEntity.setMinNum(data.getMinNum());
        newEntity.setQuality(data.getQuality());
        newEntity.setType(data.getType());
        newEntity.setCreateAt(update);
        newEntity.setUpdateAt(update);
        return newEntity;
    }

    private ItemBoxBaseEntity getItemBoxBasEntity(ItemBaseCsvResource data, String update) {
        var newEntity = ItemBoxBaseEntity.ValueOf();
        newEntity.setItemId(data.getId());
        newEntity.setIcon(data.getIcon());
        newEntity.setResources(data.getResourcePath());
        newEntity.setDes(data.getDes());
        newEntity.setName(data.getName());
        newEntity.setMaxNum(data.getMaxNum());
        newEntity.setMinNum(data.getMinNum());
        newEntity.setQuality(data.getQuality());
        newEntity.setType(data.getType());
        newEntity.setUpdateAt(update);
        return newEntity;
    }
}
