package com.gameServer.commonRefush.protocol.Puzzle;

import com.zfoo.storage.model.anno.Id;

/**
 * 地图基础类
 * @author zjy
 * @version 1.0
 * @since 2023/7/25 16 49
 */
public class Puzzle {
    @Id
    private int id;
    /**
     * 关卡名字
     */
    private String puzzleName;
    /**
     * 关联到 上一个关卡id
     */
    private int lastPuzzleID;
    
    
}
