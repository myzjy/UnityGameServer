package com.gameServer.common.protocol.playerUser;

import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/17 23 40
 */
@Protocol(id = 222)
public class UserMsgInfoData {
    private String userName;
    /**
     * 金币
     */
    private long goldNum;
    /**
     * 付费钻石 一般充值才有，付费钻石转换成普通钻石
     */
    private long premiumDiamondNum;
    /**
     * 普通钻石 由付费钻石转换成普通钻石，比例为 1:1
     */
    private long diamondNum;
    /**
     * 登记
     */
    private int lv;
    /**
     * 经验
     */
    private int exp;
    /**
     * 最大等级
     */
    private int maxLv;
    /**
     * 当前等级的最大经验值
     */
    private int maxExp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(long goldNum) {
        this.goldNum = goldNum;
    }

    public long getPremiumDiamondNum() {
        return premiumDiamondNum;
    }

    public void setPremiumDiamondNum(long premiumDiamondNum) {
        this.premiumDiamondNum = premiumDiamondNum;
    }

    public long getDiamondNum() {
        return diamondNum;
    }

    public void setDiamondNum(long diamondNum) {
        this.diamondNum = diamondNum;
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

    public int getMaxLv() {
        return maxLv;
    }

    public void setMaxLv(int maxLv) {
        this.maxLv = maxLv;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public static UserMsgInfoData valueOf() {
        return new UserMsgInfoData();
    }
}
