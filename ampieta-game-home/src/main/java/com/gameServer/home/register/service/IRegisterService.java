package com.gameServer.home.register.service;

import com.gameServer.common.entity.AccountEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/23 11 15
 */
public interface IRegisterService {
    /**
     *  读取 账号 密码
     * @param accountStr 账号
     * @return 返回数据
     */
    AccountEntity LoadAccountEntityString(String accountStr);

    /**
     * 插入 数据
     * @param entity
     */
    void InsterAccountEntityOrm(AccountEntity entity);
}
