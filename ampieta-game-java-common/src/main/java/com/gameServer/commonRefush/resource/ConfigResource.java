package com.gameServer.commonRefush.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/6 23 40
 */
@Storage
public class ConfigResource {
    @Id
    @Index
    private int lv;

    public int getLv() {
        return lv;
    }

    public int getMaxPhysical() {
        return maxPhysical;
    }

    public int getResidueTime() {
        return residueTime;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public boolean isTheLock() {
        return isTheLock;
    }

    /**
     * 最大体力
     */
    private int maxPhysical;
    /**
     * 恢复时间
     */
    private int residueTime;
    /**
     * 最大经验
     */
    private  int maxExp;
    private boolean isTheLock;
}
