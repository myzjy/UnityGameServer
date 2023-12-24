package com.gameServer.home.equipment.controller;

import com.gameServer.common.ormEntity.EquipmentUserDataOrmEntity;
import com.gameServer.common.protocol.equipment.EquipmentAllDataRequest;
import com.gameServer.common.protocol.equipment.EquipmentAllDataResponse;
import com.gameServer.common.protocol.equipment.EquipmentData;
import com.gameServer.common.protocol.equipment.EquipmentGlossaryData;
import com.gameServer.home.equipment.service.IEquipmentService;
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
 * @author zjy
 * @version 1.0
 * @since 2023/9/25 11 07
 */
@Component
public class EquipmentController {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);
    @Autowired
    private IEquipmentService iEquipmentService;

    public EquipmentController() {
        logger.info("[EquipmentController]");
    }

    @PacketReceiver
    public void atEquipmentAllDataRequest(Session session, EquipmentAllDataRequest request, GatewayAttachment gateway) {
        logger.info("获取所有圣遗物，请求者UID：{}", session.getUid());
        var userEquipmentList = iEquipmentService.GetAllTheUserToEquipmentUserDataOrm(session.getUid());
        List<EquipmentData> equipmentDataList = new ArrayList<>();
        for (var data : userEquipmentList) {
            var equipmentData = new EquipmentData();
            //主属性
            var primaryData = EquipmentGlossaryData.ValueOf(data.getThisPrimaryAttributes().getPosType(),
                                                            data.getThisPrimaryAttributes().getGrowthViceNum());
            equipmentData.setSubjectClauseEquipmentData(primaryData);
            List<EquipmentGlossaryData> equipmentGlossaryDataList = new ArrayList<>();
            for (var glossaryData : data.getInitNums()) {
                var mPrimaryData = EquipmentGlossaryData.ValueOf(glossaryData.getPosType(),
                                                                 glossaryData.getGrowthViceNum());
                equipmentGlossaryDataList.add(mPrimaryData);
            }
            equipmentData.setAdverbStripEquipmentDataList(equipmentGlossaryDataList);
            equipmentDataList.add(equipmentData);
        }
    }
}
