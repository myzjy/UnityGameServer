package com.gameServer.gameBoot;

import com.gameServer.commonRefush.ormEntity.PuzzleChapterDataEntity;
import com.gameServer.commonRefush.ormEntity.PuzzleEntity;
import com.gameServer.commonRefush.resource.PuzzleChapterResource;
import com.gameServer.commonRefush.resource.PuzzleResource;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/26 10 37
 */
@Component
public class PuzzleManager {
    @ResInjection
    public Storage<Integer, PuzzleResource> puzzleResourceStorage;
    @ResInjection
    private Storage<Integer, PuzzleChapterResource> puzzleChapterResourceStorage;

    public void UpDataPuzzleOrm() {
        var dict = puzzleResourceStorage.getAll();
        for (var data :
                dict) {
            var dataEntity = OrmContext.getAccessor().load(data.getId(), PuzzleEntity.class);
            if (dataEntity == null) {
                var entity = PuzzleEntity.ValueOf();
                entity.setId(data.getId());
                entity.setPuzzleName(data.getPuzzleName());
                entity.setPuzzleRewards(data.getPuzzleRewards());
                entity.setLastPuzzleID(data.getLastPuzzleID());
                entity.setNextPuzzleID(data.getNextPuzzleID());
                entity.setAndroidVersion(data.getAndroidVersion());
                entity.setIosVersion(data.getIosVersion());
                entity.setPcEditorVersion(data.getPcEditorVersion());
                entity.setIcon(data.getIcon());
                entity.setResourceStr(data.getResourcePath());
                var update = TimeUtils.timeToString(TimeUtils.now());

                entity.setCreateAt(update);
                entity.setUpdatedAt(update);
                OrmContext.getAccessor().insert(entity);
            } else {
                var entity = PuzzleEntity.ValueOf();
                entity.setId(data.getId());
                entity.setPuzzleName(data.getPuzzleName());
                entity.setPuzzleRewards(data.getPuzzleRewards());
                entity.setLastPuzzleID(data.getLastPuzzleID());
                entity.setNextPuzzleID(data.getNextPuzzleID());
                entity.setAndroidVersion(dataEntity.getAndroidVersion());
                entity.setIosVersion(dataEntity.getIosVersion());
                entity.setPcEditorVersion(dataEntity.getPcEditorVersion());
                entity.setIcon(data.getIcon());
                entity.setResourceStr(data.getResourcePath());
                var update = TimeUtils.timeToString(TimeUtils.now());
                entity.setUpdatedAt(update);
                OrmContext.getAccessor().update(entity);
            }
        }
    }

    public void  UpDataPuzzleChapterOrm(){
        var dict = puzzleChapterResourceStorage.getAll();
        for (var data :
                dict) {
            var dataEntity = OrmContext.getAccessor().load(data.getId(), PuzzleChapterDataEntity.class);
            if (dataEntity == null) {
                var update = TimeUtils.timeToString(TimeUtils.now());
                var entity = PuzzleChapterDataEntity.ValueOf(data.getId(),
                                                             data.getMinPuzzle(),
                                                             data.getMaxPuzzle(),
                                                             data.getChapterName(),
                                                             update, update );
                OrmContext.getAccessor().insert(entity);
            } else {
                var update = TimeUtils.timeToString(TimeUtils.now());
                var entity = PuzzleChapterDataEntity.ValueOf(data.getId(),
                                                             data.getMinPuzzle(),
                                                             data.getMaxPuzzle(),
                                                             data.getChapterName(),
                                                             dataEntity.getCreateAt(), update );
                OrmContext.getAccessor().update(entity);
            }
        }
    }
}
