package com.wsj.sms.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * sms_log 实体类
 */

@Entity
@Table(name="sms_log")
public class SmsLog {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "identifying_code")
    private String identifyingCode;
    @Column(name = "type")
    private Integer type;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "updated_by")
    private Integer updatedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifyingCode() {
        return identifyingCode;
    }

    public void setIdentifyingCode(String identifyingCode) {
        this.identifyingCode = identifyingCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}

