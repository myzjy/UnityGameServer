package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/9 11 52
 */
@Storage
public class StageMissionResource {
    @Id
    private int id;
    /**
     * 当前任务完成排序对比任务id
     */
    private int order;
    /**
     * 对应的关卡id
     */
    private int puzzleId;
    /**
     * 标题
     */
    private String title;
    /**
     * 显示
     */
    private String shortText;
    /**
     * 错误显示消息
     */
    private String failedText;
    /**
     * 标识
     */
    private String outGameSuffix;
    /**
     * 显示信息
     */
    private  String formatText;
    

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public String getTitle() {
        return title;
    }

    public String getShortText() {
        return shortText;
    }

    public String getFailedText() {
        return failedText;
    }

    public String getOutGameSuffix() {
        return outGameSuffix;
    }

    public String getFormatText() {
        return formatText;
    }

    
}
