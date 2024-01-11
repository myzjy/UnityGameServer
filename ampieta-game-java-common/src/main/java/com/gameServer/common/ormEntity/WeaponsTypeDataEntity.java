package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/2 23 56
 */
public class WeaponsTypeDataEntity implements IEntity<Integer> {
    @Id
    protected int id;

    @Override
    public Integer id() {
        return id;
    }

    /**
     *武器类型num
     */
    private int type;
    /**
     * 武器类型名
     */
    private String weaponName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
}
