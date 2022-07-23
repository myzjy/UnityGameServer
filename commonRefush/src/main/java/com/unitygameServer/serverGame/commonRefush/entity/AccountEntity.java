package com.unitygameServer.serverGame.commonRefush.entity;

import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.anno.Persister;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/23 9:40 AM
 */
@EntityCache(cacheStrategy = "tenThousand", persister = @Persister("time30s"))
public class AccountEntity {
    @Id
    private String id;

    private String name;
}
