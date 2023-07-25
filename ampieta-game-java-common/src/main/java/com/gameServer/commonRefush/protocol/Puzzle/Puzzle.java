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

    /**
     * 下一个 关卡id 可用在 跳下一关
     */
    private int nextPuzzleID;

    /**
     * 关卡 奖励
     */
    private String puzzleRewards;
    /**
     * 安卓版本号
     */
    private String androidVersion;
    /**
     * IOS 版本号
     */
    private String iosVersion;
    /**
     * pc版本号
     */
    private String pcEditorVersion;
    
}
