package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.anno.Index;
import com.zfoo.orm.model.IEntity;

/**
 * 圣遗物 每个品阶 能够到达的最大等级
 *  分为几个阶段 这个阶段都是 获取副属性的零界点
 * @author zjy
 * @version 1.0
 * @since 2023/11/10 11 32
 */
public class EquipmentConfigDataEntity implements IEntity<Integer> {
    @Id
    @Index(ascending = false, unique = false)
    private int id;
    private int quality;
    /**
     * 1阶段
     */
    private int lv1;
    /**
     * 2阶段
     */
    private int lv2;
    /**
     * 3阶段
     */
    private int lv3;
    /**
     * 4阶段
     */
    private int lv4;
    /**
     * 5阶段
     */
    private int lv5;
    /**
     * 当前数据创建时间
     */
    private String createAt;
    /**
     * 当前数据更新时间
     */
    private String updateAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getLv1() {
        return lv1;
    }

    public void setLv1(int lv1) {
        this.lv1 = lv1;
    }

    public int getLv2() {
        return lv2;
    }

    public void setLv2(int lv2) {
        this.lv2 = lv2;
    }

    public int getLv3() {
        return lv3;
    }

    public void setLv3(int lv3) {
        this.lv3 = lv3;
    }

    public int getLv4() {
        return lv4;
    }

    public void setLv4(int lv4) {
        this.lv4 = lv4;
    }

    public int getLv5() {
        return lv5;
    }

    public void setLv5(int lv5) {
        this.lv5 = lv5;
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

    @Override
    public Integer id() {
        return id;
    }

    public static EquipmentConfigDataEntity valueOf() {
        return new EquipmentConfigDataEntity();
    }
}
