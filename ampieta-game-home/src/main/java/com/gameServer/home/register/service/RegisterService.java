package com.gameServer.home.register.service;

import com.gameServer.commonRefush.entity.AccountEntity;
import com.zfoo.orm.OrmContext;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/23 11 16
 */
@Component
public class RegisterService implements IRegisterService {
    @Override
    public AccountEntity LoadAccountEntityString(String accountStr) {
        var accountData = OrmContext.getAccessor().load(accountStr, AccountEntity.class);
        return accountData;
    }

    @Override
    public void InsterAccountEntityOrm(AccountEntity entity) {
        OrmContext.getAccessor().insert(entity);
    }
}
