package com.gameServer.commonRefush.entity;

import com.zfoo.orm.anno.EntityCache;
import com.zfoo.orm.anno.Id;
import com.zfoo.orm.anno.Index;
import com.zfoo.orm.model.IEntity;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/23 9:40 AM
 */
@EntityCache
public class AccountEntity implements IEntity<String> {
    /**
     * 为account
     */
    @Id
    private String id;


    @Index(ascending = true, unique = true)
    private String account;

    private String password;

    @Index(ascending = true, unique = true)
    private long uid;

    public static AccountEntity valueOf(String id, String account, String password, long uid) {
        var entity = new AccountEntity();
        entity.id = id;
        entity.account = account;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", uid=" + uid +
                '}';
    }
}
