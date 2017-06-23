package com.wsj.entity;

import javax.persistence.*;

/**
 * Created by Jimmy on 2017/6/23.
 */
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
    @Column(name = "last_updated_at")
    private Long lastUpdatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
