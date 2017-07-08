package com.wsj.wechat.services.impl;

import com.wsj.wechat.bean.sns.SnsToken;
import com.wsj.wechat.bean.user.UserInfo;
import com.wsj.wechat.entity.WxCustomer;
import com.wsj.wechat.repository.AccessTokenRepository;
import com.wsj.wechat.repository.WxCustomerRepository;
import com.wsj.wechat.services.WechatBaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * Created by jimmy on 2017/7/2.
 */
@Service
public class WechatBaseServiceImpl implements WechatBaseService {
    @Autowired
    private WxCustomerRepository wxCustomerRepository;

    @Override
    public WxCustomer insertOrUpdateUserInfo(UserInfo userInfo, SnsToken snsToken) {
        WxCustomer wxcustomer = wxCustomerRepository.findWxCustomerByOpenId(userInfo.getOpenid());
        if (wxcustomer == null){
            wxcustomer = new WxCustomer();
            wxcustomer.setCreatedAt(new Date());
            wxcustomer.setUpdatedAt(new Date());
        }
        BeanUtils.copyProperties(userInfo, wxcustomer);
        this.fixWxCustomerInfo(wxcustomer, userInfo,snsToken);
        return wxCustomerRepository.save(wxcustomer);
    }

    @Override
    public WxCustomer findWxCustomerByOpenid(String openid) {
        return wxCustomerRepository.findWxCustomerByOpenId(openid);
    }

    /**
     * 补全wx_customer信息，两个实体类部分字段不一致导致copy不了
     *
     * @param wxCustomer
     * @param userInfo
     * @return
     */
    private WxCustomer fixWxCustomerInfo(WxCustomer wxCustomer, UserInfo userInfo,SnsToken snsToken) {
        wxCustomer.setNickname(userInfo.getNickname());
        wxCustomer.setNicknameEmoji(userInfo.getNickname_emoji());
        wxCustomer.setSubscribeTime(userInfo.getSubscribe_time());
        wxCustomer.setHeadImgUrl(userInfo.getHeadimgurl());
        wxCustomer.setAccessToken(snsToken.getAccess_token());
        wxCustomer.setAccessTokenCreatedAt(new Date());
        wxCustomer.setRefreshToken(snsToken.getRefresh_token());
        wxCustomer.setRefreshTokenCreatedAt(new Date());
        return wxCustomer;
    }


}
