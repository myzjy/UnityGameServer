package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/26 00 10
 */
@Storage
public class PuzzleResource {
 
    @Id
    @Index
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
    private String icon;
    /**
     * 资源路径
     */
    private String resourcePath;

    public PuzzleResource() {
    }

    public int getId() {
        return id;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public int getLastPuzzleID() {
        return lastPuzzleID;
    }

    public int getNextPuzzleID() {
        return nextPuzzleID;
    }

    public String getPuzzleRewards() {
        return puzzleRewards;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public String getPcEditorVersion() {
        return pcEditorVersion;
    }


    public String getIcon() {
        return icon;
    }
    

    public String getResourcePath() {
        return resourcePath;
    }
    
}
