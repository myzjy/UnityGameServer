package com.gameServer.home.weapon.service;

import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
import com.gameServer.common.protocol.weapon.WeaponPlayerUserDataStruct;
import com.gameServer.common.protocol.weapon.WeaponsConfigData;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/14 15 38
 */
public interface IWeaponService {
    /**
     * 创建 client 所需要 WeaponConfigData
     *
     * @param entity 数据库中存放
     * @return 返回 创建出来的 WeaponsConfigDate类
     */
    WeaponsConfigData CreateWeaponConfigData(WeaponsDataConfigEntity entity);

    WeaponsDataConfigEntity FindWeaponsConfigData(int id);

    /**
     * 创建 武器数据
     *
     * @param item             玩家武器数据相关
     * @param findWeaponConfig 武器基础配置
     * @return 返回 client 需要 武器数据
     */
    WeaponPlayerUserDataStruct getWeaponPlayerUserDataStruct(WeaponUsePlayerDataEntity item, WeaponsDataConfigEntity findWeaponConfig);
}
