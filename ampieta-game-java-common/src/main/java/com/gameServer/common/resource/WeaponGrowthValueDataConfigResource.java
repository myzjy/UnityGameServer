package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Storage;

/**
 * 武器 升级经验
 */
@Storage
public class WeaponGrowthValueDataConfigResource {
    /**
     * id
     */
    @Id
    private int id;
    /**
     * 等级
     */
    private int lv;
    /**
     * 经验
     */
    private int exp;
    /**
     * 品质
     */
    private int quality;

    public int getId() {
        return id;
    }

    public int getLv() {
        return lv;
    }

    public int getExp() {
        return exp;
    }

    public int getQuality() {
        return quality;
    }
}
