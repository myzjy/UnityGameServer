package com.gameServer.gameBoot;

import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
import com.gameServer.common.resource.ConfigResource;
import com.gameServer.common.resource.WeaponsDataConfigResource;
import com.gameServer.common.resource.WeaponsTypeResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
            if (ormData == null) {
                newEntity.setId(item.getId());
                newEntity.setMaxLv(item.getMaxLv());
                newEntity.setIconResource(item.getIconResource());
                newEntity.setWeaponBreakthrough(item.getWeaponBreakthrough());
                newEntity.setWeaponName(item.getWeaponName());
                newEntity.setWeaponType(item.getWeaponType());
                newEntity.setWeaponSkills(item.getWeaponSkills());
                newEntity.setWeaponInitProgress(item.getWeaponInitProgress());
                newEntity.setWeaponInitValue(item.getWeaponInitValue());
                newEntity.setCreateAt(createOrUpdate);
                newEntity.setUpdateAt(createOrUpdate);
                OrmContext.getAccessor().insert(newEntity);
            } else {
                //覆盖掉
                ormData.setId(item.getId());
                ormData.setMaxLv(item.getMaxLv());
                ormData.setIconResource(item.getIconResource());
                ormData.setWeaponBreakthrough(item.getWeaponBreakthrough());
                ormData.setWeaponName(item.getWeaponName());
                ormData.setWeaponType(item.getWeaponType());
                ormData.setWeaponSkills(item.getWeaponSkills());
                ormData.setWeaponInitProgress(item.getWeaponInitProgress());
                ormData.setWeaponInitValue(item.getWeaponInitValue());
                ormData.setCreateAt(newEntity.getCreateAt());
                ormData.setUpdateAt(createOrUpdate);
                OrmContext.getAccessor().update(ormData);
            }
        }
        logger.info("插入 WeaponsDataConfigEntity list 数据完成");
    }
}
