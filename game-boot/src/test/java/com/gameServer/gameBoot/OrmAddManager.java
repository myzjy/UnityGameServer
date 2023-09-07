package com.gameServer.gameBoot;

import com.gameServer.commonRefush.entity.ItemBoxBaseEntity;
import com.gameServer.commonRefush.ormEntity.StageDataEntity;
import com.gameServer.commonRefush.ormEntity.StageMissionEntity;
import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.gameServer.commonRefush.resource.PuzzleChapterResource;
import com.gameServer.commonRefush.resource.StageMissionResource;
import com.gameServer.commonRefush.resource.StageResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
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

    @StorageAutowired
    private StorageInt<Integer, ItemBaseCsvResource> itemBaseCsvResourceStorage;
    @StorageAutowired
    private StorageInt<Integer, StageResource> stageResourceStorage;
    @StorageAutowired
    private StorageInt<Integer, StageMissionResource> stageMissionResourceStorage;

    /**
     * 更新 stageMission 表
     */
    public void UpdateStageMissionCsvResource() {
        if (stageMissionResourceStorage == null) {
            logger.error("数据表中没有相关数据，请检查相关数据表");
            return;
        }
        var stageMissionDict = stageMissionResourceStorage.getAll();
        var steamCount = stageMissionDict.size();
        logger.info("stageMissionResourceStorage size:{}", steamCount);
        for (var data : stageMissionDict) {
            var entity = OrmContext.getAccessor().load(data.getId(), StageMissionEntity.class);
            var createOrUpdate = TimeUtils.timeToString(TimeUtils.now());
            if (entity == null) {
                var createEntity = getStageMissionEntity(data, createOrUpdate);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                var updateEntity = getStageMissionEntity(data, entity, createOrUpdate);
                OrmContext.getAccessor().update(updateEntity);
            }


        }

    }

    private StageMissionEntity getStageMissionEntity(StageMissionResource data, StageMissionEntity entity, String createOrUpdate) {
        entity.setId(data.getId());
        entity.setPuzzleId(data.getPuzzleId());
        entity.setFailedText(data.getFailedText());
        entity.setFormatText(data.getFormatText());
        entity.setTitle(data.getTitle());
        entity.setOrder(data.getOrder());
        entity.setOutGameSuffix(data.getOutGameSuffix());
        entity.setShortText(data.getShortText());
        if (entity.getCreateAt() == null) {
            entity.setCreateAt(createOrUpdate);
        } else {
            entity.setCreateAt(entity.getCreateAt());
        }
        entity.setUpdateAt(createOrUpdate);
        return entity;
    }

    private StageMissionEntity getStageMissionEntity(StageMissionResource data, String createOrUpdate) {
        var createEntity = StageMissionEntity.valueOf();
        createEntity.setId(data.getId());
        createEntity.setPuzzleId(data.getPuzzleId());
        createEntity.setFailedText(data.getFailedText());
        createEntity.setFormatText(data.getFormatText());
        createEntity.setTitle(data.getTitle());
        createEntity.setOrder(data.getOrder());
        createEntity.setOutGameSuffix(data.getOutGameSuffix());
        createEntity.setShortText(data.getShortText());
        createEntity.setCreateAt(createOrUpdate);
        createEntity.setUpdateAt(createOrUpdate);
        return createEntity;
    }

    /**
     * 插入数据 stage
     */
    public void UpdateStageCsvResource() {
        var stageDict = stageResourceStorage.getAll();
        logger.info("stageDict.count:{}", stageDict.size());
        for (var data : stageDict) {
            var entity = OrmContext.getAccessor().load(data.getId(), StageDataEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            if (entity != null) {
                var updateData = getStageDataEntity(entity, update);
                OrmContext.getAccessor().update(updateData);
            } else {
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

    private StageDataEntity getStageDataEntity(StageDataEntity entity, String update) {
        var updateData = StageDataEntity.ValueOf();
        if (entity.getCreateAt() == null) {
            logger.info(entity.getCreateAt());
            updateData.setCreateAt(update);
        } else {
            updateData.setCreateAt(entity.getCreateAt());
        }
        updateData.setUpdatedAt(update);
        updateData.setId(entity.getId());
        updateData.setOrder(entity.getOrder());
        updateData.setMissionId(entity.getMissionId());
        updateData.setLockMessage(entity.getLockMessage());
        updateData.setStandbyRole(entity.getStandbyRole());
        updateData.setPuzzleId(entity.getPuzzleId());
        return updateData;
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
                var newEntity = getItemBoxBasEntity(data, entity, update);
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

    private ItemBoxBaseEntity getItemBoxBasEntity(ItemBaseCsvResource data, ItemBoxBaseEntity entity, String update) {
        var newEntity = ItemBoxBaseEntity.ValueOf();
        if (entity.getCreateAt() == null) {
            newEntity.setCreateAt(update);
        } else {
            newEntity.setCreateAt(entity.getCreateAt());
        }
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
