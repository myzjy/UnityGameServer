package com.gameServer.home.puzzle.controller;

import com.gameServer.commonRefush.entity.ItemBoxBaseEntity;
import com.gameServer.commonRefush.entity.PuzzleEntity;
import com.gameServer.commonRefush.protocol.Puzzle.Puzzle;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleAllConfigRequest;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleAllConfigResponse;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleRewardsData;
import com.gameServer.home.puzzle.service.IPuzzleService;
import com.zfoo.net.NetContext;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/1 15 27
 */
@Component
public class puzzleController {
    private static final Logger logger = LoggerFactory.getLogger(puzzleController.class);
    @Autowired
    private IPuzzleService iPuzzleService;

    @PacketReceiver
    public void atPuzzleAllConfigRequest(Session session, PuzzleAllConfigRequest puzzleAllConfigRequest, GatewayAttachment gatewayAttachment) {
        //不是活动地图相关
        if (puzzleAllConfigRequest.getEventId() < 1) {
            //获取 实时性
            var puzzleConfig = OrmContext.getQuery(PuzzleEntity.class).queryAll();
            var packet = PuzzleAllConfigResponse.ValueOf();
            List<Puzzle> puzzleList = iPuzzleService.GetTheMapServiceDataList(puzzleConfig);
            /**L
             * 设置 地图 基础属性配置
             */
            packet.setPuzzleConfigList(puzzleList);
            NetContext.getRouter().send(session, packet, gatewayAttachment);
            return;
        }
        /**
         * 活动 相关 请求配置器
         */
    }

    
}
