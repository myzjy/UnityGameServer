package com.gameServer.common.protocol.character;

import com.zfoo.protocol.anno.Protocol;

/**
 * 角色 队伍
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/11 18 55
 */
@Protocol(id = 219)
public class GameMainTeamCharacterIDData {
    /**
     * 角色 id
     */
    private int characterId;
    /**
     * 玩家 id
     */
    private int uid;
    /**
     * 这个角色在 队伍 的 第几位
     */
    private int teamIndex;
    /**
     * 这个角色是否出战 为主控
     */
    private int fightTeamIndex;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTeamIndex() {
        return teamIndex;
    }

    public void setTeamIndex(int teamIndex) {
        this.teamIndex = teamIndex;
    }

    public int getFightTeamIndex() {
        return fightTeamIndex;
    }

    public void setFightTeamIndex(int fightTeamIndex) {
        this.fightTeamIndex = fightTeamIndex;
    }

    public static GameMainTeamCharacterIDData valueOf() {
        return new GameMainTeamCharacterIDData();
    }

    public static GameMainTeamCharacterIDData valueOf(int characterId, int uid, int teamIndex, int fightTeamIndex) {
        var data = new GameMainTeamCharacterIDData();
        data.setCharacterId(characterId);
        data.setUid(uid);
        data.setTeamIndex(teamIndex);
        data.setFightTeamIndex(fightTeamIndex);
        return data;
    }
}
