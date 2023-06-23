package com.gameServer.gateway;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.zfoo.util.ThreadUtils;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/19 17 37
 */
@Ignore
public class ApplicationTest {

    static {
        TankDeployEnum.InitDefaultEnv();
    }

    @Test
    public void AppTest() {
        Application.main(null);
        ThreadUtils.sleep(Long.MAX_VALUE);

    }
}
