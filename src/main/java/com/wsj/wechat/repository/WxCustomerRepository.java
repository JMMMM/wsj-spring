package com.wsj.wechat.repository;

import com.wsj.wechat.entity.AccessToken;
import com.wsj.wechat.entity.WxCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Jimmy on 2017/6/29.
 */
public interface WxCustomerRepository extends JpaRepository<WxCustomer, Integer> {
    @Query("select w from WxCustomer w where openid=?1")
    WxCustomer findWxCustomerByOpenId(String openid);
}
