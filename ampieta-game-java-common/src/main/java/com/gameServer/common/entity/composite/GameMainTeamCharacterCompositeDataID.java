package com.gameServer.common.entity.composite;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/15 23 50
 */
public class GameMainTeamCharacterCompositeDataID implements Comparable<GameMainTeamCharacterCompositeDataID> {
    /**
     * 角色 id
     */
    private int characterId;
    /**
     * 玩家 id
     */
    private long uid;
    /**
     * 这个角色在 队伍 的 第几位
     */
    private int teamIndex;
    /**
     * 这个角色是否出战 为主控
     */
    private int fightTeamIndex;

    @Override
    public int compareTo(GameMainTeamCharacterCompositeDataID o) {
        return 0;
    }

    public static GameMainTeamCharacterCompositeDataID valueOf() {
        return new GameMainTeamCharacterCompositeDataID();
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
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
}
