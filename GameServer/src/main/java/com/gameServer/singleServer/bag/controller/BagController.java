package com.gameServer.singleServer.bag.controller;

import com.gameServer.commonRefush.entity.BagItemEntity;
import com.gameServer.commonRefush.protocol.bag.AllBagItemRequest;
import com.mongodb.client.model.Filters;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.model.AttributeType;
import com.zfoo.net.session.model.Session;
import com.zfoo.orm.OrmContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

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

    @PacketReceiver
    public void AtAllBagItemResponse(Session session, AllBagItemRequest request) {
        //拿到我们自己的UID，当前
        var uid = (long) session.getAttribute(AttributeType.UID);
        logger.info("[{}]开始获取背包", uid);
        var dict = OrmContext.getQuery(BagItemEntity.class).queryAll();
        var dicts = OrmContext.getOrmManager().getCollection(BagItemEntity.class);

        dicts.find(Filters.text(uid + "")).forEach(new Consumer<BagItemEntity>() {
            @Override
            public void accept(BagItemEntity bagItemEntity) {

            }
        });
    }
}
