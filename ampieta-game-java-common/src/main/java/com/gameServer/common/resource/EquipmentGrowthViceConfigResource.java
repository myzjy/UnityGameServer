package com.gameServer.common.resource;

import com.zfoo.storage.anno.Id;
import com.zfoo.storage.anno.Storage;

/**
 * 圣遗物 副属性 初始
 *
 * @author zjy
 * @version 1.0
 * @since 2023/10/5 01 20
 */
@Storage
public class EquipmentGrowthViceConfigResource {
    /**
     * id
     */
    @Id
    private int viceId;
    /**
     * 详细属性
     */
    private String viceName;
    /**
     * 属性所属pos对应
     */
    private int PosGrowthType;
    /**
     * 副属性的初始值数组
     */
    private String initNums;

    public int getViceId() {
        return viceId;
    }

    public String getViceName() {
        return viceName;
    }

    public int getPosGrowthType() {
        return PosGrowthType;
    }

    public String getInitNums() {
        return initNums;
    }
}
