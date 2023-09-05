package com.gameServer.home.puzzle.service;

import com.gameServer.commonRefush.ormEntity.PuzzleChapterDataEntity;
import com.gameServer.commonRefush.ormEntity.PuzzleEntity;
import com.gameServer.commonRefush.protocol.Puzzle.Puzzle;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleChapter;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/22 16 28
 */
public interface IPuzzleService {
    /**
     * 通过 数据库中的 地图数据 创建相关 Puzzle
     * 数据
     *
     * @return 地图相关 结构数据
     */
    List<Puzzle> GetTheMapServiceDataList(List<PuzzleEntity> puzzleConfig);

    List<PuzzleEntity> GetOrmPuzzleEntityAllList();

    /**
     * 返回 数据库中 章节 数据
     *
     * @return 返回 数据库中 章节 数据
     */
    List<PuzzleChapterDataEntity> GetPuzzleChapterDataEntityAllList();

    List<PuzzleChapter> GetThePuzzleChapterList(List<PuzzleChapterDataEntity> config);
}
