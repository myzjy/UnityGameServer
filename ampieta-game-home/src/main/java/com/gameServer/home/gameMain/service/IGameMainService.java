package com.gameServer.home.gameMain.service;

import com.gameServer.common.entity.character.GameMainTeamCharacterListEntity;

public interface IGameMainService {
    /**
     * 初始创建 玩家角色队伍列表
     *
     * @param playerId 初始角色id
     * @param uid      玩家id
     * @return 返回队伍列表 可以插入数据库结构
     */
    GameMainTeamCharacterListEntity CreateInitGameMainTeamCharacterListEntity(int playerId, long uid);
}
