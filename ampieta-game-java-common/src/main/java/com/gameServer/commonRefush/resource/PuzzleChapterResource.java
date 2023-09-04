package com.gameServer.commonRefush.resource;

import com.zfoo.storage.model.anno.Id;
import com.zfoo.storage.model.anno.Index;
import com.zfoo.storage.model.anno.Resource;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/4 17 28
 */
@Resource(alias = "PuzzleChapterResource")
public class PuzzleChapterResource {
    /**
     * id
     */
    @Id
    @Index
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


    public int getId() {
        return id;
    }


    public String getChapterName() {
        return chapterName;
    }

    public int getMinPuzzle() {
        return minPuzzle;
    }

    public int getMaxPuzzle() {
        return maxPuzzle;
    }
}
