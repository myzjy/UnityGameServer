package com.unitygameServer.model;

/**
 * @author zjy
 * @version 0.1
 * @since 11:15 下午
 */
public class UserModel {
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getToKen() {
        return toKen;
    }

    public void setToKen(String toKen) {
        this.toKen = toKen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    private long uid;

    private String toKen;
}
