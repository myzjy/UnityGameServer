package com.gameServer.home;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.PuzzleEntity;
import com.gameServer.commonRefush.resource.PuzzleResource;
import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import com.zfoo.util.ThreadUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/18 19 44
 */
@Ignore
public class ApplicationTest {
    static {
        TankDeployEnum.InitDefaultEnv();
    }

    @ResInjection
    private Storage<Integer, PuzzleResource> puzzleResourceStorage;

    @Test
    public void startApplication1() {
        Application.main(null);

        ThreadUtils.sleep(Long.MAX_VALUE);
    }

    @Test
    public void TestEx() {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
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
                entity.setAndroidVersion(dataEntity.getAndroidVersion());
                entity.setIosVersion(entity.getIosVersion());
                entity.setCreateAt(TimeUtils.simpleDateString());
                entity.setUpdatedAt(TimeUtils.simpleDateString());
                OrmContext.getAccessor().insert(entity);
            }
        }

    }
}
