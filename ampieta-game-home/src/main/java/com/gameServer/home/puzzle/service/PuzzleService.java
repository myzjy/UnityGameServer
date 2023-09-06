package com.gameServer.home.puzzle.service;

import com.gameServer.commonRefush.entity.PuzzleChapterCachesEntity;
import com.gameServer.commonRefush.entity.PuzzleUserDataCaChesEntity;
import com.gameServer.commonRefush.ormEntity.PuzzleChapterDataEntity;
import com.gameServer.commonRefush.ormEntity.PuzzleEntity;
import com.gameServer.commonRefush.protocol.Puzzle.Puzzle;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleChapter;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleRewardsData;
import com.gameServer.home.bag.service.IBagService;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/22 16 37
 */
@Component
public class PuzzleService implements IPuzzleService {
    private final Logger logger = LoggerFactory.getLogger(PuzzleService.class);
    @Autowired
    private IBagService bagService;

    @Override
    public List<Puzzle> GetTheMapServiceDataList(List<PuzzleEntity> puzzleConfig) {
        List<Puzzle> puzzleList = new ArrayList<>();
        for (var data : puzzleConfig) {
            Puzzle puzzle = new Puzzle();
            puzzle.setId(data.getId());
            puzzle.setIcon(data.getIcon());
            puzzle.setResourcePath(data.getResourceStr());
            puzzle.setPuzzleName(data.getPuzzleName());
            puzzle.setLastPuzzleID(data.getLastPuzzleID());
            puzzle.setNextPuzzleID(data.getNextPuzzleID());
            List<PuzzleRewardsData> rewardsDataList = new ArrayList<>();
            var rewardStr = data.getPuzzleRewards();
            var rewardSplit = rewardStr.split(";");
            for (String rewardValueStr : rewardSplit) {
                var rewardValueStrSplit = rewardValueStr.split(":");
                /* *
                 * type
                 */
                var rewardValueStr1 = rewardValueStrSplit[0];
                /* *
                 * 奖励的type
                 */
                int rewardType = Integer.parseInt(rewardValueStr1);
                /* *
                 * 具体奖励内容
                 */
                var rewardValueStr2 = rewardValueStrSplit[1];
                var rewardValues = rewardValueStr2.split("|");
                int rewardID = Integer.parseInt(rewardValues[0]);
                int rewardNum = Integer.parseInt(rewardValues[1]);
                PuzzleRewardsData data1 = new PuzzleRewardsData();
                var itemData = bagService.loadItemBoxBaseEntity(rewardID);
                if (itemData != null) {
                    data1.setRewardIcon(itemData.getIcon());
                    data1.setRewardResource(itemData.getResources());
                    data1.setNum(rewardNum);
                    data1.setRewardId(rewardID);
                    data1.setRewardType(rewardType);
                    rewardsDataList.add(data1);
                }
            }
            puzzle.setPuzzleRewardsDatas(rewardsDataList);
            logger.info("index:{}, Puzzle:{}", puzzleList.size(), JsonUtils.object2String(puzzle));
            puzzleList.add(puzzle);
        }
        return puzzleList;
    }

    @Override
    public List<PuzzleEntity> GetOrmPuzzleEntityAllList() {
        var puzzleConfig = OrmContext.getQuery(PuzzleEntity.class).queryAll();
        return puzzleConfig;
    }

    @Override
    public List<PuzzleChapterDataEntity> GetPuzzleChapterDataEntityAllList() {
        var ChapterDataConfig = OrmContext.getQuery(PuzzleChapterDataEntity.class).queryAll();
        return ChapterDataConfig;
    }

    @Override
    public List<PuzzleChapter> GetThePuzzleChapterList(List<PuzzleChapterDataEntity> config, long uid) {
        List<PuzzleChapter> puzzleList = new ArrayList<>();
        for (PuzzleChapterDataEntity data : config) {
            var id = String.format(data.getId() + "|" + uid);
            var caches = OrmContext.getAccessor().load(id, PuzzleChapterCachesEntity.class);
            if (caches != null) {
                var puzzleChapter = PuzzleChapter.ValueOf(data.getId(),
                                                          data.getChapterName(),
                                                          data.getMinPuzzle(),
                                                          data.getMaxPuzzle(),
                                                          caches.getNowCarryOutPuzzleId(),
                                                          caches.getDoneMaxPuzzleId());
                puzzleList.add(puzzleChapter);
            } else {
                logger.error("id:{},class:{},数据库中查找不到对应数据", id, PuzzleChapterCachesEntity.class);
                var puzzleChapter = PuzzleChapter.ValueOf(data.getId(),
                                                          data.getChapterName(),
                                                          data.getMinPuzzle(),
                                                          data.getMaxPuzzle(),
                                                          -1,
                                                          0);
                puzzleList.add(puzzleChapter);
            }
        }
        return puzzleList;
    }
}
