package com.fzn.classsign.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInStatistics {
    public Integer getSsid() {
        return ssid;
    }

    public void setSsid(Integer ssid) {
        this.ssid = ssid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getCheckInNum() {
        return checkInNum;
    }

    public void setCheckInNum(Integer checkInNum) {
        this.checkInNum = checkInNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Expose(serialize = false , deserialize = false)
    @SerializedName("ssid")
    private Integer ssid;
    @SerializedName("cid")
    private Integer cid;
    @SerializedName("name")
    private String name;
    @SerializedName("startTime")
    private Long startTime;
    @SerializedName("checkInNum")
    private Integer checkInNum;
    @SerializedName("total")
    private Integer total;
    @SerializedName("status")
    private String status;
}
