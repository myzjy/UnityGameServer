package com.gameServer.home.bag.service;

import com.gameServer.commonRefush.entity.BagUserItemEntity;
import com.gameServer.commonRefush.entity.ItemBoxBaseEntity;
import com.gameServer.commonRefush.protocol.bag.BagUserItemData;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/28 14 17
 */
public interface IBagService {
    ItemBoxBaseEntity loadItemBoxBaseEntity(int itemId);

    List<BagUserItemEntity> FindBagMasterUserIdEntityOrm(long uid);
}
