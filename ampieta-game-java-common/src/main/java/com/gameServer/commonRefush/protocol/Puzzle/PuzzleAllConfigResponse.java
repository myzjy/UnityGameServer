package com.gameServer.commonRefush.protocol.Puzzle;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/31 23 58
 */
@Protocol(id = 1036)
public class PuzzleAllConfigResponse implements IPacket {
    /**
     * 地图配置
     */
    private List<Puzzle> puzzleConfigList = new ArrayList<>();
    private List<PuzzleChapter> puzzleChapterConfigList = new ArrayList<>();

    public static PuzzleAllConfigResponse ValueOf() {
        var packet = new PuzzleAllConfigResponse();
        packet.puzzleConfigList = new ArrayList<>();
        packet.puzzleChapterConfigList = new ArrayList<>();
        return packet;
    }

    public void setPuzzleConfigList(List<Puzzle> list) {
        puzzleConfigList = new ArrayList<>();
        if (list == null) {
            return;
        }
        puzzleConfigList.addAll(list);
    }

    public List<Puzzle> getPuzzleConfigList() {
        return puzzleConfigList;
    }

    public void setPuzzleChapterConfigList(List<PuzzleChapter> puzzleChapterConfigList) {
        this.puzzleChapterConfigList = new ArrayList<>();
        if (puzzleChapterConfigList == null) {
            return;
        }
        this.puzzleChapterConfigList.addAll(puzzleChapterConfigList);
    }

    public List<PuzzleChapter> getPuzzleChapterConfigList() {
        return puzzleChapterConfigList;
    }
}
