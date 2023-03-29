package com.gameServer.commonRefush.entity;

import com.zfoo.net.session.Session;
import com.zfoo.orm.model.anno.Cache;
import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.anno.Persister;
import com.zfoo.orm.model.entity.IEntity;

/**
 * 体力缓存
 * <p>
 * 记录当前1点 增长剩余时间
 * 所有体力增长结束时间
 * 所有体力增长满 的所需时间
 *
 * @author zjy
 * @version 1.0
 * @since 2023/3/29 00 30
 */
@EntityCache(persister = @Persister("time30s"))
public class PhysicalPowerEntity implements IEntity<Long> {
    public transient long sid = 1;
    public transient Session session = null;
    @Id
    private long id;
    private long vs;
    /**
     * 当前体力 这个地方是需要同步PlayUser 表中的体力值，哪里一概不进行增加，只用同步
     */
    private int nowPhysicalPowerNum;
    /**
     * 一点体力增长剩余时间
     */
    private int residueTime;
    /**
     * 一点体力增长结束时间
     */
    private long residueEndTime;
    /**
     * 最大体力 用于限制 这个值会随着 等级增长
     */
    private int maximumStrength;

    /**
     * 创建值
     *
     * @param id                  uid
     * @param residueEndTime      一点体力增长结束时间
     * @param nowPhysicalPowerNum 当前体力 这个地方是需要同步PlayUser 表中的体力值，哪里一概不进行增加，只用同步
     * @param maximumStrength     最大体力 用于限制 这个值会随着 等级增长
     * @param residueTime         一点体力增长剩余时间
     */
    public static PhysicalPowerEntity ValueOf(int id,
                                              long residueEndTime,
                                              int nowPhysicalPowerNum,
                                              int residueTime,
                                              int maximumStrength) {
        var data = new PhysicalPowerEntity();
        data.setId(id);
        data.setResidueEndTime(residueEndTime);
        data.setNowPhysicalPowerNum(nowPhysicalPowerNum);
        data.setMaximumStrength(maximumStrength);
        data.setResidueTime(residueTime);
        return data;
    }


    @Override
    public Long id() {
        return id;
    }

    public long getResidueEndTime() {
        return residueEndTime;
    }

    public void setResidueEndTime(long residueEndTime) {
        this.residueEndTime = residueEndTime;
    }

    public int getMaximumStrength() {
        return maximumStrength;
    }

    public void setMaximumStrength(int maximumStrength) {
        this.maximumStrength = maximumStrength;
    }

    public int getResidueTime() {
        return residueTime;
    }

    public void setResidueTime(int residueTime) {
        this.residueTime = residueTime;
    }

    public int getNowPhysicalPowerNum() {
        return nowPhysicalPowerNum;
    }

    public void setNowPhysicalPowerNum(int nowPhysicalPowerNum) {
        this.nowPhysicalPowerNum = nowPhysicalPowerNum;
    }

    public long getVs() {
        return vs;
    }

    public void setVs(long vs) {
        this.vs = vs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
