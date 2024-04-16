package com.gameServer.common.entity.character;

import com.gameServer.common.entity.composite.GameMainTeamCharacterCompositeDataID;
import com.zfoo.orm.anno.Id;
import com.zfoo.orm.model.IEntity;

import java.util.List;

/**
 * 队伍列表
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/15 23 44
 */
public class GameMainTeamCharacterListEntity implements IEntity<Long> {
    @Id
    private long id;
    /**
     * 第一个队伍
     */
    private List<GameMainTeamCharacterCompositeDataID> teamCharacter;
    /**
     * 第2个队伍
     */
    private List<GameMainTeamCharacterCompositeDataID> teamCharacter2;
    /**
     * 第3个队伍
     */
    private List<GameMainTeamCharacterCompositeDataID> teamCharacter3;
    /**
     * 第4个队伍
     */
    private List<GameMainTeamCharacterCompositeDataID> teamCharacter4;

    public static GameMainTeamCharacterListEntity valueOf() {
        return new GameMainTeamCharacterListEntity();
    }

    @Override
    public Long id() {
        return id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<GameMainTeamCharacterCompositeDataID> getTeamCharacter() {
        return teamCharacter;
    }

    public void setTeamCharacter(List<GameMainTeamCharacterCompositeDataID> teamCharacter) {
        this.teamCharacter = teamCharacter;
    }

    public List<GameMainTeamCharacterCompositeDataID> getTeamCharacter2() {
        return teamCharacter2;
    }

    public void setTeamCharacter2(List<GameMainTeamCharacterCompositeDataID> teamCharacter2) {
        this.teamCharacter2 = teamCharacter2;
    }

    public List<GameMainTeamCharacterCompositeDataID> getTeamCharacter3() {
        return teamCharacter3;
    }

    public void setTeamCharacter3(List<GameMainTeamCharacterCompositeDataID> teamCharacter3) {
        this.teamCharacter3 = teamCharacter3;
    }

    public List<GameMainTeamCharacterCompositeDataID> getTeamCharacter4() {
        return teamCharacter4;
    }

    public void setTeamCharacter4(List<GameMainTeamCharacterCompositeDataID> teamCharacter4) {
        this.teamCharacter4 = teamCharacter4;
    }
}
