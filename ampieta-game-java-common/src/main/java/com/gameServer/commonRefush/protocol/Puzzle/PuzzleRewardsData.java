package com.gameServer.commonRefush.protocol.Puzzle;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/9 17 30
 */
public class PuzzleRewardsData {
    /**
     * 奖励的物品id
     */
    protected int rewardId;
    /**
     * 奖励 type
     */
    protected int rewardType;
    /**
     * 奖励icon
     */
    protected String rewardIcon;
    /**
     * 奖励 资源
     */
    protected String rewardResource;
    /**
     * 奖励数量
     */
    protected int num;

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
