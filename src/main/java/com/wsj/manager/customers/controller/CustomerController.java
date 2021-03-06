package com.wsj.manager.customers.controller;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.services.CustomerService;
import com.wsj.sys.annotation.SessionCheck;
import com.wsj.sys.bean.PageBean;
import com.wsj.sys.bean.ResultBean;
import com.wsj.sys.enums.SysConstants;
import com.wsj.tools.MD5Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jimmy on 2017/6/25.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 用户列表
     *
     * @param customer
     * @param pageBean
     * @return
     */
    @SessionCheck(checkedType = SessionCheck.Type.MANAGER)
    @RequestMapping("/findCustomers")
    public PageBean<Customer> findCustomers(Customer customer,PageBean pageBean) {
        return customerService.findByPage(customer, pageBean);
    }

    /**
     * 修改用户状态
     *
     * @param status 修改后的状态
     * @return
     */
    @SessionCheck(checkedType = SessionCheck.Type.MANAGER)
    @RequestMapping(value="/modifyCustomerStatus", method = RequestMethod.POST)
    public ResultBean modifyCustomerStatus(int customerId, int status) {
        return customerService.modifyCustomerStatus(customerId, status);
    }

    /**
     * customer 注册
     * @param customer
     * @return
     */
    @RequestMapping("/register")
    public ResultBean register(Customer customer){
        return customerService.register(customer);
    }

    /**
     * 登陆
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public ResultBean login(String loginName,String password){
        return customerService.login(loginName, MD5Helper.encode(password));
    }

    /**
     * 获取登陆用户信息
     * @param request
     * @return
     */
    @SessionCheck(checkedType = SessionCheck.Type.WEB)
    @RequestMapping(value="/getLoginCustomerInfo")
    public ResultBean getLoginCustomerInfo(HttpServletRequest request){
        Customer customer = (Customer)request.getSession().getAttribute(SysConstants.WebLoginSession.getName());
        return ResultBean.success("在线用户",customer);
    }

    /**
     * 修改用户昵称
     * @param request
     * @param nickName
     * @return
     */
    @SessionCheck(checkedType = SessionCheck.Type.WEB)
    @RequestMapping(value="/changeCustomerNickName")
    public ResultBean changeCustomerNickName(HttpServletRequest request,String nickName){
        Customer customer = (Customer)request.getSession().getAttribute(SysConstants.WebLoginSession.getName());
        return customerService.changeCustomerNickName(nickName,customer.getId());
    }

    /**
     * 根据昵称获取用户信息
     * @param nickName
     * @return
     */
    @RequestMapping(value="/findCustomerByNickName")
    public ResultBean findCustomerByNickName(String nickName){
        return customerService.findCustomerByNickName(nickName);
    }
}
