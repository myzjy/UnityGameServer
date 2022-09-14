package com.unitygameServer.serverGame.commonRefush.entity;

import com.zfoo.net.session.model.Session;
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
    // 记录会话信息
    public transient long sid = 1;
    public transient Session session = null;
    /**
     * 相当于uid
     * */
    @Id
    private long id;

    private long vs;
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
    private String message;

    /**
     * 玩家数据结构
     *
     * @param id            userId
     * @param name          userName
     * @param lastLoginTime 上次登录时间
     * @param registerTime  注册时间
     */
    public static UserEntity valueOf(long id, String name, long lastLoginTime, long registerTime) {
        var entity = new UserEntity();

        entity.id = id;
        entity.name = name;
        entity.lastLoginTime = lastLoginTime;
        entity.registerTime = registerTime;

        return entity;
    }

    @Override
    public Long id() {
        return id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public long gvs() {
        return vs;
    }

    @Override
    public void svs(long vs) {
        this.vs = vs;
    }

    public long getVs() {
        return vs;
    }

    public void setVs(long vs) {
        this.vs = vs;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return Token;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 设置当前玩家Token 除开第一次登录以外，都采用token形式
     */
    public void setToken(String token) {
        Token = token;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 设置消息
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
