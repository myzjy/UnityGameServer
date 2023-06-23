package com.gameServer.commonRefush.resource;

import com.zfoo.storage.model.anno.Id;
import com.zfoo.storage.model.anno.Index;
import com.zfoo.storage.model.anno.Resource;

import java.util.Date;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/11 15 10
 */
@Resource(alias="AccesGameTimeResource")
public class AccesGameTimeResource {
    @Id
    @Index
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
