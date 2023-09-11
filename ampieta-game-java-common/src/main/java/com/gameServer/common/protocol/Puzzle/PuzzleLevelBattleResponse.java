package com.gameServer.common.protocol.Puzzle;

import com.zfoo.net.packet.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/8 16 20
 */
public class PuzzleLevelBattleResponse implements IPacket {
    /**
     *
     */
    private String levelStr;
    /**
     * 怪物 数据
     */
    private String monsterStr;
}
