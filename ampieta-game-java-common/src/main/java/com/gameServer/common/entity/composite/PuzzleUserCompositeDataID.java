package com.gameServer.common.entity.composite;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/5 13 11
 */
public class PuzzleUserCompositeDataID implements Comparable<PuzzleUserCompositeDataID> {
    private int puzzleId;
    private int userId;

    @Override
    public int compareTo(PuzzleUserCompositeDataID o) {
        return 0;
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
}
