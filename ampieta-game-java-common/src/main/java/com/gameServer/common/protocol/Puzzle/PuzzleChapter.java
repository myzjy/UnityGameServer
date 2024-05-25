package com.gameServer.common.protocol.Puzzle;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 地图章节 数据
 *
 * @author zjy
 * @version 1.0
 * @since 2023/9/3 18 07
 */
@Protocol(id = 204)
public class PuzzleChapter implements IPacket {
    /**
     * id
     */
    @Note("id")
    private int id;
    /**
     * 章节名
     */
    @Note("章节名")
    private String chapterName;
    /**
     * 当前章节 最小 关卡id
     */
    @Note("当前章节 最小 关卡id")
    private int minPuzzle;
    /**
     * 当前章节 最大 关卡id
     */
    @Note("当前章节 最大 关卡id")
    private int maxPuzzle;
    /**
     * 当前 进行中的 关卡id
     * <p>
     * 如果这个数字 为负数就查看 doneMaxPuzzleId
     * 是否有值，没有值就代表 没有进行
     * </p>
     */
    @Note("当前 进行中的 关卡id")
    private int nowCarryOutPuzzleId;
    /**
     * 完成的最大关卡id 只有 关卡完成之后 才会更新
     */
    @Note("完成的最大关卡id 只有 关卡完成之后 才会更新")
    private int doneMaxPuzzleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getMinPuzzle() {
        return minPuzzle;
    }

    public void setMinPuzzle(int minPuzzle) {
        this.minPuzzle = minPuzzle;
    }

    public int getMaxPuzzle() {
        return maxPuzzle;
    }

    public void setMaxPuzzle(int maxPuzzle) {
        this.maxPuzzle = maxPuzzle;
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

    public static PuzzleChapter ValueOf(int id,String chapterName,int minPuzzle,int maxPuzzle,int nowCarryOutPuzzleId,int doneMaxPuzzleId) {
        var data = new PuzzleChapter();
        return data;
    }
}
