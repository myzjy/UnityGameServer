package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

public class WeaponGrowthValueDataConfigOrmEntity implements IEntity<Integer> {
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
    /**
     * 创建时间
     */
    private String createAt;
    /**
     * 更新时间
     */
    private String updateAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public static WeaponGrowthValueDataConfigOrmEntity valueOf() {
        return new WeaponGrowthValueDataConfigOrmEntity();
    }

    public static WeaponGrowthValueDataConfigOrmEntity valueOf(int id, int lv, int exp, int quality, String createAt, String updateAt) {
        var data = new WeaponGrowthValueDataConfigOrmEntity();
        data.id = id;
        data.lv = lv;
        data.exp = exp;
        data.quality = quality;
        data.createAt = createAt;
        data.updateAt = updateAt;
        return data;
    }

    @Override
    public Integer id() {
        return id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
