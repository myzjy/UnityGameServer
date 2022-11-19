package com.gameServer.commonRefush.resource;

import com.zfoo.storage.model.anno.Id;
import com.zfoo.storage.model.anno.Resource;

/**
 * 道具类
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/11/19 21:21
 */
@Resource
public class BagItem {
    @Id
    private int _id;
    private String _introduce;
    private int maxNum;
    private int minNum;
    private int type;
    private String _name;

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
}
