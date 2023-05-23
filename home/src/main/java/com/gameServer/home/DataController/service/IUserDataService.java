package com.gameServer.home.DataController.service;

import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.home.model.UserModel;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/5/19 21 28
 */
public interface IUserDataService {
    void Add(PlayerUserEntity userEntity);
}
