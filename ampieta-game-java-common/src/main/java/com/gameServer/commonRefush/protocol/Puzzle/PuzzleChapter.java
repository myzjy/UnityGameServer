package com.gameServer.commonRefush.protocol.Puzzle;

import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 地图章节 数据
 * @author zjy
 * @version 1.0
 * @since 2023/9/3 18 07
 */
@Protocol(id = 204)
public class PuzzleChapter implements IPacket {
    /**
     * id
     */
    private int id;
    /**
     * 章节名
     */
    private String chapterName;
    /**
     * 当前章节 最小 关卡id
     */
    private int minPuzzle;
    /**
     * 当前章节 最大 关卡id
     */
    private int maxPuzzle;

}
