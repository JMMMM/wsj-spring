package com.wsj.wechat.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jimmy on 2017/6/28.
 */
@Entity
@Table(name = "wx_access_token")
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "access_token")
    private String access_token;
    @Column(name = "expires_in")
    private int expires_in;
    @Column(name = "created_at")
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "id=" + id +
                ", access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", createdAt=" + createdAt +
                '}';
    }
}
