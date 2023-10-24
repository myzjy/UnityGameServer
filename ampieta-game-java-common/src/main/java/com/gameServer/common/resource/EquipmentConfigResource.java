package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/15 17 07
 */
@Storage
public class EquipmentConfigResource {
    @Id
    private int quality;
    private int lv1;
    private int lv2;
    private int lv3;
    private int lv4;
    private int lv5;

    public int getQuality() {
        return quality;
    }

    public int getLv1() {
        return lv1;
    }

    public int getLv2() {
        return lv2;
    }

    public int getLv3() {
        return lv3;
    }

    public int getLv4() {
        return lv4;
    }

    public int getLv5() {
        return lv5;
    }
}
