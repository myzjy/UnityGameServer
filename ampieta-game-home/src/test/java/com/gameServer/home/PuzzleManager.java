package com.gameServer.home;

import com.gameServer.commonRefush.entity.PuzzleEntity;
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
                var update = TimeUtils.timeToString(TimeUtils.now());
                entity.setUpdatedAt(update);
                OrmContext.getAccessor().insert(entity);
            }
        }
    }
}
