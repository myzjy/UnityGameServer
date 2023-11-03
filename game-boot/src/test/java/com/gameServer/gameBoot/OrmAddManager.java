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
    private StorageInt<Integer, EquipmentGrowthViceConfigResource> equipmentGrowthViceConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, EquipmentGrowthConfigResource> equipmentGrowthConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, AccesGameTimeResource> accesGameTimeResourceStorage;
    public void  UpdateOrInAccesGameTimeResource() throws Exception {
        if(accesGameTimeResourceStorage==null){
            throw new Exception("AccesGameTimeResource 数据表 不存在");
        }
        var dict = accesGameTimeResourceStorage.getAll();
        for (var item : dict) {

            var entity =  OrmContext.getAccessor().load(item.getTimeID(), AccessGameTimeEntity.class);
            if (entity == null) {
                //数据库没有相关配置
                entity = new AccessGameTimeEntity();
                entity.setTimeID(item.getTimeID());
                entity.setTime(new Date(item.getTime()));
                entity.setId(item.getTimeID());
                OrmContext.getAccessor().insert(entity);
            } else {
                entity = new AccessGameTimeEntity();
                entity.setTimeID(item.getTimeID());
                entity.setTime(new Date(item.getTime()));
                entity.setId(item.getTimeID());
                OrmContext.getAccessor().update(entity);
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
            if (ormEntity == null) {
                var createEntity = getConfigResourceEntity(data, createOrUpdate);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                var updateEntity = getConfigResourceEntity(data, ormEntity);
                OrmContext.getAccessor().update(updateEntity);
            }
        }
    }

    private ConfigResourceEntity getConfigResourceEntity(ConfigResource data, String createOrUpdate) {
        var createEntity = ConfigResourceEntity.ValueOf();
        createEntity.setId(data.getLv());
        createEntity.setLv(data.getLv());
        createEntity.setMaxExp(data.getMaxExp());
        createEntity.setResidueTime(data.getResidueTime());
        createEntity.setMaxPhysical(data.getMaxPhysical());
        createEntity.setTheLock(data.isTheLock());
        createEntity.setCreateAt(createOrUpdate);
        createEntity.setUpdateAt(createOrUpdate);
        return createEntity;
    }

    private ConfigResourceEntity getConfigResourceEntity(ConfigResource data, ConfigResourceEntity ormEntity) {
        var updateEntity = ConfigResourceEntity.ValueOf();
        if (ormEntity.getLv() != data.getLv()) {
            //数据库中 和 本地 配置 表不一致 依据 本地为准
            updateEntity.setLv(data.getLv());
        } else {
            updateEntity.setLv(data.getLv());
        }
        if (ormEntity.getResidueTime() != data.getResidueTime()) {
            //数据库中 和 本地 配置 表不一致 依据 本地为准
            updateEntity.setResidueTime(data.getResidueTime());
        } else {
            updateEntity.setResidueTime(data.getResidueTime());
        }
        if (ormEntity.getMaxPhysical() != data.getMaxPhysical()) {
            //数据库中 和 本地 配置 表不一致 依据 本地为准
            updateEntity.setMaxPhysical(data.getMaxPhysical());
        } else {
            updateEntity.setMaxPhysical(data.getMaxPhysical());
        }
        if (ormEntity.getMaxExp() != data.getMaxExp()) {
            //数据库中 和 本地 配置 表不一致 依据 本地为准
            updateEntity.setMaxExp(data.getMaxExp());
        } else {
            updateEntity.setMaxExp(data.getMaxExp());
        }
        if (ormEntity.isTheLock() != data.isTheLock()) {
            //数据库中 和 本地 配置 表不一致 依据 本地为准
            updateEntity.setTheLock(data.isTheLock());
        } else {
            updateEntity.setTheLock(data.isTheLock());
        }
        updateEntity.setId(data.getLv());
        return updateEntity;
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

    public void UpdateEquipmentGrowthViceConfigResource() {
        var dict = equipmentGrowthViceConfigResourceStorageInt.getAll();
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getViceId(), EquipmentGrowthViceConfigDataEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            if (entity != null) {
                entity.setId(entity.getViceId());
                //数据库中存在
                entity.setViceId(entity.getViceId());
                entity.setInitNums(entity.getInitNums());
                entity.setPosGrowthType(entity.getPosGrowthType());
                entity.setViceName(entity.getViceName());
                entity.setCreateAt(entity.getCreateAt());
                entity.setUpdateAt(update);
                OrmContext.getAccessor().update(entity);
            } else {
                var str = data.getInitNums().split("/");
                var initNums = new ArrayList<String>();
                var index = 0;
                for (var s : str) {
                    initNums.add(s);
                }
                //数据库中不存在
                var dataCreate = EquipmentGrowthViceConfigDataEntity.ValueOf(data.getViceId(), data.getViceName(), data.getPosGrowthType(), initNums);
                dataCreate.setId(data.getViceId());
                dataCreate.setUpdateAt(update);
                dataCreate.setCreateAt(update);
                OrmContext.getAccessor().insert(dataCreate);
            }
        }
    }
}
