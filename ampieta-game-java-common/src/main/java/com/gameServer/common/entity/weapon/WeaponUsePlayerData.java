package com.gameServer.common.entity.weapon;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/13 13 27
 */
public class WeaponUsePlayerData implements IEntity<Long> {
    /**
     * 下标
     */
    @Id
    private long id;

    @Override
    public Long id() {
        return id;
    }

    /**
     * 当前装备所属玩家
     */
    private long userUid;
    /**
     * 武器id
     */
    private int weaponId;
}
