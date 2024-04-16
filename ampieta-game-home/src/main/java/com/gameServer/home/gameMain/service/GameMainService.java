package com.gameServer.home.gameMain.service;

import com.gameServer.common.entity.character.GameMainTeamCharacterListEntity;
import com.gameServer.common.entity.composite.GameMainTeamCharacterCompositeDataID;
import com.gameServer.common.protocol.character.GameMainTeamCharacterIDData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * GameMain 相关 service
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/16 18 59
 */
@Component
public class GameMainService implements IGameMainService {
    @Override
    public GameMainTeamCharacterListEntity CreateInitGameMainTeamCharacterListEntity(int playerId, long uid) {
        GameMainTeamCharacterCompositeDataID gameMainTeamCharacterIDData = GameMainTeamCharacterCompositeDataID.valueOf();
        gameMainTeamCharacterIDData.setCharacterId(playerId);
        gameMainTeamCharacterIDData.setTeamIndex(0);
        gameMainTeamCharacterIDData.setFightTeamIndex(0);
        gameMainTeamCharacterIDData.setUid(uid);
        List<GameMainTeamCharacterCompositeDataID> gameMainTeamCharacterIDDataList = new ArrayList<>();
        gameMainTeamCharacterIDDataList.add(GameMainTeamCharacterCompositeDataID.valueOf());
        gameMainTeamCharacterIDDataList.add(GameMainTeamCharacterCompositeDataID.valueOf());
        gameMainTeamCharacterIDDataList.add(GameMainTeamCharacterCompositeDataID.valueOf());
        gameMainTeamCharacterIDDataList.add(GameMainTeamCharacterCompositeDataID.valueOf());
        GameMainTeamCharacterListEntity entity = GameMainTeamCharacterListEntity.valueOf();
        entity.setTeamCharacter4(gameMainTeamCharacterIDDataList);
        entity.setTeamCharacter3(gameMainTeamCharacterIDDataList);
        entity.setTeamCharacter2(gameMainTeamCharacterIDDataList);
        gameMainTeamCharacterIDDataList.set(0, gameMainTeamCharacterIDData);
        entity.setTeamCharacter(gameMainTeamCharacterIDDataList);
        entity.setId(uid);
        return entity;
    }
}
