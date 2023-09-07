package com.gameServer.commonRefush.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/9 11 52
 */
@Storage
public class StageMissionResource {
    @Id
    protected int id;
    /**
     * 当前任务完成排序对比任务id
     */
    protected int order;
    /**
     * 对应的关卡id
     */
    protected int puzzleId;
    /**
     * 标题
     */
    protected String title;
    /**
     * 显示
     */
    protected String shortText;
    /**
     * 错误显示消息
     */
    protected String failedText;
    /**
     * 标识
     */
    protected String outGameSuffix;
    /**
     * 显示信息
     */
    protected  String formatText;
    

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
