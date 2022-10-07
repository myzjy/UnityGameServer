package com.gameServer.gameBoot;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.util.ThreadUtils;
import org.junit.Ignore;
import org.junit.Test;

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
}
