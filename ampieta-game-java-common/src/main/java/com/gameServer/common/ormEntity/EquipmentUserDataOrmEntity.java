package com.gameServer.common.ormEntity;

import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/26 17 22
 */
public class EquipmentUserDataOrmEntity implements IEntity<Long> {
    /**
     * 所属玩家 UID
     */
    private long Uid;
    /**
     * id
     */
    private long id;
    /**
     * 圣遗物所属 id
     */
    private int equipmentID;

    @Override
    public long id() {
        return id;
    }

    public long getUid() {
        return Uid;
    }

    public void setUid(long uid) {
        Uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(int equipmentID) {
        this.equipmentID = equipmentID;
    }
}
