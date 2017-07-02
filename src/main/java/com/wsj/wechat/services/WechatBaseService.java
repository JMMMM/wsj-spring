package com.wsj.wechat.services;

import com.wsj.wechat.bean.sns.SnsToken;
import com.wsj.wechat.bean.user.UserInfo;
import com.wsj.wechat.entity.WxCustomer;

/**
 * Created by jimmy on 2017/6/30.
 */
public interface WechatBaseService {
    public WxCustomer insertOrUpdateUserInfo(UserInfo userInfo,SnsToken snsToken);
    public WxCustomer findWxCustomerByOpenid(String openid);

}
