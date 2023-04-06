package com.gameServer.commonRefush.resource;

import com.zfoo.storage.model.anno.Id;
import com.zfoo.storage.model.anno.Index;
import com.zfoo.storage.model.anno.Resource;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/6 23 40
 */
@Resource
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

    private int maxPhysical;
    private int residueTime;
    private  int maxExp;
    private boolean isTheLock;
}
