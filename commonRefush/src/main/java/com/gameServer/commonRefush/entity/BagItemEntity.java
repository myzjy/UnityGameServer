package com.gameServer.commonRefush.entity;

import com.gameServer.commonRefush.resource.BagItem;
import com.zfoo.orm.model.entity.IEntity;
import com.zfoo.storage.model.anno.Id;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/11/20 2:07
 */
public class BagItemEntity implements IEntity<Integer> {
    @Id
    private int _id;
    private String _introduce;
    private int maxNum;
    private int minNum;
    private int type;
    private String _name;

    public static BagItemEntity valueOf(BagItem item) {
        BagItemEntity value = new BagItemEntity();
        value.set_id(item.get_id());
        value.set_introduce(item.get_introduce());
        value.set_name(item.get_name());
        value.setType(item.getType());
        value.setMaxNum(item.getMaxNum());
        value.setMinNum(item.getMinNum());
        return value;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_introduce() {
        return _introduce;
    }

    public void set_introduce(String _introduce) {
        this._introduce = _introduce;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    @Override
    public Integer id() {
        return this._id;
    }

}
