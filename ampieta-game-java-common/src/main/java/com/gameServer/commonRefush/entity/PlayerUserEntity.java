package com.gameServer.commonRefush.entity;

import com.zfoo.net.session.Session;
import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.anno.Persister;
import com.zfoo.orm.model.entity.IEntity;

/**
 * @author zjy
 * @version 0.1
 * @since 2022-7-15 23:45
 */
@EntityCache(persister = @Persister("time30s"))
public class PlayerUserEntity implements IEntity<Long> {
    // 记录会话信息
    public transient long sid = 1;
    public transient Session session = null;
    /**
     * 相当于uid
     */
    @Id
    private long id;

    private long vs;
    /**
     * 用户token
     */
    private String Token;

    private String name;
    /**
     * 上次登录时间
     */
    private long lastLoginTime;
    /*
     * 退出游戏时间
     * */
    private long endLoginOutTime;
    /**
     * 注册时间
     */
    private long registerTime;

    /**
     * 金币
     */
    private long goldNum;
    /**
     * 付费钻石 一般充值才有，付费钻石转换成普通钻石
     */
    private long PremiumDiamondNum;
    /**
     * 普通钻石 又两部分组成
     * <code>
     * <p>第一部分 由付费钻石转换成普通钻石，比例为 1:1 </p>
     * <p>第二部分 通过任务 开箱 地图 奖励 日常运维奖励 </p>
     * </code>
     */
    private long DiamondNum;
    /**
     * 玩家等级
     */
    private int playerLv;

    /**
     * 玩家当前等级 当前经验
     */
    private int nowExp;

    /**
     * 玩家当前等级的最大经验
     */
    private int nowLvMaxExp;

    /**
     * 当前体力
     */
    private int nowPhysicalPowerNum;

//    private long

    /**
     * 玩家数据结构
     *
     * @param id            userId
     * @param name          userName
     * @param lastLoginTime 上次登录时间
     * @param registerTime  注册时间
     * @param token         玩家token
     */
    public static PlayerUserEntity valueOf(long id,
                                           String name,
                                           long lastLoginTime,
                                           long registerTime,
                                           String token,
                                           long goldNum,
                                           long premiumDiamondNum,
                                           long diamondNum,
                                           long endLoginOutTime,
                                           int nowExp,
                                           int nowPhysicalPowerNum,
                                           int nowLvMaxExp,
                                           int playerLv) {
        var entity = new PlayerUserEntity();

        entity.id = id;
        entity.name = name;
        entity.lastLoginTime = lastLoginTime;
        entity.registerTime = registerTime;
        entity.setToken(token);
        entity.goldNum = goldNum;
        entity.PremiumDiamondNum = premiumDiamondNum;
        entity.DiamondNum = diamondNum;
        entity.endLoginOutTime = endLoginOutTime;
        entity.nowExp = nowExp;
        entity.nowPhysicalPowerNum = nowPhysicalPowerNum;
        entity.nowLvMaxExp = nowLvMaxExp;
        entity.playerLv = playerLv;
        return entity;
    }

    @Override
    public Long id() {
        return id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public long gvs() {
        return vs;
    }

    @Override
    public void svs(long vs) {
        this.vs = vs;
    }

    public long getVs() {
        return vs;
    }

    public void setVs(long vs) {
        this.vs = vs;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return Token;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 设置当前玩家Token 除开第一次登录以外，都采用token形式
     */
    public void setToken(String token) {
        Token = token;
    }

    public long getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(long goldNum) {
        this.goldNum = goldNum;
    }

    public long getPremiumDiamondNum() {
        return PremiumDiamondNum;
    }

    public void setPremiumDiamondNum(long premiumDiamondNum) {
        PremiumDiamondNum = premiumDiamondNum;
    }

    public long getDiamondNum() {
        return DiamondNum;
    }

    public void setDiamondNum(long diamondNum) {
        DiamondNum = diamondNum;
    }

    public long getEndLoginOutTime() {
        return endLoginOutTime;
    }

    public void setEndLoginOutTime(long endLoginOutTime) {
        this.endLoginOutTime = endLoginOutTime;
    }

    public int getPlayerLv() {
        return playerLv;
    }

    public void setPlayerLv(int playerLv) {
        this.playerLv = playerLv;
    }

    public int getNowExp() {
        return nowExp;
    }

    public void setNowExp(int nowExp) {
        this.nowExp = nowExp;
    }

    public int getNowLvMaxExp() {
        return nowLvMaxExp;
    }

    public void setNowLvMaxExp(int nowLvMaxExp) {
        this.nowLvMaxExp = nowLvMaxExp;
    }

    /**
     * 返回当前体力
     *
     * @return 返回当前体力
     */
    public int getNowPhysicalPowerNum() {
        return nowPhysicalPowerNum;
    }

    /**
     * 设置体力
     *
     * @param nowPhysicalPowerNum 体力
     */
    public void setNowPhysicalPowerNum(int nowPhysicalPowerNum) {
        this.nowPhysicalPowerNum = nowPhysicalPowerNum;
    }
}
