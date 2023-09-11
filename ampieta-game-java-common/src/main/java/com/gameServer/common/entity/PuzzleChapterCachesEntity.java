package com.gameServer.common.entity;

import com.zfoo.orm.model.IEntity;

/**
 * 当前 章节 进度
 * @author zjy
 * @version 1.0
 * @since 2023/9/5 11 03
 */
public class PuzzleChapterCachesEntity implements IEntity<String> {
    /**
     * id 自增
     */
    private String id;
    private int puzzleId;
    private int userId;
    /**
     * 当前 进行中的 关卡id
     * <p>
     * 如果这个数字 为负数就查看 doneMaxPuzzleId
     * 是否有值，没有值就代表 没有进行
     * </p>
     */
    private int nowCarryOutPuzzleId;
    /**
     * 完成的最大关卡id 只有 关卡完成之后 才会更新
     */
    private int doneMaxPuzzleId;
    @Override
    public String id() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNowCarryOutPuzzleId() {
        return nowCarryOutPuzzleId;
    }

    public void setNowCarryOutPuzzleId(int nowCarryOutPuzzleId) {
        this.nowCarryOutPuzzleId = nowCarryOutPuzzleId;
    }

    public int getDoneMaxPuzzleId() {
        return doneMaxPuzzleId;
    }

    public void setDoneMaxPuzzleId(int doneMaxPuzzleId) {
        this.doneMaxPuzzleId = doneMaxPuzzleId;
    }
}
