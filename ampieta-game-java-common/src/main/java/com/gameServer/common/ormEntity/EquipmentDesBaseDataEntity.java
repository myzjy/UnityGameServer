package com.gameServer.common.ormEntity;

import com.zfoo.orm.anno.Id;
import com.zfoo.orm.anno.Index;
import com.zfoo.orm.model.IEntity;

/**
 * 圣遗物介绍基础信息
 *
 * @author zjy
 * @version 1.0
 * @since 2023/11/10 14 25
 */
public class EquipmentDesBaseDataEntity implements IEntity<Integer> {
    /**
     * id uuid
     */
    @Id
    @Index(ascending = false, unique = false)
    private int id;
    /**
     * 介绍id
     */
    private int desId;
    /**
     * 这个介绍 圣遗物的名字
     */
    private String name;
    /**
     * 介绍
     */
    private String desStr;
    /**
     * 故事
     */
    private String storyDesStr;
    /**
     * 当前数据创建时间
     */
    private String createAt;
    /**
     * 当前数据更新时间
     */
    private String updateAt;
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

    public int getDesId() {
        return desId;
    }

    public void setDesId(int desId) {
        this.desId = desId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesStr() {
        return desStr;
    }

    public void setDesStr(String desStr) {
        this.desStr = desStr;
    }

    public String getStoryDesStr() {
        return storyDesStr;
    }

    public void setStoryDesStr(String storyDesStr) {
        this.storyDesStr = storyDesStr;
    }

    public static EquipmentDesBaseDataEntity valueOf() {
        return new EquipmentDesBaseDataEntity();
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
