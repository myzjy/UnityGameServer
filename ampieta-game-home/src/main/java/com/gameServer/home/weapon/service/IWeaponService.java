package com.gameServer.home.weapon.service;

import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
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
}
