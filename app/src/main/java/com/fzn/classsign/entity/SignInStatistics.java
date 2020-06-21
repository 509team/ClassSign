package com.fzn.classsign.entity;

public class SignInStatistics {
    public Integer getSsid() {
        return ssid;
    }

    public void setSsid(Integer ssid) {
        this.ssid = ssid;
    }

    private Integer ssid;

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

    private Integer cid;
    private String name;
    private Long startTime;
    private Integer checkInNum;
    private Integer total;
    private String status;
}
