package com.unitygameServer.serverGame.common.entity;

import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.anno.Persister;
import com.zfoo.orm.model.entity.IEntity;

/**
 * @author zjy
 * @version 0.1
 * @since 2022-7-15 23:45
 */
@EntityCache(cacheStrategy = "tenThousand", persister = @Persister("time30s"))
public class UserEntity implements IEntity<Long> {
    @Id
    private long id;
    /**
     * 用户token
     */
    private String Token;

    private String name;
    /**
     * 上次登录时间
     */
    private long lastLoginTime;
    /**
     * 注册时间
     */
    private long registerTime;


    @Override
    public Long id() {
        return null;
    }

    @Override
    public long gvs() {
        return IEntity.super.gvs();
    }

    @Override
    public void svs(long vs) {
        IEntity.super.svs(vs);
    }
}
