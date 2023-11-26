package com.gameServer.home;

import com.gameServer.common.constant.TankDeployEnum;
import com.zfoo.protocol.util.ThreadUtils;

import org.junit.Ignore;
import org.junit.Test;

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
}
