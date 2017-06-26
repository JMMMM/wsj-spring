package com.wsj.repository;

import com.wsj.entity.Companys;
import com.wsj.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Jimmy on 2017/6/22.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
