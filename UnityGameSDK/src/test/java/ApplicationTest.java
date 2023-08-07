import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.unityGameSDK.Application;
import com.zfoo.event.model.event.AppStartEvent;
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
        var studentManager = context.getBean(PuzzleManager.class);
        studentManager.UpDataPuzzleOrm();
        var ormAddManager = context.getBean(OrmAddManager.class);
        ormAddManager.UpdateItemBaseCsvResource();
        ormAddManager.UpdateStageCsvResource();

    }
}
