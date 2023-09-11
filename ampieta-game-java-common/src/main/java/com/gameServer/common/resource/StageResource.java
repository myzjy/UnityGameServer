package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/6 17 48
 */
@Storage
public class StageResource{
    @Id
    @Index
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
    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public String getStandbyRole() {
        return standbyRole;
    }

    public int getLockMessage() {
        return lockMessage;
    }

    public String getMissionId() {
        return missionId;
    }
}
