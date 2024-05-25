package com.gameServer.common.protocol.Puzzle;

import com.gameServer.common.attribute.grounding.MonsterData;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/8 16 20
 */
@Protocol(id = 1038)
public class PuzzleLevelBattleResponse implements IPacket {
    /**
     * 当前关卡名字
     */
    @Note("当前关卡名字")
    private String levelStr;
    /**
     * 怪物数据
     */
    @Note("怪物数据")
    private MonsterData[] monsterStr;
}
