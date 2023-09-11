package com.gameServer.common.entity;

import com.gameServer.common.entity.composite.PuzzleUserCompositeDataID;
import com.gameServer.common.entity.composite.PuzzleUserDataRewardsCaChes;
import com.zfoo.orm.model.IEntity;

/**
 * 玩家 关卡 进度
 *
 * @author zjy
 * @version 1.0
 * @since 2023/9/5 11 22
 */
public class PuzzleUserDataCaChesEntity implements IEntity<PuzzleUserCompositeDataID> {
    private PuzzleUserCompositeDataID puzzleUserCompositeDataID;
    /**
     * 当前关卡 获取 星级
     */
    private int puzzleStarRating;
    /**
     * 奖励获取情况
     */
    private PuzzleUserDataRewardsCaChes[] obtains;

    @Override
    public PuzzleUserCompositeDataID id() {
        return puzzleUserCompositeDataID;
    }
}
