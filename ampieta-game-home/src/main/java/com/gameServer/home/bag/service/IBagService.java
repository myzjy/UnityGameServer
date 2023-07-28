package com.gameServer.home.bag.service;

import com.gameServer.commonRefush.entity.ItemBoxBaseEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/28 14 17
 */
public interface IBagService {
    ItemBoxBaseEntity loadItemBoxBaseEntity(int itemId);
}
