package com.gameServer.commonRefush.protocol.Puzzle;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/9 17 30
 */
@Protocol(id=203)
public class PuzzleRewardsData implements IPacket {
    /**
     * 奖励的物品id
     */
    private int rewardId;
    /**
     * 奖励 type
     */
    private int rewardType;
    /**
     * 奖励icon
     */
    private String rewardIcon;
    /**
     * 奖励 资源
     */
    private String rewardResource;
    /**
     * 奖励数量
     */
    private  int num;

    public static PuzzleRewardsData ValueOf(int rewardId, int rewardType, String rewardIcon, String rewardResource, int num) {
        var entity = new PuzzleRewardsData();
        entity.setRewardId(rewardId);
        entity.setRewardType(rewardType);
        entity.setRewardIcon(rewardIcon);
        entity.setRewardResource(rewardResource);
        entity.setNum(num);
        return entity;
    }

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public int getRewardType() {
        return rewardType;
    }

    public void setRewardType(int rewardType) {
        this.rewardType = rewardType;
    }

    public String getRewardIcon() {
        return rewardIcon;
    }

    public void setRewardIcon(String rewardIcon) {
        this.rewardIcon = rewardIcon;
    }

    public String getRewardResource() {
        return rewardResource;
    }

    public void setRewardResource(String rewardResource) {
        this.rewardResource = rewardResource;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
