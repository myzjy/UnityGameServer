package com.gameServer.gameBoot;

import com.gameServer.common.resource.ConfigResource;
import com.gameServer.common.resource.WeaponsDataConfigResource;
import com.gameServer.common.resource.WeaponsTypeResource;
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
    public void UpdateOrInWeaponsDataConfigResource(){
        if(weaponsDataConfigResourceStorages==null){
            throw new NullArgumentException("weaponsDataConfigResourceStorages 不存在");
        }
        if(weaponsDataConfigResourceStorages.size()<1){
            logger.error("武器属性 配置表 没有数据");
            return;
        }
        var list=weaponsDataConfigResourceStorages.getAll();

    }
}
