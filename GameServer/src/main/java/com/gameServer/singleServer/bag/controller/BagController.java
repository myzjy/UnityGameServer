package com.gameServer.singleServer.bag.controller;

import com.gameServer.commonRefush.entity.BagUserItemEntity;
import com.gameServer.commonRefush.event.bag.StartLoginBagEvent;
import com.gameServer.commonRefush.protocol.bag.AllBagItemRequest;
import com.gameServer.commonRefush.protocol.bag.AllBagItemResponse;
import com.gameServer.commonRefush.protocol.bag.BagUserItemData;
import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.zfoo.event.manager.EventBus;
import com.zfoo.event.model.anno.EventReceiver;
import com.zfoo.net.NetContext;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 背包控制器 对于背包相关操作
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 19:49
 */
@Component
public class BagController {
    private static final Logger logger = LoggerFactory.getLogger(BagController.class);
    /**
     * 背包基础
     */
    @ResInjection
    public Storage<Integer, ItemBaseCsvResource> itemCsvResources;

    @PacketReceiver
    public void atAllBagItemRequest(Session session, AllBagItemRequest request) {

    }

    @EventReceiver
    public void onStartLoginBagEvent(StartLoginBagEvent event) {
        logger.info("onStartLoginBagEvent");
        var session = event.getSession();
        List<BagUserItemData> bagUserItemEntities = new ArrayList<>();
        EventBus.asyncExecute(() -> {
            IEntityCaches<? extends Comparable<?>, BagUserItemEntity> items = OrmContext.getOrmManager().getEntityCaches(BagUserItemEntity.class);
            items.forEach((res, i) -> {
                if (i.getMasterUserId() != session.getUid()) {
                    return;
                }
                //打出数据
                logger.info(i.toString());
                BagUserItemData data = BagUserItemData.ValueOf(i);
                bagUserItemEntities.add(data);
            });
            logger.info("[uid:{}][当前背包里面count:{}]", session.getUid(), bagUserItemEntities.size());
            //推送发送
            NetContext.getRouter().send(event.getSession(), AllBagItemResponse.ValueOf(bagUserItemEntities));
        });


    }

}
