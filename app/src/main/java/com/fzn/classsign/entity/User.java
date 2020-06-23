package com.fzn.classsign.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Expose(serialize = false , deserialize = false)
    @SerializedName("uid")
    private Integer uid;
    @SerializedName("phone")
    private String phone;
    @SerializedName("uNum")
    private String uNum;
    @SerializedName("name")
    private String name;
    @SerializedName("sex")
    private String sex;
    @SerializedName("password")
    private String password;
}
