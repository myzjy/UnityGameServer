package com.gameServer.singleServer.PhysicalPower.service;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.zfoo.orm.OrmContext;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/16 00 41
 */
@Component
public class PhysicalPowerService implements IPhysicalPowerService {
    @Override
    public PhysicalPowerEntity FindOnePhysicalPower(long uid) {
        var data = OrmContext.getAccessor().load(uid, PhysicalPowerEntity.class);
        return data;
    }

}
