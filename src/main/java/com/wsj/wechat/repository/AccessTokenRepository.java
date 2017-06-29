package com.wsj.wechat.repository;

import com.wsj.wechat.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Jimmy on 2017/6/29.
 */
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {

    @Query(value = "select a.* from wx_access_token a where created_at >= DATE_ADD(now(),INTERVAL -1.8 hour)", nativeQuery = true)
    AccessToken getValidAccessToken();
}
