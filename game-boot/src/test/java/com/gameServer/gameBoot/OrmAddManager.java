package com.gameServer.gameBoot;

import com.gameServer.common.entity.AccessGameTimeEntity;
import com.gameServer.common.entity.ItemBoxBaseEntity;
import com.gameServer.common.entity.config.ConfigResourceEntity;
import com.gameServer.common.ormEntity.EquipmentGrowthViceConfigDataEntity;
import com.gameServer.common.ormEntity.StageDataEntity;
import com.gameServer.common.ormEntity.StageMissionEntity;
import com.gameServer.common.resource.*;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/26 19 40
 */
@Component
public class OrmAddManager {
    private static final Logger logger = LoggerFactory.getLogger(OrmAddManager.class);
    @StorageAutowired
    private StorageInt<Integer, ConfigResource> configResourceStorage;
    @StorageAutowired
    private StorageInt<Integer, ItemBaseCsvResource> itemBaseCsvResourceStorage;
    @StorageAutowired
    private StorageInt<Integer, StageResource> stageResourceStorage;
    @StorageAutowired
    private StorageInt<Integer, StageMissionResource> stageMissionResourceStorage;
    @StorageAutowired
    private StorageInt<Integer, AccesGameTimeResource> accesGameTimeResourceStorage;

    public void UpdateOrInAccesGameTimeResource() throws Exception {
        if (accesGameTimeResourceStorage == null) {
            throw new Exception("AccesGameTimeResource 数据表 不存在");
        }
        var dict = accesGameTimeResourceStorage.getAll();
        for (var item : dict) {
            var entity = OrmContext.getAccessor().load(item.getTimeID(), AccessGameTimeEntity.class);
            var createEntity = AccessGameTimeEntity.valueOf();
            createEntity.setTimeID(item.getTimeID());
            createEntity.setTime(item.getTime());
            createEntity.setId(item.getTimeID());
            var createOrUpdate = TimeUtils.timeToString(TimeUtils.now());
            createEntity.setUpdateAt(createOrUpdate);
            if (entity == null) {
                createEntity.setCreateAt(createOrUpdate);
                //数据库没有相关配置
                OrmContext.getAccessor().insert(createEntity);
            } else {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    createEntity.setCreateAt(createOrUpdate);
                } else {
                    createEntity.setCreateAt(entity.getCreateAt());
                }
                OrmContext.getAccessor().update(createEntity);
            }
            logger.info("AccesGameTimeResource:{}", JsonUtils.object2String(entity));
        }
    }

    public void UpdateConfigResource() throws Exception {
        if (configResourceStorage == null) {
            throw new Exception("ConfigResource 数据表 不存在");
        }
        var configAll = configResourceStorage.getAll();
        for (var data : configAll) {
            var ormEntity = OrmContext.getAccessor().load(data.getLv(), ConfigResourceEntity.class);
            var createOrUpdate = TimeUtils.timeToString(TimeUtils.now());
            var createEntity = ConfigResourceEntity.ValueOf();
            createEntity.setId(data.getLv());
            createEntity.setLv(data.getLv());
            createEntity.setUpdateAt(createOrUpdate);
            createEntity.setMaxExp(data.getMaxExp());
            createEntity.setTheLock(data.isTheLock());
            createEntity.setResidueTime(data.getResidueTime());
            createEntity.setMaxPhysical(data.getMaxPhysical());
            if (ormEntity == null) {
                createEntity.setCreateAt(createOrUpdate);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                if (StringUtils.isEmpty(ormEntity.getCreateAt())) {
                    createEntity.setCreateAt(createOrUpdate);
                } else {
                    createEntity.setCreateAt(ormEntity.getCreateAt());
                }
                OrmContext.getAccessor().update(createEntity);
            }
        }
    }

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
        if (StringUtils.isEmpty(entity.getCreateAt())) {
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
            var updateData = StageDataEntity.ValueOf();
            updateData.setUpdatedAt(update);
            updateData.setId(data.getId());
            updateData.setOrder(data.getOrder());
            updateData.setMissionId(data.getMissionId());
            updateData.setLockMessage(data.getLockMessage());
            updateData.setStandbyRole(data.getStandbyRole());
            updateData.setPuzzleId(data.getPuzzleId());
            if (entity != null) {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    updateData.setCreateAt(update);
                } else {
                    updateData.setCreateAt(entity.getCreateAt());
                }
                OrmContext.getAccessor().update(updateData);
            } else {
                updateData.setCreateAt(update);
                OrmContext.getAccessor().insert(updateData);
            }
        }
    }

    /**
     * 更新 数据库中资料
     */
    public void UpdateItemBaseCsvResource() {
        var dict = itemBaseCsvResourceStorage.getAll();
        for (var data : dict) {
            //设置
            var entity = OrmContext.getAccessor().load(data.getId(), ItemBoxBaseEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            var createEntity = ItemBoxBaseEntity.ValueOf();
            createEntity.setIcon(data.getIcon());
            createEntity.setItemId(data.getId());
            createEntity.setResources(data.getResourcePath());
            createEntity.setDes(data.getDes());
            createEntity.setName(data.getName());
            createEntity.setMaxNum(data.getMaxNum());
            createEntity.setMinNum(data.getMinNum());
            createEntity.setQuality(data.getQuality());
            createEntity.setType(data.getType());
            createEntity.setUpdateAt(update);
            if (entity != null) {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    createEntity.setCreateAt(update);
                } else {
                    createEntity.setCreateAt(entity.getCreateAt());
                }
                OrmContext.getAccessor().update(createEntity);
            } else {
                createEntity.setCreateAt(update);
                OrmContext.getAccessor().insert(createEntity);
            }
        }
    }
}
