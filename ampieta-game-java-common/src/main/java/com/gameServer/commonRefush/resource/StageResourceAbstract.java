package com.gameServer.commonRefush.resource;

import com.zfoo.storage.model.anno.Id;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/6 17 52
 */
public abstract class StageResourceAbstract {
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
}
