package com.gameServer.home.serverConfig.controller;

import com.gameServer.common.constant.I18nEnum;
import com.gameServer.common.ormEntity.*;
import com.gameServer.common.protocol.equipment.base.*;
import com.gameServer.common.protocol.riqueza.RefreshingResourcesMainRequest;
import com.gameServer.common.protocol.riqueza.RefreshingResourcesMainResponse;
import com.gameServer.common.protocol.serverConfig.ItemBaseData;
import com.gameServer.common.protocol.serverConfig.ServerConfigRequest;
import com.gameServer.common.protocol.serverConfig.ServerConfigResponse;
import com.gameServer.common.resource.ItemBaseCsvResource;
import com.gameServer.home.equipment.service.IEquipmentService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 服务器
 */
@Component
public class ServerBaseConfigController {
    private static final Logger logger = LoggerFactory.getLogger(ServerBaseConfigController.class);
    /**
     * 背包基础
     */
    @StorageAutowired
    public StorageInt<Integer, ItemBaseCsvResource> itemCsvResources;
    @Autowired
    private IUserLoginService userLoginService;
    @Autowired
    private IEquipmentService iEquipmentService;

    @PacketReceiver
    public void atServerConfigRequest(Session session, ServerConfigRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("调用 protocolId: {},{}", request.protocolId(), ServerBaseConfigController.class);
        if (session.getUid() < 1) {
            logger.error("[protocolId:{}]当前请求在未登录就请求，请先登录", request.protocolId());
            NetContext.getRouter().send(session,
                                        Error.valueOf(request.protocolId(), 0, I18nEnum.error_start_login_not_setuid.getMessage()),
                                        gatewayAttachment);
            return;
        }
        var bagUserItemEntities = new ArrayList<ItemBaseData>();
        itemCsvResources.getData().forEach((id, item) -> {
            bagUserItemEntities.add(ItemBaseData.ValueOf(item));
        });
        var equipmentBaseServerList = new ArrayList<EquipmentBaseData>();
        var equipmentBaseList = OrmContext.getQuery(EquipmentBaseDataEntity.class).queryAll();
        equipmentBaseList.forEach((item) -> {
            equipmentBaseServerList.add(iEquipmentService.CreateEquipmentBaseData(item));
        });
        var equipmentDesBaseDataServerList = new ArrayList<EquipmentDesBaseData>();
        var equipmentDesBaseDataList = OrmContext.getQuery(EquipmentDesBaseDataEntity.class).queryAll();
        equipmentDesBaseDataList.forEach((data) -> {
            equipmentDesBaseDataServerList.add(iEquipmentService.CreateEquipmentDesBaseData(data));
        });
        var equipmentPrimaryConfigDataServerList = new ArrayList<EquipmentPrimaryConfigBaseData>();
        var equipmentPrimaryConfigBaseDataList = OrmContext.getQuery(EquipmentPrimaryConfigDataEntity.class).queryAll();
        equipmentPrimaryConfigBaseDataList.forEach((data) -> {
            equipmentPrimaryConfigDataServerList.add(iEquipmentService.CreateEquipmentPrimaryConfigBaseData(data));
        });
        var equipmentGrowthConfigBaseDataServerList = new ArrayList<EquipmentGrowthConfigBaseData>();
        var equipmentGrowthConfigList = OrmContext.getQuery(EquipmentGrowthConfigEntity.class).queryAll();
        equipmentGrowthConfigList.forEach((data) -> {
            equipmentGrowthConfigBaseDataServerList.add(iEquipmentService.CreateEquipmentGrowthConfigBaseData(data));
        });
        var equipmentGrowthViceConfigDataServerList = new ArrayList<EquipmentGrowthViceConfigBaseData>();
        var equipmentGrowthViceConfigDataList = OrmContext.getQuery(EquipmentGrowthViceConfigDataEntity.class).queryAll();
        equipmentGrowthViceConfigDataList.forEach((data) -> {
            equipmentGrowthViceConfigDataServerList.add(iEquipmentService.CreateEquipmentGrowthViceConfigBaseData(data));
        });
        var equipmentConfigDataServerList = new ArrayList<EquipmentConfigBaseData>();
        var equipmentConfigBaseDataList = OrmContext.getQuery(EquipmentConfigDataEntity.class).queryAll();
        equipmentConfigBaseDataList.forEach((data) -> {
            equipmentConfigDataServerList.add(iEquipmentService.CreateEquipmentConfigBaseData(data));
        });
        var response = ServerConfigResponse.ValueOf();
        response.setBagItemEntityList(bagUserItemEntities);
        response.setEquipmentBaseDataList(equipmentBaseServerList);
        response.setEquipmentDesBaseDataList(equipmentDesBaseDataServerList);
        response.setEquipmentPrimaryConfigBaseDataList(equipmentPrimaryConfigDataServerList);
        response.setEquipmentGrowthConfigBaseDataList(equipmentGrowthConfigBaseDataServerList);
        response.setEquipmentGrowthViceConfigBaseDataList(equipmentGrowthViceConfigDataServerList);
        response.setEquipmentConfigBaseDataList(equipmentConfigDataServerList);
        logger.info("ServerConfigResponse:{}", JsonUtils.object2String(response));
        NetContext.getRouter().send(session, response, gatewayAttachment);
    }

    @PacketReceiver
    public void atRefreshingResourcesMainRequest(Session session, RefreshingResourcesMainRequest request,
                                                 GatewayAttachment gatewayAttachment) {
        logger.info("刷新主界面上面的金币 钻石 付费钻石 调用protocol id：{}", request.protocolId());
        var user = userLoginService.LoadPlayerUserEntity(session.getUid());
        if (user == null) {
            // 没查询到 对应角色 报错提示
            logger.error("数据库中查询角色id：{},出现错误", session.getUid());
            NetContext.getRouter().send(session,
                                        Error.valueOf(request.protocolId(), 0, I18nEnum.error_account_not_exit.getMessage()), gatewayAttachment);
            return;
        }
        // 查询到角色 返回 金币 钻石 付费钻石
        var data = RefreshingResourcesMainResponse.ValueOf(user.getGoldNum(), user.getPremiumDiamondNum(),
                                                           user.getDiamondNum());
        logger.info("RefreshingResourcesMainResponse:{}", JsonUtils.object2String(data));
        NetContext.getRouter().send(session, data, gatewayAttachment.getSignalAttachment());
    }
}
