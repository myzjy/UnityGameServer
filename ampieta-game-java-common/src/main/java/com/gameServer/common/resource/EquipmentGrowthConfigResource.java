package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/3 17 20
 */
@Storage
public class EquipmentGrowthConfigResource {
    @Id
    private int id;
    /**
     * 圣遗物位置
     */
    private int locationOfEquipmentType;
    /**
     * 位置的名字
     */
    private String posName;

    public int getId() {
        return id;
    }

    public int getLocationOfEquipmentType() {
        return locationOfEquipmentType;
    }

    public String getPosName() {
        return posName;
    }
}
