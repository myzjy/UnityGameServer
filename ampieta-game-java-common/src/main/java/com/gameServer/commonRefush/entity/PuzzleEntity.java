package com.gameServer.commonRefush.entity;

import com.zfoo.orm.model.anno.Id;
import com.zfoo.orm.model.entity.IEntity;

/**
 * 关卡基础
 * @author zjy
 * @version 1.0
 * @since 2023/7/25 19 41
 */

public class PuzzleEntity implements IEntity<Integer> {

   

    /**
     * 关卡ID
     */
    @Id
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    public int getLastPuzzleID() {
        return lastPuzzleID;
    }

    public void setLastPuzzleID(int lastPuzzleID) {
        this.lastPuzzleID = lastPuzzleID;
    }

    public int getNextPuzzleID() {
        return nextPuzzleID;
    }

    public void setNextPuzzleID(int nextPuzzleID) {
        this.nextPuzzleID = nextPuzzleID;
    }

    public String getPuzzleRewards() {
        return puzzleRewards;
    }

    public void setPuzzleRewards(String puzzleRewards) {
        this.puzzleRewards = puzzleRewards;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public void setIosVersion(String iosVersion) {
        this.iosVersion = iosVersion;
    }

    public String getPcEditorVersion() {
        return pcEditorVersion;
    }

    public void setPcEditorVersion(String pcEditorVersion) {
        this.pcEditorVersion = pcEditorVersion;
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
    public static PuzzleEntity ValueOf(){
        var packet=new PuzzleEntity();
        return packet;
    }
}
