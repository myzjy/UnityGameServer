package com.gameServer.common.protocol.Puzzle;

import com.gameServer.common.attribute.grounding.MonsterData;
import com.zfoo.net.packet.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/8 16 20
 */
public class PuzzleLevelBattleResponse implements IPacket {
    /**
     * 当前关卡名字
     */
    private String levelStr;
    /**
     * 怪物 数据
     */
    private MonsterData[] monsterStr;
}
