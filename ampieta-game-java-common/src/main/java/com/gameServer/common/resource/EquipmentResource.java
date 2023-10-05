package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/3 00 56
 */
@Storage
public class EquipmentResource {
    /**
     * 圣遗物id
     */
    @Id
    private int id;
    /**
     * 圣遗物介绍id
     */
    private int desId;

    public int getId() {
        return id;
    }

    public int getDesId() {
        return desId;
    }
}
