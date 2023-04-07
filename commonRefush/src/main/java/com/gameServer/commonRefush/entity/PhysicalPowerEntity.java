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
     * <p> 注意这里不是时间戳赋值</p>
     */
    private int residueTime;

    /**
     * 当前体力实时时间 会跟着剩余时间一起变化
     */
    private long residueNowTime;
    /**
     * 一点体力增长结束时间
     */
    private long residueEndTime;
    /**
     * 最大体力 用于限制 这个值会随着 等级增长
     */
    private int maximumStrength;
    /**
     * 我恢复到最大体力的结束时间
     * <p>这里不是时间戳<p/>
     */
    private int maximusResidueEndTime;

    /**
     * 我恢复到最大体力的结束时间 此为时间戳
     */
    private long maxResidueEndTime;

    /**
     * 创建值
     *
     * @param id                  uid
     * @param residueEndTime      一点体力增长结束时间
     * @param nowPhysicalPowerNum 当前体力 这个地方是需要同步PlayUser 表中的体力值，哪里一概不进行增加，只用同步
     * @param maximumStrength     最大体力 用于限制 这个值会随着 等级增长
     * @param residueTime         一点体力增长剩余时间
     * @param maxResidueEndTime   恢复满体力的结束时间
     */
    public static PhysicalPowerEntity ValueOf(long id,
                                              long residueEndTime,
                                              int nowPhysicalPowerNum,
                                              int residueTime,
                                              int maximumStrength,
                                              long maxResidueEndTime) {
        var data = new PhysicalPowerEntity();
        data.setId(id);
        data.setResidueEndTime(residueEndTime);
        data.setNowPhysicalPowerNum(nowPhysicalPowerNum);
        data.setMaximumStrength(maximumStrength);
        data.setResidueTime(residueTime);
        data.setMaxResidueEndTime(maxResidueEndTime);
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

    /**
     * @return 返回剩余恢复1点体力时间 这里不是时间戳
     */
    public int getResidueTime() {
        return residueTime;
    }

    /**
     * 设置剩余时间
     *
     * @param residueTime 设置剩余时间
     */
    public void setResidueTime(int residueTime) {
        this.residueTime = residueTime;
    }

    /**
     * 返回当前体力值
     *
     * @return 返回当前体力值
     */
    public int getNowPhysicalPowerNum() {
        return nowPhysicalPowerNum;
    }

    /**
     * 设置体力值
     *
     * @param nowPhysicalPowerNum 需要进行设置的体力值
     */
    public void setNowPhysicalPowerNum(int nowPhysicalPowerNum) {
        this.nowPhysicalPowerNum = nowPhysicalPowerNum;
    }

    public long getVs() {
        return vs;
    }

    public void setVs(long vs) {
        this.vs = vs;
    }

    /**
     * 返回id
     *
     * @return 返回🆔
     */
    public long getId() {
        return id;
    }

    /**
     * 设置id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 返回体力满 结束时间
     *
     * @return 返回结束时间
     */
    public long getMaxResidueEndTime() {
        return maxResidueEndTime;
    }

    /**
     * 进行设置恢复最大体力的结束时见，只会在我体力满的时候、消耗体力了才会进行赋值
     *
     * @param maxResidueEndTime 结束时间
     */
    public void setMaxResidueEndTime(long maxResidueEndTime) {
        this.maxResidueEndTime = maxResidueEndTime;
    }

    /**
     * @return 返回当前体力实时恢复时间
     */
    public long getResidueNowTime() {
        return residueNowTime;
    }

    /**
     * @param residueNowTime 当前体力恢复实时计算 时间戳
     */
    public void setResidueNowTime(long residueNowTime) {
        this.residueNowTime = residueNowTime;
    }

    public int getMaximusResidueEndTime() {
        return maximusResidueEndTime;
    }

    public void setMaximusResidueEndTime(int maximusResidueEndTime) {
        this.maximusResidueEndTime = maximusResidueEndTime;
    }


}
