package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/2 23 56
 */
public class WeaponDataConfigEntity implements IEntity<Integer> {
    @Id
    protected int id;

    @Override
    public Integer id() {
        return id;
    }
}
