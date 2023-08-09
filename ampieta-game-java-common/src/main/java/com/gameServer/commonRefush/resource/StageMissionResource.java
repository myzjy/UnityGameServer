package com.gameServer.commonRefush.resource;

import com.zfoo.storage.model.anno.Id;
import com.zfoo.storage.model.anno.Resource;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/9 11 52
 */
@Resource(alias = "StageMissionResource")
public class StageMissionResource {
    @Id
    protected int id;
    /**
     * 当前任务完成排序对比任务id
     */
    protected int order;
    protected int puzzleId;
    protected String title;
    protected String shortText;
    protected String failedText;
    protected String outGameSuffix;

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

    protected  String formatText;
    
}
