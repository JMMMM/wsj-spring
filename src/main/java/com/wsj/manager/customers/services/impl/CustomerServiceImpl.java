package com.wsj.manager.customers.services.impl;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.repository.CustomerRepository;
import com.wsj.manager.customers.services.CustomerService;
import com.wsj.sys.bean.PageBean;
import com.wsj.sys.bean.ResultBean;
import com.wsj.sys.enums.ErrorCode;
import com.wsj.sys.enums.SysConstants;
import com.wsj.tools.*;
import com.wsj.tools.validator.CustomerValidator;
import com.wsj.wechat.entity.WxCustomer;
import com.wsj.wechat.repository.WxCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jimmy on 2017/6/25.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EntityManager em;
    @Autowired
    private WxCustomerRepository wxCustomerRepository;

    @Override
    public PageBean<Customer> findByPage(Customer customer, PageBean pageBean) {
        String sql = " select c.* from customers c where 1 =1 ";
        List<Object> parameter = new ArrayList<Object>();
        if (customer != null) {
            if (!StringUtils.isEmpty(customer.getPhone())) {
                sql += " and phone like ? ";
                parameter.add("%" + customer.getPhone() + "%");
            }

            if (!StringUtils.isEmpty(customer.getName())) {
                sql += " and name like ? ";
                parameter.add("%" + customer.getName() + "%");
            }
        }
        return QueryTools.queryPageResult(em, sql, parameter, pageBean, Customer.class);
    }

    @Override
    public ResultBean<Customer> register(Customer customer) {
        //如果没有填写用户名，系统默认提供用户名
        if (StringUtils.isEmpty(customer.getName())) {
            customer.setName("wsj_" + customer.getPhone());
        }
        ErrorCode validatorErrorCode = ValidatorHelper.validator(customer, new CustomerValidator());
        if (validatorErrorCode.getCode() > 0)
            return ResultBean.failure(validatorErrorCode.getMessage(), validatorErrorCode);
        if (customerRepository.findCustomerByLoginName(customer.getLoginName()) != null)
            return ResultBean.failure(ErrorCode.LOGIN_NAME_EXISTS.getMessage(), ErrorCode.LOGIN_NAME_EXISTS);
        //密码md5处理
        customer.setStatus(1);
        customer.setPassword(MD5Helper.encode(customer.getPassword()));
        customer.setCreatedAt(new Date());
        customer.setUpdatedAt(new Date());
        Customer result = customerRepository.save(customer);
        return ResultBean.success("注册成功", result);
    }

    @Override
    public Customer findByLoginName(String phone) {
        return customerRepository.findCustomerByLoginName(phone);
    }

    @Override
    public ResultBean modifyCustomerStatus(int customerId, int status) {
        customerRepository.modifyCustomerStatus(customerId, status, OperatorUtil.getOperatorName(session).getId());
        return ResultBean.success("更新成功!");
    }

    @Override
    public ResultBean<Customer> login(String loginName, String md5Password) {
        Customer customer = customerRepository.findCustomerByLoginNameAndPassword(loginName, md5Password);
        if (customer == null) {
            return ResultBean.failure("账号或密码错误");
        } else if (customer.getStatus() == 0) {
            return ResultBean.failure("账号被禁用");
        }
        session.setAttribute(SysConstants.WebLoginSession.getName(), customer);
        String openId = CookieUtil.getCookieValue(request.getCookies(), SysConstants.WsjWxOpenId.getName());

        if (openId != null) {
            WxCustomer wxCustomer = wxCustomerRepository.findWxCustomerByOpenId(openId);
            if (wxCustomer != null) {
                customerRepository.updateCustomerWxCustomerId(wxCustomer.getId(), customer.getId());
            }
        }
        return ResultBean.success("登陆成功", customer);
    }

    @Override
    public Customer findCustomerByWxCustomerId(int wxCustomerId) {
        return customerRepository.findCustomerByOpenId(wxCustomerId);
    }

    @Override
    public ResultBean changeCustomerNickName(String nickName, int id) {
        Customer customer = customerRepository.findCustomerByNickName(nickName);
        if (customer != null) return ResultBean.failure("已存在昵称");
        customerRepository.changeCustomerNickName(nickName, id);
        return ResultBean.success("更新成功!");
    }

    @Override
    public ResultBean findCustomerByNickName(String nickName) {
        Customer customer = customerRepository.findCustomerByNickName(nickName);
        return null == customer ? ResultBean.success("存在用户", customer) : ResultBean.failure("不存在用户", null);
    }
}
