package com.gameServer.home.bag.controller;

import com.gameServer.common.constant.BagItemType;
import com.gameServer.common.constant.BagTypeEnum;
import com.gameServer.common.entity.BagUserItemEntity;
import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.gameServer.common.event.bag.StartLoginBagEvent;
import com.gameServer.common.protocol.bag.AllBagItemRequest;
import com.gameServer.common.protocol.bag.AllBagItemResponse;
import com.gameServer.common.protocol.bag.BagUserItemData;
import com.gameServer.common.protocol.bag.UseTheBagItemEffectRequest;
import com.gameServer.home.bag.service.IBagService;
import com.gameServer.home.weapon.service.IWeaponService;
import com.zfoo.event.anno.EventReceiver;
import com.zfoo.event.manager.EventBus;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IBagService mBagService;
    @Autowired
    private IWeaponService weaponService;

    @PacketReceiver
    public void atAllBagItemRequest(Session session, AllBagItemRequest request) {
        BagTypeEnum enumType = BagTypeEnum.GetType(request.getType());
        switch (enumType) {
            case Weapon: {
                //武器相关协议处理
                var findUidDataList = OrmContext.getQuery(WeaponUsePlayerDataEntity.class).eq("userUid", session.getUid());
                var list = findUidDataList.queryAll();
                for (int i = 0; i < list.size(); i++) {
                    // 具体数据
                    var item = list.get(i);
                    var configData = weaponService.FindWeaponsConfigData(item.getWeaponId());
                    BagUserItemData data = new BagUserItemData();
                    data.set_id(item.getId());
                    data.setItemId(item.getWeaponId());
                    data.setIcon(item.getCreateAt());
                }
            }
            break;
        }
    }

    @EventReceiver
    public void onStartLoginBagEvent(StartLoginBagEvent event) {
        logger.info("onStartLoginBagEvent");
        var session = event.getSession();
        //根据type去区分
        var type = event.getType();
        List<BagUserItemData> bagUserItemEntities = new ArrayList<>();
        EventBus.asyncExecute(() -> {
//            IEntityCaches<? extends Comparable<?>, BagUserItemEntity> items = OrmContext.getOrmManager().getEntityCaches(BagUserItemEntity.class);
            //获取到对应玩家道具
            List<BagUserItemEntity> items = mBagService.FindBagMasterUserIdEntityOrm(session.getUid());
            items.forEach((res) -> {
                //打出数据
                logger.info(res.toString());
                BagUserItemData data = BagUserItemData.ValueOf(res);
                //查找 对应的 道具
                var item = mBagService.loadItemBoxBaseEntity(data.getItemId());
                data.setQuality(item.getQuality());
                bagUserItemEntities.add(data);
            });
            logger.info("[uid:{}][当前背包里面count:{}]", session.getUid(), bagUserItemEntities.size());
            //推送发送
            NetContext.getRouter().send(event.getSession(), AllBagItemResponse.ValueOf(bagUserItemEntities));
        });
    }

    @PacketReceiver
    public void atUseTheBagItemEffectRequest(Session session, UseTheBagItemEffectRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("玩家使用背包道具相关");
        /* *
         * 获取到对应 数据库中配置
         */
        var itemBase = mBagService.loadItemBoxBaseEntity(request.getItemId());
        BagTypeEnum enumType = BagTypeEnum.GetType(itemBase.getType());
        switch (enumType) {
            case Weapon: {
                //武器
                logger.info("[UID:{}] 使用[{}] {}", session.getUid(), BagItemType.Weapons.getCodeMessage(), itemBase.getName());
            }
            break;
            //case 2: {
            //    /* *
            //     * 首饰
            //     */
            //    logger.info("[UID:{}] 使用[{}] {}", session.getUid(), BagItemType.Jewelry.getCodeMessage(), itemBase.getName());
            //}
            //break;
        }
    }
}
