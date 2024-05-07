package com.gameServer.home.weapon.service;

import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
import com.gameServer.common.protocol.weapon.WeaponsConfigData;
import com.zfoo.orm.OrmContext;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/14 15 38
 */
@Component
public class WeaponService implements IWeaponService {
    @Override
    public WeaponsConfigData CreateWeaponConfigData(WeaponsDataConfigEntity entity) {
        WeaponsConfigData data = new WeaponsConfigData();
        data.setId(entity.getId());
        data.setWeaponName(entity.getWeaponName());
        data.setWeaponType(entity.getWeaponType());
        //data.setWeaponBreakthrough(entity.getWeaponBreakthrough());
        data.setWeaponSkills(entity.getWeaponSkills());
        data.setIconResource(entity.getIconResource());
        data.setMaxLv(entity.getMaxLv());
        data.setWeaponInitValue(entity.getWeaponInitValue());
        return data;
    }

    @Override
    public WeaponsDataConfigEntity FindWeaponsConfigData(int id) {
        return OrmContext.getAccessor().load(id, WeaponsDataConfigEntity.class);
    }
}
