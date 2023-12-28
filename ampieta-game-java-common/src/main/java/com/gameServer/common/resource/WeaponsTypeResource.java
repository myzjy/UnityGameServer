package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * 武器 type 类型 对应 武器所属类型名
 * @author zjy
 * @version 1.0
 * @since 2023/12/28 23 51
 */
@Storage
public class WeaponsTypeResource {
    @Id
    @Index
    protected int id;
    //武器type
    protected int type;
    /**
     * 武器 type 对应 的名字
     */
    protected String weaponName;

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getWeaponName() {
        return weaponName;
    }
}
