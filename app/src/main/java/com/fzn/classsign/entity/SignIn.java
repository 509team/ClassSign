package com.fzn.classsign.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignIn {
    @Expose(serialize = false , deserialize = false)
    @SerializedName("sid")
    private Integer sid;
    @SerializedName("ssid")
    private Integer ssid;
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("uNum")
    private String uNum;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getSsid() {
        return ssid;
    }

    public void setSsid(Integer ssid) {
        this.ssid = ssid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getuNum() {
        return uNum;
    }

    public void setuNum(String uNum) {
        this.uNum = uNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
