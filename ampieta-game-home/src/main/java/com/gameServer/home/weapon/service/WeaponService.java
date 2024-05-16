package com.gameServer.home.weapon.service;

import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
import com.gameServer.common.protocol.weapon.WeaponPlayerUserDataStruct;
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

    @Override
    public WeaponPlayerUserDataStruct getWeaponPlayerUserDataStruct(WeaponUsePlayerDataEntity item, WeaponsDataConfigEntity findWeaponConfig) {
        var data = WeaponPlayerUserDataStruct.ValueOf();
        data.setId(item.getId());
        data.setWeaponIcons(findWeaponConfig.getIconResource());
        data.setHaveTimeAt(item.getCreateAt());
        data.setWeaponName(item.getWeaponName());
        data.setWeaponType(findWeaponConfig.getWeaponType());
        data.setNowLv(item.getLv());
        data.setWeaponId(findWeaponConfig.getId());
        data.setNowMaxLv(item.getNowMaxLv());
        data.setNowLvExp(item.getNowLvExp());
        data.setNowSkills(item.getWeaponsSkill());
        data.setWeaponMainValue(item.getWeaponValue());
        data.setWeaponMainValueType(findWeaponConfig.getWeaponMainInitType());
        data.setWeaponModelNameIcons(findWeaponConfig.getIconResource());
        data.setBagWeaponIcon(findWeaponConfig.getBagIconResource());
        data.setNowLvMaxExp(item.getNowLvMaxExp());
        return data;
    }
}
