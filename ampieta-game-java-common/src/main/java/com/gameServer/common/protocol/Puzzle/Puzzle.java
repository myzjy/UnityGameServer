package com.gameServer.common.protocol.Puzzle;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图 当前关卡内部可能的配置 比如怪物配置，怪物拥有技能配置，怪物和boss 配置攻击设置 位置
 *
 * @author zjy
 * @version 1.0
 * @since 2023/7/25 16 49
 */
@Protocol(id = 202)
public class Puzzle implements IPacket {
    @Note("id")
    private int id;
    /**
     * 关卡名字
     */
    @Note("关卡名字")
    private String puzzleName;
    /**
     * 关联到 上一个关卡id
     */
    @Note("关联到 上一个关卡id")
    private int lastPuzzleID;
    /**
     * 下一个 关卡id 可用在 跳下一关
     */
    @Note("下一个 关卡id 可用在 跳下一关")
    private int nextPuzzleID;
    /**
     * 关卡 奖励
     */
    @Note("关卡 奖励")
    private List<PuzzleRewardsData> puzzleRewardsDatas;
    /**
     * Icon 图片资源名
     */
    @Note(" Icon 图片资源名")
    private String icon;
    /**
     * 资源路径
     */
    @Note("资源路径")
    private String resourcePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    public int getLastPuzzleID() {
        return lastPuzzleID;
    }

    public void setLastPuzzleID(int lastPuzzleID) {
        this.lastPuzzleID = lastPuzzleID;
    }

    public int getNextPuzzleID() {
        return nextPuzzleID;
    }

    public void setNextPuzzleID(int nextPuzzleID) {
        this.nextPuzzleID = nextPuzzleID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public List<PuzzleRewardsData> getPuzzleRewardsDatas() {
        return puzzleRewardsDatas;
    }

    public void setPuzzleRewardsDatas(List<PuzzleRewardsData> puzzleRewardsDatas) {
        this.puzzleRewardsDatas = new ArrayList<>();
        if (puzzleRewardsDatas == null) {
            return;
        }
        this.puzzleRewardsDatas.addAll(puzzleRewardsDatas);
    }
}
