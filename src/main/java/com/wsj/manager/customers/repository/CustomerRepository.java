package com.wsj.manager.customers.repository;


import com.wsj.manager.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Jimmy on 2017/6/22.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Modifying(clearAutomatically = true)
    @Query("update Customer c set status = :status,updatedBy= :operatorId where id = :customerId")
    void modifyCustomerStatus(@Param("customerId") int customerId, @Param("status") int status, @Param("operatorId") int operatorId);
    @Query("select c from Customer c where c.loginName = ?1")
    Customer findCustomerByLoginName(String loginName);
    @Query("select c from Customer c where c.loginName = ?1 and c.password = ?2")
    Customer findCustomerByLoginNameAndPassword(String loginName, String password);

    @Query("select c from Customer c where c.wxCustomerId=?1")
    Customer findCustomerByOpenId(int wxCustomerId);
    @Modifying(clearAutomatically = true)
    @Query("update Customer c set c.name = ?1,updatedAt=now() where id =?2")
    void changeCustomerNickName(String nickName,int id);

    @Query("select c from Customer c where c.name =?1")
    Customer findCustomerByNickName(String nickName);
}
