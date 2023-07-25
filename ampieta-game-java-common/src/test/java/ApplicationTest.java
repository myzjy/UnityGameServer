import com.gameServer.common.test.bagTest.BagTest;
import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.resource.ConfigResource;
import com.gameServer.commonRefush.resource.PuzzleResource;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/11/20 1:45
 */
@Ignore
public class ApplicationTest {
    static {
        TankDeployEnum.InitDefaultEnv();
    }
    @ResInjection
    private Storage<Integer, PuzzleResource> puzzleResourceStorage;
    /**
     * 将表放到数据库中
     */
    @Test
    public void ApplyDataMain() {
        
    }
}
