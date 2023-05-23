package com.gameServer.home.serverConfig.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.riqueza.RefresqueARiquezaRequest;
import com.gameServer.commonRefush.protocol.riqueza.RefresqueARiquezaResponse;
import com.gameServer.commonRefush.protocol.serverConfig.ItemBaseData;
import com.gameServer.commonRefush.protocol.serverConfig.ServerConfigRequest;
import com.gameServer.commonRefush.protocol.serverConfig.ServerConfigResponse;
import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.accessor.MongodbAccessor;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器
 */
@Component
public class ServerBaseConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ServerBaseConfigController.class);


    /**
     * 背包基础
     */
    @ResInjection
    public Storage<Integer, ItemBaseCsvResource> itemCsvResources;

    @PacketReceiver
    public void atServerConfigRequest(Session session, ServerConfigRequest request, GatewayAttachment gatewayAttachment) {
        if (session.getUid() < 1) {
            logger.error("[protocolId:{}]当前请求在未登录就请求，请先登录", request.protocolId());
            NetContext.getRouter().send(session,
                    Error.valueOf(request.protocolId(), 0, I18nEnum.error_start_login_not_setuid.getMessage()), gatewayAttachment);
            return;
        }
        logger.info("[UID:{}][sid:{}]atServerConfigRequest", session.getUid(), session.getSid());
        logger.info("[UID:{}][sid:{}][itemCsvResources.count：{}]", session.getUid(), session.getSid(), itemCsvResources.size());
        List<ItemBaseData> bagUserItemEntities = new ArrayList<>();
        itemCsvResources.getData().forEach((id, item) -> {

            bagUserItemEntities.add(ItemBaseData.ValueOf(item));
        });
        NetContext.getRouter().send(session, ServerConfigResponse.ValueOf(bagUserItemEntities), gatewayAttachment);
    }

    @PacketReceiver
    public void atRefresqueARiquezaRequest(Session session, RefresqueARiquezaRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("刷新主界面上面的金币 钻石 付费钻石 调用protocol id：{}", request.protocolId());
        var user = OrmContext.getAccessor().load(session.getUid(), PlayerUserEntity.class);
        if (user == null) {
            //没查询到 对应角色 报错提示
            logger.error("数据库中查询角色id：{},出现错误", session.getUid());
            NetContext.getRouter().send(session,
                    Error.valueOf(request.protocolId(), 0, I18nEnum.error_account_not_exit.getMessage()), gatewayAttachment);
            return;
        }
        //查询到角色 返回 金币 钻石 付费钻石
        var data = RefresqueARiquezaResponse.ValueOf(user.getGoldNum(), user.getPremiumDiamondNum(), user.getDiamondNum());
        NetContext.getRouter().send(session, data, gatewayAttachment.getSignalAttachment());
    }
}