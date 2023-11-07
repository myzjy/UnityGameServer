package com.gameServer.gameBoot;

import com.gameServer.common.ormEntity.EquipmentGrowthConfigEntity;
import com.gameServer.common.ormEntity.EquipmentGrowthViceConfigDataEntity;
import com.gameServer.common.ormEntity.EquipmentPrimaryConfigDataEntity;
import com.gameServer.common.resource.EquipmentGrowthConfigResource;
import com.gameServer.common.resource.EquipmentGrowthViceConfigResource;
import com.gameServer.common.resource.EquipmentPrimaryConfigResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/6 14 46
 */
@Component
public class EquipmentOrmAddManager {
    @StorageAutowired
    private StorageInt<Integer, EquipmentGrowthViceConfigResource> equipmentGrowthViceConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, EquipmentGrowthConfigResource> equipmentGrowthConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, EquipmentPrimaryConfigResource> equipmentPrimaryConfigResourceStorageInt;

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
                Collections.addAll(initNums, str);
                //数据库中不存在
                var dataCreate = EquipmentGrowthViceConfigDataEntity.ValueOf(data.getViceId(), data.getViceName(), data.getPosGrowthType(), initNums);
                dataCreate.setId(data.getViceId());
                dataCreate.setUpdateAt(update);
                dataCreate.setCreateAt(update);
                OrmContext.getAccessor().insert(dataCreate);
            }
        }
    }

    public void UpdateEquipmentGrowthConfigResource() {
        var dict = equipmentGrowthConfigResourceStorageInt.getAll();
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getId(), EquipmentGrowthConfigEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            var createOrUpdateEntity = EquipmentGrowthConfigEntity.ValueOf();
            createOrUpdateEntity.setId(data.getId());
            createOrUpdateEntity.setLocationOfEquipmentType(data.getLocationOfEquipmentType());
            createOrUpdateEntity.setPosName(data.getPosName());
            if (entity == null) {
                //创建
                createOrUpdateEntity.setCreateAt(update);
                createOrUpdateEntity.setUpdateAt(update);
                OrmContext.getAccessor().insert(createOrUpdateEntity);
            } else {
                createOrUpdateEntity.setUpdateAt(update);
                OrmContext.getAccessor().update(createOrUpdateEntity);
            }
        }
    }

    public void UpdateEquipmentPrimaryConfigResource() {
        var dict = equipmentPrimaryConfigResourceStorageInt.getAll();
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getId(), EquipmentPrimaryConfigDataEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            var createEntity = EquipmentPrimaryConfigDataEntity.ValueOf();
            createEntity.setId(data.getId());
            createEntity.setGrowthPosInt(data.getGrowthPosInt());
            createEntity.setGrowthPosName(data.getGrowthPosName());
            createEntity.setPrimaryQuality(data.getPrimaryQuality());
            createEntity.setPrimaryGrowthMaxInt(data.getPrimaryGrowthMaxInt());
            createEntity.setPrimaryGrowthInts(data.getPrimaryGrowthInts());
            if (entity == null) {
                createEntity.setCreateAt(update);
                createEntity.setUpdateAt(update);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                createEntity.setUpdateAt(update);
                OrmContext.getAccessor().update(createEntity);
            }
        }
    }
}
