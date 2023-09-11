package com.gameServer.commonRefush.protocol.Puzzle;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 关卡点击进入战斗场景
 *
 * @author zjy
 * @version 1.0
 * @since 2023/9/8 14 00
 */
@Protocol(id = 1037)
public class PuzzleLevelBattleRequest implements IPacket {
    /**
     * 关卡id
     */
    private int puzzleId;
    private long uid;

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public static PuzzleLevelBattleRequest ValueOf(int puzzleId, long uid) {
        var request = new PuzzleLevelBattleRequest();
        request.setPuzzleId(puzzleId);
        request.setUid(uid);
        return request;
    }
}
