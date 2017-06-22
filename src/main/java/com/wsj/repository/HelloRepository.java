package com.wsj.repository;

import com.wsj.entity.Companys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Jimmy on 2017/6/22.
 */
public interface HelloRepository extends JpaRepository<Companys, Integer> {


    @Query(value = "select name from Companys where id =?1")
    String findCompanyName(int id);


}
