package com.gameServer.home.equipment.service;

import com.gameServer.common.ormEntity.*;
import com.gameServer.common.protocol.equipment.base.*;
import com.gameServer.common.resource.EquipmentConfigResource;
import com.gameServer.common.resource.EquipmentResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/15 14 28
 */
@Component
public class EquipmentService implements IEquipmentService {
    /**
     * 比较 NowPlayerUserID 将 以装备 圣遗物 往前 排列
     */
    Comparator<EquipmentUserDataOrmEntity> playerUserIdComparator = new Comparator<EquipmentUserDataOrmEntity>() {
        @Override
        public int compare(EquipmentUserDataOrmEntity o1, EquipmentUserDataOrmEntity o2) {
            if (o1.getNowPlayerUserId() > o2.getNowPlayerUserId()) {
                return 1;
            } else if (o1.getNowPlayerUserId() == o2.getNowPlayerUserId()) {
                return 0;
            }
            return -1;
        }
    };
    @StorageAutowired
    private StorageInt<Integer, EquipmentConfigResource> equipmentConfigResourceStorageInt;
    @StorageAutowired
    private StorageInt<Integer, EquipmentResource> equipmentResourceStorageInt;

    @Override
    public List<EquipmentUserDataOrmEntity> GetAllTheUserToEquipmentUserDataOrm(long uid) {
        // 查找到 传递 UID 的玩家 所有 圣遗物装备
        var list = OrmContext.getQuery(EquipmentUserDataOrmEntity.class)
                             .eq("Uid", uid).queryAll();
        // 排序
        list.sort(playerUserIdComparator);
        return list;
    }

    @Override
    public EquipmentConfigDataEntity FindQualityGetEquipmentConfigData(int quality) {
        return OrmContext.getAccessor().load(quality, EquipmentConfigDataEntity.class);
    }

    @Override
    public EquipmentBaseData CreateEquipmentBaseData(EquipmentBaseDataEntity entity) {
        var data = EquipmentBaseData.valueOf();
        data.setDesId(entity.getDesId());
        data.setEquipmentName(entity.getEquipmentName());
        data.setEquipmentPosType(entity.getEquipmentPosType());
        data.setQuality(entity.getQuality());
        data.setMainAttributes(entity.getMainAttributes());
        return data;
    }

    @Override
    public EquipmentDesBaseData CreateEquipmentDesBaseData(EquipmentDesBaseDataEntity entity) {
        var data = EquipmentDesBaseData.valueOf();
        data.setDesStr(entity.getDesStr());
        data.setStoryDesStr(data.getStoryDesStr());
        data.setName(data.getName());
        data.setDesId(data.getDesId());
        return data;
    }

    @Override
    public EquipmentPrimaryConfigBaseData CreateEquipmentPrimaryConfigBaseData(EquipmentPrimaryConfigDataEntity entity) {
        var createData = EquipmentPrimaryConfigBaseData.valueOf();
        createData.setId(entity.getId());
        createData.setGrowthPosInt(entity.getGrowthPosInt());
        createData.setGrowthPosName(entity.getGrowthPosName());
        createData.setPrimaryQuality(entity.getPrimaryQuality());
        createData.setPrimaryGrowthInts(entity.getPrimaryGrowthInts());
        return createData;
    }

    @Override
    public EquipmentGrowthConfigBaseData CreateEquipmentGrowthConfigBaseData(EquipmentGrowthConfigEntity entity) {
        var createData = EquipmentGrowthConfigBaseData.valueOf();
        createData.setId(entity.getId());
        createData.setPosName(entity.getPosName());
        createData.setLocationOfEquipmentType(entity.getLocationOfEquipmentType());
        return createData;
    }

    @Override
    public EquipmentGrowthViceConfigBaseData CreateEquipmentGrowthViceConfigBaseData(EquipmentGrowthViceConfigDataEntity entity) {
        var createData = EquipmentGrowthViceConfigBaseData.valueOf();
        createData.setInitNums(entity.getInitNums());
        createData.setViceId(entity.getViceId());
        createData.setPosGrowthType(entity.getPosGrowthType());
        createData.setViceName(entity.getViceName());
        return createData;
    }

    @Override
    public EquipmentConfigBaseData CreateEquipmentConfigBaseData(EquipmentConfigDataEntity entity) {
        var createData = EquipmentConfigBaseData.valueOf();
        createData.setLv1(entity.getLv1());
        createData.setLv2(entity.getLv2());
        createData.setLv3(entity.getLv3());
        createData.setLv4(entity.getLv4());
        createData.setQuality(entity.getQuality());
        return createData;
    }
}
