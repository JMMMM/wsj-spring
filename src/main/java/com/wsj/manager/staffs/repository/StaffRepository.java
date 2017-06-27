package com.wsj.manager.staffs.repository;

import com.wsj.manager.staffs.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Jimmy on 2017/6/23.
 */
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query("select s from Staff s where login_name = ?1 and password = ?2")
    public Staff findStaffByPassword(String loginName, String password);


}
