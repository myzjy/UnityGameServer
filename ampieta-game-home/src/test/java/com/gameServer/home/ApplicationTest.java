package com.gameServer.home;

import com.gameServer.common.constant.TankDeployEnum;
import com.gameServer.common.entity.PlayerUserEntity;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.anno.EntityCache;
import com.zfoo.orm.anno.EntityCacheAutowired;
import com.zfoo.orm.anno.Persister;
import com.zfoo.orm.cache.IEntityCache;
import com.zfoo.orm.model.IEntity;
import com.zfoo.protocol.util.ThreadUtils;
import com.zfoo.scheduler.util.TimeUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
