package com.gameServer.common.entity.config;

import com.zfoo.orm.anno.EntityCache;
import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/30 14 26
 */
@EntityCache
public class ConfigResourceEntity implements IEntity<Integer> {
    @Id
    private int id;
    private int lv;
    /**
     * 最大体力
     */
    private int maxPhysical;
    /**
     * 恢复时间
     */
    private int residueTime;
    /**
     * 最大经验
     */
    private int maxExp;
    private boolean theLock;
    private String createAt;
    protected String updateAt;

    public static ConfigResourceEntity ValueOf() {
        return new ConfigResourceEntity();
    }

    /**
     * @param lv
     * @param maxPhysical
     * @param maxExp
     * @param residueTime
     * @param isTheLock
     * @return
     */
    public static ConfigResourceEntity ValueOf(int lv, int maxPhysical, int maxExp, int residueTime, boolean isTheLock) {
        var data = new ConfigResourceEntity();
        data.setId(lv);
        data.setLv(lv);
        data.setMaxPhysical(maxPhysical);
        data.setMaxExp(maxExp);
        data.setResidueTime(residueTime);
        data.setTheLock(isTheLock);
        return data;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getMaxPhysical() {
        return maxPhysical;
    }

    public void setMaxPhysical(int maxPhysical) {
        this.maxPhysical = maxPhysical;
    }

    public int getResidueTime() {
        return residueTime;
    }

    public void setResidueTime(int residueTime) {
        this.residueTime = residueTime;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public boolean getTheLock() {
        return theLock;
    }

    public void setTheLock(boolean theLock) {
        theLock = theLock;
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
