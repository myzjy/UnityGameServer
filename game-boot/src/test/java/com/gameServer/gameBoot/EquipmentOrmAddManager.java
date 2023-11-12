package com.gameServer.gameBoot;

import com.gameServer.common.ormEntity.*;
import com.gameServer.common.resource.*;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.StringUtils;
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
    @StorageAutowired
    private StorageInt<Integer, EquipmentResource> equipmentResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, EquipmentConfigResource> equipmentConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, EquipmentDesResource> equipmentDesResourceStorageInt;

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
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    entity.setCreateAt(update);
                } else {
                    entity.setCreateAt(entity.getCreateAt());
                }
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
            createOrUpdateEntity.setUpdateAt(update);
            if (entity == null) {
                //创建
                createOrUpdateEntity.setCreateAt(update);
                OrmContext.getAccessor().insert(createOrUpdateEntity);
            } else {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    createOrUpdateEntity.setCreateAt(update);
                } else {
                    createOrUpdateEntity.setCreateAt(entity.getCreateAt());
                }
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
            ;
            createEntity.setPrimaryGrowthMaxInt(StringUtils.format("{}", data.getPrimaryGrowthMaxInt()));
            createEntity.setPrimaryGrowthInts(StringUtils.format("{}", data.getPrimaryGrowthInts()));
            createEntity.setUpdateAt(update);
            if (entity == null) {
                createEntity.setCreateAt(update);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    createEntity.setCreateAt(update);
                } else {
                    createEntity.setCreateAt(entity.getCreateAt());
                }
                OrmContext.getAccessor().update(createEntity);
            }
        }
    }

    public void UpdateEquipmentResource() {
        var list = equipmentResourceStorageInt.getAll();
        for (var data : list) {
            var entity = OrmContext.getAccessor().load(data.getId(), EquipmentBaseDataEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            var createEntity = EquipmentBaseDataEntity.valueOf();
            createEntity.setId(data.getId());
            createEntity.setEquipmentName(data.getEquipmentName());
            createEntity.setEquipmentPosType(data.getEquipmentPosType());
            createEntity.setQuality(data.getQuality());
            createEntity.setDesId(data.getDesId());
            createEntity.setMainAttributes(data.getMainAttributes());
            createEntity.setUpdateAt(update);
            if (entity == null) {
                createEntity.setCreateAt(update);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    createEntity.setCreateAt(update);
                } else {
                    createEntity.setCreateAt(entity.getCreateAt());
                }
                OrmContext.getAccessor().update(createEntity);
            }
        }
    }

    public void UpdateEquipmentConfigResource() {
        var dict = equipmentConfigResourceStorageInt.getAll();
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getQuality(), EquipmentConfigDataEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            var createEntity = EquipmentConfigDataEntity.valueOf();
            createEntity.setId(data.getQuality());
            createEntity.setQuality(data.getQuality());
            createEntity.setLv1(data.getLv1());
            createEntity.setLv2(data.getLv2());
            createEntity.setLv3(data.getLv3());
            createEntity.setLv4(data.getLv4());
            createEntity.setLv5(data.getLv5());
            createEntity.setUpdateAt(update);
            if (entity == null) {
                createEntity.setCreateAt(update);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    createEntity.setCreateAt(update);
                } else {
                    createEntity.setCreateAt(entity.getCreateAt());
                }
                OrmContext.getAccessor().update(createEntity);
            }
        }
    }

    public void UpdateEquipmentDesResource() {
        var dict = equipmentDesResourceStorageInt.getAll();
        for (var data : dict) {
            var entity = OrmContext.getAccessor().load(data.getDesId(), EquipmentDesBaseDataEntity.class);
            var update = TimeUtils.timeToString(TimeUtils.now());
            var createEntity = EquipmentDesBaseDataEntity.valueOf();
            createEntity.setId(data.getDesId());
            createEntity.setName(data.getName());
            createEntity.setUpdateAt(update);
            createEntity.setDesId(data.getDesId());
            createEntity.setDesStr(data.getDesStr());
            createEntity.setStoryDesStr(data.getStoryDesStr());
            if (entity == null) {
                createEntity.setCreateAt(update);
                OrmContext.getAccessor().insert(createEntity);
            } else {
                if (StringUtils.isEmpty(entity.getCreateAt())) {
                    createEntity.setCreateAt(update);
                } else {
                    createEntity.setCreateAt(entity.getCreateAt());
                }
                OrmContext.getAccessor().update(createEntity);
            }
        }
    }
}
