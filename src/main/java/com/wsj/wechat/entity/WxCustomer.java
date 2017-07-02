package com.wsj.wechat.entity;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wx_customers")
public class WxCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "subscribe")
    private Integer subscribe;
    @Column(name = "openid")
    private String openid;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "nickname_emoji")
    private String nicknameEmoji;
    @Column(name = "sex")
    private short sex;
    @Column(name = "language")
    private String language;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "head_img_url")
    private String headImgUrl;
    @Column(name = "subscribe_time")
    private Long subscribeTime;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "privilege")
    private String privilege;
    @Column(name = "remark")
    private String remark;
    @Column(name = "targid_list")
    private String targidList;
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "access_token_created_at")
    private Date accessTokenCreatedAt;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "refresh_token_created_at")
    private Date refreshTokenCreatedAt;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNicknameEmoji() {
        return nicknameEmoji;
    }

    public void setNicknameEmoji(String nicknameEmoji) {
        this.nicknameEmoji = nicknameEmoji;
    }

    public short getSex() {
        return sex;
    }

    public void setSex(short sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTargidList() {
        return targidList;
    }

    public void setTargidList(String targidList) {
        this.targidList = targidList;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessTokenCreatedAt() {
        return accessTokenCreatedAt;
    }

    public void setAccessTokenCreatedAt(Date accessTokenCreatedAt) {
        this.accessTokenCreatedAt = accessTokenCreatedAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getRefreshTokenCreatedAt() {
        return refreshTokenCreatedAt;
    }

    public void setRefreshTokenCreatedAt(Date refreshTokenCreatedAt) {
        this.refreshTokenCreatedAt = refreshTokenCreatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

