package com.gameServer.home.bag.service;

import com.gameServer.common.entity.BagUserItemEntity;
import com.gameServer.common.entity.ItemBoxBaseEntity;

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
