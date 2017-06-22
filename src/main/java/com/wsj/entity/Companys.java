package com.wsj.entity;


import javax.persistence.*;

/**
 * Created by Jimmy on 2017/6/22.
 */
@Entity
@Table(name = "companys")
public class Companys {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
