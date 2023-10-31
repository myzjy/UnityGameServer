package com.gameServer.httpsServer;

import com.gameServer.common.constant.TankDeployEnum;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.protocol.util.ThreadUtils;
import org.junit.Ignore;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/29 15 19
 */
@Ignore
public class ApplicationTest {
    static {
        TankDeployEnum.InitDefaultEnv();
    }

    @org.junit.Test
    public void startApplication() {
        Application.main(StringUtils.EMPTY_ARRAY);
        ThreadUtils.sleep(Long.MAX_VALUE);
    }
}
