package com.unitygameServer.serverGame.commonRefush.entity;

import com.zfoo.net.session.model.Session;
import com.zfoo.orm.model.anno.EntityCache;
import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.anno.Index;
import com.zfoo.orm.model.anno.Persister;
import com.zfoo.orm.model.entity.IEntity;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/23 9:40 AM
 */
public class AccountEntity implements IEntity<String> {
    /**
     * 为account
     */
    @Id
    private String id;

    @Index(ascending = true, unique = true)
    private String name;

    private String password;

    @Index(ascending = true, unique = true)
    private long uid;

    public static AccountEntity valueOf(String id, String name, String password, long uid) {
        var entity = new AccountEntity();
        entity.id = id;
        entity.name = name;
        entity.password = password;
        entity.uid = uid;
        return entity;
    }

    @Override
    public String id() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
