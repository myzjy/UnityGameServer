package com.gameServer.home.puzzle.controller;

import com.gameServer.commonRefush.entity.PuzzleEntity;
import com.gameServer.commonRefush.protocol.Puzzle.Puzzle;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleAllConfigRequest;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleAllConfigResponse;
import com.gameServer.commonRefush.protocol.Puzzle.PuzzleRewardsData;
import com.zfoo.net.NetContext;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PacketReceiver
    public void atPuzzleAllConfigRequest(Session session, PuzzleAllConfigRequest puzzleAllConfigRequest, GatewayAttachment gatewayAttachment) {
        //不是活动地图相关
        if (puzzleAllConfigRequest.getEventId() < 1) {
            var puzzleConfig = OrmContext.getQuery(PuzzleEntity.class).queryAll();
            var packet = PuzzleAllConfigResponse.ValueOf();
            List<Puzzle> puzzleList = getPuzzleList(puzzleConfig);
            /**
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

    private List<Puzzle> getPuzzleList(List<PuzzleEntity> puzzleConfig) {
        List<Puzzle> puzzleList = new ArrayList<>();
        for (var data : puzzleConfig) {
            Puzzle puzzle = new Puzzle();
            puzzle.setId(data.getId());
            puzzle.setIcon(data.getIcon());
            puzzle.setResourcePath(data.getResourceStr());
            puzzle.setPuzzleName(data.getPuzzleName());
            puzzle.setLastPuzzleID(data.getLastPuzzleID());
            puzzle.setNextPuzzleID(data.getNextPuzzleID());
            List<PuzzleRewardsData> rewardsDataList = new ArrayList<>();
            var rewardStr = data.getPuzzleRewards();
            var rewardSplit = rewardStr.split(";");
            for (var i = 0; i < rewardSplit.length; i++) {
                var rewardValueStr = rewardSplit[i];
                var rewardValueStrSplit = rewardValueStr.split(":");
                /**
                 * 
                 */
                var rewardValueStr1 = rewardValueStrSplit[0];
                var rewardValueStr2 = rewardValueStrSplit[1];
            }
            puzzle.setPuzzleRewardsDatas((PuzzleRewardsData[]) rewardsDataList.toArray());
            puzzleList.add(puzzle);
        }
        return puzzleList;
    }
}
