package com.fzn.classsign.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class {
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getcNum() {
        return cNum;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Expose(serialize = false , deserialize = false)
    @SerializedName("cid")
    private Integer cid;
    @SerializedName("cNum")
    private String cNum;
    @SerializedName("name")
    private String name;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("tName")
    private String tName;
    @SerializedName("joinCode")
    private String joinCode;
    @SerializedName("total")
    private Integer total;
}
