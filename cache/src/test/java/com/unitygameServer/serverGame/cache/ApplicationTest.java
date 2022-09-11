package com.unitygameServer.serverGame.cache;

import com.unitygameServer.serverGame.commonRefush.constant.TankDeployEnum;
import com.zfoo.util.ThreadUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/10 22:58
 */
@Ignore
public class ApplicationTest {

    static {
        TankDeployEnum.InitDefaultEnv();
    }


    @Test
    public void startApplication() {
        Application.main(null);

        ThreadUtils.sleep(Long.MAX_VALUE);
    }
}
