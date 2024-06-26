package com.gameServer.common.protocol.riqueza;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 返回自己拥有金币 钻石 付费钻石
 * 刷新 也可以作为返回
 *
 * @author zjy
 * @version 1.0
 * @since 2023/5/7 23 59
 */
@Protocol(id = 1028)
public class RefreshingResourcesMainResponse implements IPacket {


    /**
     * 金币
     */
    @Note("金币")
    private long goldNum;
    /**
     * 付费钻石 一般充值才有，付费钻石转换成普通钻石
     */
    @Note(" 付费钻石 一般充值才有，付费钻石转换成普通钻石")
    private long PremiumDiamondNum;
    /**
     * 普通钻石 由付费钻石转换成普通钻石，比例为 1:1
     */
    @Note("普通钻石 由付费钻石转换成普通钻石，比例为 1:1")
    private long DiamondNum;

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

    public static RefreshingResourcesMainResponse ValueOf(long goldNum, long premiumDiamondNum, long diamondNum) {
        var data = new RefreshingResourcesMainResponse();
        data.setGoldNum(goldNum);
        data.setPremiumDiamondNum(premiumDiamondNum);
        data.setDiamondNum(diamondNum);
        return data;
    }
}
