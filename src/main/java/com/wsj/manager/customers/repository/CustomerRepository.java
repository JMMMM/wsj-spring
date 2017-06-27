package com.wsj.manager.customers.repository;


import com.wsj.manager.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jimmy on 2017/6/22.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
