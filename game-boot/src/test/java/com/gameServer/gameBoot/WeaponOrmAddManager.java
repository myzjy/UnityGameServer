package com.gameServer.gameBoot;

import com.gameServer.common.entity.composite.WeaponBreakthroughCompositeData;
import com.gameServer.common.entity.composite.WeaponProgressDataOrmEntity;
import com.gameServer.common.ormEntity.WeaponGrowthValueDataConfigOrmEntity;
import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
import com.gameServer.common.resource.ConfigResource;
import com.gameServer.common.resource.WeaponGrowthValueDataConfigResource;
import com.gameServer.common.resource.WeaponsDataConfigResource;
import com.gameServer.common.resource.WeaponsTypeResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/11 23 29
 */
@Component
public class WeaponOrmAddManager {
    private static final Logger logger = LoggerFactory.getLogger(WeaponOrmAddManager.class);
    @StorageAutowired
    private StorageInt<Integer, WeaponsDataConfigResource> weaponsDataConfigResourceStorages;
    @StorageAutowired
    private StorageInt<Integer, WeaponsTypeResource> weaponsTypeResourceStorages;
    @StorageAutowired
    private StorageInt<Integer, WeaponGrowthValueDataConfigResource> weaponGrowthValueDataConfigResourceStorageInt;

    public void UpdateOrInWeaponsDataConfigResource() {
        if (weaponsDataConfigResourceStorages == null) {
            throw new NullArgumentException("weaponsDataConfigResourceStorages 不存在");
        }
        if (weaponsDataConfigResourceStorages.size() < 1) {
            logger.error("武器属性 配置表 没有数据");
            return;
        }
        var list = weaponsDataConfigResourceStorages.getAll();
        for (var item : list) {
            //数据库中查找的
            var ormData = OrmContext.getAccessor().load(item.getId(), WeaponsDataConfigEntity.class);
            var newEntity = WeaponsDataConfigEntity.valueOf();
            var createOrUpdate = TimeUtils.timeToString(TimeUtils.now());
            List<WeaponBreakthroughCompositeData> weaponBreakthroughList = getWeaponBreakthroughCompositeData(item);
            List<WeaponProgressDataOrmEntity> weaponProgressDataList = getWeaponProgressDataOrmEntities(item);
            if (ormData == null) {
                newEntity.setId(item.getId());
                newEntity.setMaxLv(item.getMaxLv());
                newEntity.setIconResource(item.getIconResource());
                newEntity.setWeaponBreakthrough(weaponBreakthroughList);
                newEntity.setWeaponName(item.getWeaponName());
                newEntity.setWeaponType(item.getWeaponType());
                newEntity.setWeaponSkills(item.getWeaponSkills());
                newEntity.setWeaponInitProgress(item.getWeaponInitProgress());
                newEntity.setWeaponInitValue(item.getWeaponInitValue());
                newEntity.setWeaponMainInitType(item.getWeaponMainInitType());
                newEntity.setWeaponReinforcementEqualOrder(item.getReinforcementEqualOrder());
                newEntity.setWeaponQuality(item.getWeaponQuality());
                newEntity.setWeaponOrderNum(item.getOrderNum());
                newEntity.setCreateAt(createOrUpdate);
                newEntity.setUpdateAt(createOrUpdate);
                newEntity.setBagIconResource(item.getBagIconResource());
                newEntity.setWeaponProgressData(weaponProgressDataList);
                OrmContext.getAccessor().insert(newEntity);
            } else {
                //覆盖掉
                ormData.setId(item.getId());
                ormData.setMaxLv(item.getMaxLv());
                ormData.setIconResource(item.getIconResource());
                ormData.setWeaponBreakthrough(weaponBreakthroughList);
                ormData.setWeaponName(item.getWeaponName());
                ormData.setWeaponType(item.getWeaponType());
                ormData.setWeaponSkills(item.getWeaponSkills());
                ormData.setWeaponInitProgress(item.getWeaponInitProgress());
                ormData.setWeaponInitValue(item.getWeaponInitValue());
                ormData.setWeaponQuality(item.getWeaponQuality());
                if (StringUtils.isEmpty(ormData.getCreateAt())) {
                    ormData.setCreateAt(createOrUpdate);
                } else {
                    ormData.setCreateAt(ormData.getCreateAt());
                }
                ormData.setWeaponMainInitType(item.getWeaponMainInitType());
                ormData.setWeaponReinforcementEqualOrder(item.getReinforcementEqualOrder());
                ormData.setWeaponOrderNum(item.getOrderNum());
                ormData.setBagIconResource(item.getBagIconResource());
                ormData.setUpdateAt(createOrUpdate);
                ormData.setWeaponProgressData(weaponProgressDataList);
                OrmContext.getAccessor().update(ormData);
            }
        }
        logger.info("插入 WeaponsDataConfigEntity list 数据完成");
    }

    private List<WeaponProgressDataOrmEntity> getWeaponProgressDataOrmEntities(WeaponsDataConfigResource item) {
        List<WeaponProgressDataOrmEntity> weaponProgressDataList = new ArrayList<>();
        String[] split = item.getWeaponProgress().split("\\|");
        for (String s : split) {
            String[] split1 = s.split("\\+");
            WeaponProgressDataOrmEntity entity = WeaponProgressDataOrmEntity.valueOf();
            int lv = Integer.parseInt(split1[0]);
            entity.setWeaponLv(lv);
            entity.setWeaponAddNum(split1[1]);
            weaponProgressDataList.add(entity);
        }
        return weaponProgressDataList;
    }

    private List<WeaponBreakthroughCompositeData> getWeaponBreakthroughCompositeData(WeaponsDataConfigResource item) {
        List<WeaponBreakthroughCompositeData> weaponBreakthroughList = new ArrayList<>();
        String[] split = item.getWeaponBreakthrough().split("\\|");
        for (String str : split) {
            var weaponBreakthroughCompositeData = WeaponBreakthroughCompositeData.valueOf();
            String[] split1 = str.split("\\+");
            int lv = Integer.parseInt(split1[0]);
            int num = Integer.parseInt(split1[1]);
            weaponBreakthroughCompositeData.setWeaponLv(lv);
            weaponBreakthroughCompositeData.setWeaponAddNum(num);
            weaponBreakthroughList.add(weaponBreakthroughCompositeData);
        }
        return weaponBreakthroughList;
    }

    public void UpdateOrInWeaponGrowthValueDataConfigResource() {
        if (weaponGrowthValueDataConfigResourceStorageInt == null) {
            throw new NullArgumentException("weaponGrowthValueDataConfigResourceStorageInt 不存在");
        }
        if (weaponGrowthValueDataConfigResourceStorageInt.size() < 1) {
            logger.error("武器 升级经验 配置表 没有数据");
            return;
        }
        var list = weaponGrowthValueDataConfigResourceStorageInt.getAll();
        for (var item : list) {
            //数据库中查找的
            var ormData = OrmContext.getAccessor().load(item.getId(), WeaponGrowthValueDataConfigOrmEntity.class);
            var newEntity = WeaponGrowthValueDataConfigOrmEntity.valueOf();
            var createOrUpdate = TimeUtils.timeToString(TimeUtils.now());
            if (ormData == null) {
                newEntity.setId(item.getId());
                newEntity.setLv(item.getLv());
                newEntity.setExp(item.getExp());
                newEntity.setQuality(item.getQuality());
                newEntity.setCreateAt(createOrUpdate);
                newEntity.setUpdateAt(createOrUpdate);
                OrmContext.getAccessor().insert(newEntity);
            } else {
                //覆盖掉
                ormData.setId(item.getId());
                ormData.setLv(item.getLv());
                ormData.setExp(item.getExp());
                ormData.setQuality(item.getQuality());
                ormData.setCreateAt(ormData.getCreateAt());
                ormData.setUpdateAt(createOrUpdate);
                OrmContext.getAccessor().update(ormData);
            }
        }
        logger.info("插入 或者 更新 weaponGrowthValueDataConfigEntity list 数据完成");
    }
}
