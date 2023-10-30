package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.EntityCache;
import com.zfoo.orm.anno.Index;
import com.zfoo.orm.model.IEntity;
import com.zfoo.storage.anno.Id;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/5 18 53
 */
@EntityCache
public class EquipmentGrowthViceConfigDataEntity implements IEntity<Integer> {
    @Id
    @Index(ascending = true, unique = true)
    private int id;
    /**
     * id
     */
    private int viceId;
    /**
     * 详细属性
     */
    private String viceName;
    /**
     * 属性所属pos对应
     */
    private int posGrowthType;
    /**
     * 副属性的初始值数组
     */

    private List<String> initNums;
    /**
     * 创建时间
     */
    private String createAt;
    /**
     * 更新时间
     */
    private String updateAt;

    @Override
    public Integer id() {
        return viceId;
    }
    public static EquipmentGrowthViceConfigDataEntity ValueOf(int viceId,String viceName,int posGrowthType,List<String> initNums){
        var data=new EquipmentGrowthViceConfigDataEntity();
        data.setViceId(viceId);
        data.setViceName(viceName);
        data.setPosGrowthType(posGrowthType);
        data.setInitNums(initNums);
        return data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViceId() {
        return viceId;
    }

    public void setViceId(int viceId) {
        this.viceId = viceId;
    }

    public String getViceName() {
        return viceName;
    }

    public void setViceName(String viceName) {
        this.viceName = viceName;
    }

    public int getPosGrowthType() {
        return posGrowthType;
    }

    public void setPosGrowthType(int posGrowthType) {
        this.posGrowthType = posGrowthType;
    }

    public List<String> getInitNums() {
        return initNums;
    }

    public void setInitNums(List<String> initNums) {
        this.initNums = initNums;
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
}
