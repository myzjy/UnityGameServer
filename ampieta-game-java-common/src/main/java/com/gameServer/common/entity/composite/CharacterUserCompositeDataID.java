package com.gameServer.common.entity.composite;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/16 23 25
 */
public class CharacterUserCompositeDataID implements Comparable<CharacterUserCompositeDataID> {
    /**
     * 角色id
     */
    private int characterId;
    /**
     * 玩家UID
     */
    private long uid;

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

    @Override
    public int compareTo(CharacterUserCompositeDataID o) {
        return 0;
    }
}
