package com.gameServer.common.constant;

/**
 * 通关奖励 因为 完成关卡任务奖励就已经有钻石了
 * 
 *
 * @author zjy
 * @version 1.0
 * @since 2023/8/10 17 07
 */
public enum PuzzleRewardsEnum {
    /**
     * 金币
     */
    GoldCoin(1),
    /**
     * 装备
     */
    Equipment(2),
    ;
    /**
     * type 数字
     */
    public int type;

    PuzzleRewardsEnum(int type) {
        this.type = type;
    }
}
