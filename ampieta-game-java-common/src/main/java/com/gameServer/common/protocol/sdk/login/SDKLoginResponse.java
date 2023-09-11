package com.gameServer.common.protocol.sdk.login;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/5/7 22 43
 */
@Protocol(id = 5001)
public class SDKLoginResponse implements IPacket {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    private long uid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    /**
     * 金币
     */
    private long goldNum;
    /**
     * 付费钻石 一般充值才有，付费钻石转换成普通钻石
     */
    private long PremiumDiamondNum;
    /**
     * 普通钻石 由付费钻石转换成普通钻石，比例为 1:1
     */
    private long DiamondNum;

    /**
     * 拿到token之后返回出去
     */
    public static SDKLoginResponse valueOf(String token, String userName, long uid, long goldNum, long premiumDiamondNum, long diamondNum) {
        var paket = new SDKLoginResponse();
        paket.token = token;
        //可能会为，因为没有到玩家取名步骤
        paket.userName = userName;
        paket.uid = uid;
        paket.setGoldNum(goldNum);
        paket.setPremiumDiamondNum(premiumDiamondNum);
        paket.setDiamondNum(diamondNum);
        return paket;
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
}
