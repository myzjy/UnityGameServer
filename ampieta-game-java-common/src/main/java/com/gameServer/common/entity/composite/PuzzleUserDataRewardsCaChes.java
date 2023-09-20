package com.gameServer.common.entity.composite;

/**
 * 关卡 星级
 * @author zjy
 * @version 1.0
 * @since 2023/9/5 15 39
 */
public class PuzzleUserDataRewardsCaChes {
    /**
     * 星级
     */
    private int starRating;
    /**
     * 当前星级是否 已获取
     */
    private boolean rewardsObtain;

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public boolean isRewardsObtain() {
        return rewardsObtain;
    }

    public void setRewardsObtain(boolean rewardsObtain) {
        this.rewardsObtain = rewardsObtain;
    }
}
