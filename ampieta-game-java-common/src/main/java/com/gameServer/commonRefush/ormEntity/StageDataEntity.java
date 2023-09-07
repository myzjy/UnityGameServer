package com.gameServer.commonRefush.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

/**
 * Stage 数据库相关 基础 不是 User相关
 * @author zjy
 * @version 1.0
 * @since 2023/8/6 23 58
 */
public class StageDataEntity implements IEntity<Integer> {

    @Id
    protected int id;
    /**
     * 排序
     */
    protected int order;
    /**
     * 配置器id 读取关卡配置相关
     */
    protected int puzzleId;
    /**
     * 关卡点击上去之后界面会显示的角色
     */
    protected String standbyRole;
    /**
     * 解锁信息
     */
    protected int lockMessage;
    /**
     * 任务ID集合
     */
    protected String missionId;
    /**
     * 创建时间
     */
    protected String createAt;
    /**
     * 更新时间
     */
    protected String updatedAt;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getStandbyRole() {
        return standbyRole;
    }

    public void setStandbyRole(String standbyRole) {
        this.standbyRole = standbyRole;
    }

    public int getLockMessage() {
        return lockMessage;
    }

    public void setLockMessage(int lockMessage) {
        this.lockMessage = lockMessage;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public static  StageDataEntity ValueOf(){
        var entity=new StageDataEntity();
        return entity;
    }
}
