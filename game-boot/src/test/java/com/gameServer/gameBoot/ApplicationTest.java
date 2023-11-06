package com.gameServer.gameBoot;

import com.gameServer.common.constant.TankDeployEnum;
import com.zfoo.event.model.AppStartEvent;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.protocol.util.ThreadUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/10/5 13:35
 */
@Ignore
public class ApplicationTest {
    static {
        TankDeployEnum.InitDefaultEnv();
    }
    @Test
    public void startApplication(){
        Application.main(StringUtils.EMPTY_ARRAY);

        ThreadUtils.sleep(Long.MAX_VALUE);
    }
    @Test
    public void TestEx() throws Exception {
        var context = new ClassPathXmlApplicationContext("app.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
        var studentManager = context.getBean(PuzzleManager.class);
        studentManager.UpDataPuzzleOrm();
        studentManager.UpDataPuzzleChapterOrm();
        var ormAddManager = context.getBean(OrmAddManager.class);
        ormAddManager.UpdateItemBaseCsvResource();
        ormAddManager.UpdateStageCsvResource();
        ormAddManager.UpdateStageMissionCsvResource();
        ormAddManager.UpdateConfigResource();
        ormAddManager.UpdateOrInAccesGameTimeResource();
        var equipmentOrm=new EquipmentOrmAddManager();
        equipmentOrm.UpdateEquipmentGrowthConfigResource();
        equipmentOrm.UpdateEquipmentGrowthViceConfigResource();
        //equipmentOrm.UpdateEquipmentPrimaryConfigResource();
    }
}
