package com.fzn.classsign.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classjoin {
    public Integer getCjid() {
        return cjid;
    }

    public void setCjid(Integer cjid) {
        this.cjid = cjid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Expose(serialize = false , deserialize = false)
    @SerializedName("cjid")
    private Integer cjid;
    @SerializedName("cid")
    private Integer cid;
    @SerializedName("uid")
    private Integer uid;
}
