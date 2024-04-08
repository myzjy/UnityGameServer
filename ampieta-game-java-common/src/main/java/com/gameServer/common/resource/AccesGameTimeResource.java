package com.gameServer.common.resource;


import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Index;
import com.zfoo.storage.anno.Storage;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/11 15 10
 */
@Storage
public class AccesGameTimeResource {
    @Id
    private int timeID;

    public int getTimeID() {
        return timeID;
    }


    public long getTime() {
        return time;
    }

    /***/
    private long time;
}
