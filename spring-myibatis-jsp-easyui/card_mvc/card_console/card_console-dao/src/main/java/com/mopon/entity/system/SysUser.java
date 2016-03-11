package com.mopon.entity.system;

import java.util.Date;

public class SysUser {
    private Long uid;

    private Integer roleid;

    private String username;

    private String password;

    private Integer loginnum;

    private Integer status;

    private Date lasttime;

    private Date createtime;

    private String createman;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getLoginnum() {
        return loginnum;
    }

    public void setLoginnum(Integer loginnum) {
        this.loginnum = loginnum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateman() {
        return createman;
    }

    public void setCreateman(String createman) {
        this.createman = createman == null ? null : createman.trim();
    }
}