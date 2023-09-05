package com.gameServer.commonRefush.ormEntity;

import com.zfoo.orm.model.entity.IEntity;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/4 15 55
 */
public class PuzzleChapterDataEntity implements IEntity<Integer> {
    /**
     * id
     */
    private int id;
    /**
     * 章节名
     */
    private String chapterName;
    /**
     * 当前章节 最小 关卡id
     */
    private int minPuzzle;
    /**
     * 当前章节 最大 关卡id
     */
    private int maxPuzzle;
    /**
     * 创建时间
     */
    private String createAt;
    /**
     * 更新时间
     */
    private String updatedAt;
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

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getMinPuzzle() {
        return minPuzzle;
    }

    public void setMinPuzzle(int minPuzzle) {
        this.minPuzzle = minPuzzle;
    }

    public int getMaxPuzzle() {
        return maxPuzzle;
    }

    public void setMaxPuzzle(int maxPuzzle) {
        this.maxPuzzle = maxPuzzle;
    }
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public static PuzzleChapterDataEntity ValueOf(int id, int minPuzzle, int maxPuzzle, String chapterName, String createAt, String updatedAt){
        var data=new PuzzleChapterDataEntity();
        data.setId(id);
        data.setMinPuzzle(minPuzzle);
        data.setMaxPuzzle(maxPuzzle);
        data.setChapterName(chapterName);
        data.setCreateAt(createAt);
        data.setUpdatedAt(updatedAt);
        return data;
    }
}
