package com.gameServer.commonRefush.protocol.user;

/**
 * 登录的时候会请求一边 相关数据，也有一定区别
 * 进入主界面之后 会请求刷新相关
 * 在GameMain 界面请求User 数据 Exp Lv 金币 钻石 付费钻石 角色数据
 *
 * @author zjy
 * @version 1.0
 * @since 2023/7/14 16 21
 */
public class GameMainUserInfoToResponse {

    /**
     * 当前经验
     */
    private int nowExp;
    /**
     * 最大经验
     */
    private int maxExp;
    /**
     * 当前等级
     */
    private int nowLv;
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

    public int getNowExp() {
        return nowExp;
    }

    public void setNowExp(int nowExp) {
        this.nowExp = nowExp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public int getNowLv() {
        return nowLv;
    }

    public void setNowLv(int nowLv) {
        this.nowLv = nowLv;
    }

    public int getMaxLv() {
        return maxLv;
    }

    public void setMaxLv(int maxLv) {
        this.maxLv = maxLv;
    }

    public int getGoldCoinNum() {
        return goldCoinNum;
    }

    public void setGoldCoinNum(int goldCoinNum) {
        this.goldCoinNum = goldCoinNum;
    }

    public int getDiamondsNum() {
        return diamondsNum;
    }

    public void setDiamondsNum(int diamondsNum) {
        this.diamondsNum = diamondsNum;
    }

    public int getPaidDiamondsNum() {
        return paidDiamondsNum;
    }

    public void setPaidDiamondsNum(int paidDiamondsNum) {
        this.paidDiamondsNum = paidDiamondsNum;
    }

    /**
     *  返回结构
     * @param nowLv 当前登录
     * @param maxLv 最大等级
     * @param nowExp 当前经验
     * @param maxExp 最大经验
     * @param goldCoinNum 当前金币
     * @param diamondsNum 钻石
     * @param paidDiamondsNum 付费钻石 
     * @return 返回 GameMainUserInfoToResponse
     */
    public static GameMainUserInfoToResponse ValueOf(int nowLv, int maxLv, int nowExp, int maxExp, int goldCoinNum, int diamondsNum, int paidDiamondsNum) {
        GameMainUserInfoToResponse data = new GameMainUserInfoToResponse();
        data.setNowLv(nowLv);
        data.setMaxLv(maxLv);
        data.setNowExp(nowExp);
        data.setMaxExp(maxExp);
        data.setGoldCoinNum(goldCoinNum);
        data.setDiamondsNum(diamondsNum);
        data.setPaidDiamondsNum(paidDiamondsNum);
        return data;
    }
}
