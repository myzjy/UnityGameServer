package com.gameServer.commonRefush.protocol.user;

/**
 * 登录的时候会请求一边 相关数据，也有一定区别
 * 进入主界面之后 会请求刷新相关
 * 在GameMain 界面请求User 数据 Exp Lv 金币 钻石 付费钻石 角色数据
 * @author zjy
 * @version 1.0
 * @since 2023/7/14 16 21
 */
public class GameMainUserInfoToResponse {

    /**
     * 当前经验
     */
    private int exp;
    /**
     * 最大经验
     */
    private int maxExp;
    /**
     * 当前等级
     */
    private int lv;
    /**
     * 最大等级
     */
    private int maxLv;
    /**
     * 金币
     */
    private int goldCoinNum;
    /**
     * 钻石
     */
    private int diamondsNum;
    /**
     * 付费钻石
     */
    private int paidDiamondsNum;
    
}
