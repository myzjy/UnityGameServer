package com.gameServer.commonRefush.ormEntity;

import com.zfoo.orm.model.IEntity;
import com.zfoo.storage.anno.Id;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/8/9 15 13
 */
public class StageMissionEntity implements IEntity<Integer> {
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
    
    private String createAt;
    protected String updateAt;
    
    
    @Override
    public Integer id() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(int puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFailedText() {
        return failedText;
    }

    public void setFailedText(String failedText) {
        this.failedText = failedText;
    }

    public String getOutGameSuffix() {
        return outGameSuffix;
    }

    public void setOutGameSuffix(String outGameSuffix) {
        this.outGameSuffix = outGameSuffix;
    }

    public String getFormatText() {
        return formatText;
    }

    public void setFormatText(String formatText) {
        this.formatText = formatText;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
    public static StageMissionEntity valueOf(){
        return new StageMissionEntity();
    }
}
