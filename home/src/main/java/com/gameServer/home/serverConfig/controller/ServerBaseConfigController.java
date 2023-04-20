package com.gameServer.home.serverConfig.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.protocol.serverConfig.ItemBaseData;
import com.gameServer.commonRefush.protocol.serverConfig.ServerConfigRequest;
import com.gameServer.commonRefush.protocol.serverConfig.ServerConfigResponse;
import com.gameServer.commonRefush.resource.ItemBaseCsvResource;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
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
    public void atServerConfigRequest(Session session, ServerConfigRequest request) {
        if (session.getUid() < 1) {
            logger.error("[protocolId:{}]当前请求在未登录就请求，请先登录", request.protocolId());
            NetContext.getRouter().send(session, Error.valueOf(request.protocolId(), 0, I18nEnum.error_start_login_not_setuid.getMessage()));
            return;
        }
        logger.info("[UID:{}][sid:{}]atServerConfigRequest", session.getUid(), session.getSid());
        logger.info("[UID:{}][sid:{}][itemCsvResources.count：{}]", session.getUid(), session.getSid(), itemCsvResources.size());
        List<ItemBaseData> bagUserItemEntities = new ArrayList<>();
        itemCsvResources.getData().forEach((id, item) -> {

            bagUserItemEntities.add(ItemBaseData.ValueOf(item));
        });
        NetContext.getRouter().send(session, ServerConfigResponse.ValueOf(bagUserItemEntities));
    }

}
