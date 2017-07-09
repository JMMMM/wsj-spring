package com.wsj.sms.controller;

import com.wsj.manager.customers.entity.Customer;
import com.wsj.manager.customers.services.CustomerService;
import com.wsj.sms.entity.SmsLog;
import com.wsj.sms.services.SmsLogService;
import com.wsj.sms.tools.SmsConfigure;
import com.wsj.sys.bean.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;


/**
 * SmsLoginController
 */

@RestController
@RequestMapping(value = "/sms/login")
public class SmsLoginController {
    private static Logger logger = LoggerFactory.getLogger(SmsLoginController.class);

    @Autowired
    private SmsLogService smsLogService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/sendIdentifyingCode", method = RequestMethod.GET)
    public ResultBean sendIdentifyingCode(HttpServletRequest request, String mobile) {
        String ipAddress = getRemoteHost(request);
        if (smsLogService.countSmsLogByIpAddress(ipAddress) > 2) {
            return ResultBean.failure("Error-003,操作过于频繁，请5分钟后重试");
        }
        /**
         * 保存短信验证码
         */
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        SmsLog smsLog = new SmsLog();
        smsLog.setCreatedAt(new Date());
        smsLog.setIdentifyingCode(code + "");
        smsLog.setPhone(mobile + "");
        smsLog.setType(1);
        smsLog.setIpAddress(ipAddress);
        ResultBean resultBean = smsLogService.saveSmsLog(smsLog);
        if (!resultBean.isSuccess()) return ResultBean.failure("发送短信失败，接口异常");
        /**
         * 发送短信验证码
         */
        String content = null;
        try {
            content = URLEncoder.encode(new StringBuilder().append("此次登录验证码：").append(code).append("【味食家】").toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuilder param = new StringBuilder().append("username=").append(SmsConfigure.getUsername())
                .append("&password=").append(SmsConfigure.getPassword())
                .append("&mobile=").append(mobile)
                .append("&content=").append(content)
                .append("&productid=").append(SmsConfigure.getProductId())
                .append("&xh=");
        String result = sendSms(SmsConfigure.getUrl(), param.toString());
        result = result.contains(",") ? result.substring(0, 1) : result;
        switch (result) {
            case "0":
                return ResultBean.failure("发送短信失败，请输入正确的手机号码");
            case "1":
                return ResultBean.success("发送短信成功");
            case "6":
                return ResultBean.failure("发送短信失败，请输入正确的手机号码");
            default:
                return ResultBean.failure("发送短信失败，接口异常");
        }
    }

    /**
     * 短信校验登录
     *
     * @param mobile 电话号码
     * @param code   验证码
     * @return
     */
    @RequestMapping(value = "/validateCode", method = RequestMethod.GET)
    public ResultBean validateCode(long mobile, int code) {
        SmsLog smsLog = smsLogService.findSmsLogByCondition(mobile + "", code + "", 1);
        if (null == smsLog) {
            return ResultBean.failure("Error-001,验证码错误，请重新验证");
        }
        Date now = new Date();
        long timeDiff = now.getTime() - smsLog.getCreatedAt().getTime();
        if (timeDiff > 60 * 1000) {
            return ResultBean.failure("Error-002,验证码超时，请重新验证");
        }

        /**
         * 通过电话号码查找用户是否已经注册，已经注册的返回用户昵称，未注册的就调用注册接口，返回用户昵称
         */
        String phone = mobile + "";
        Customer customer = customerService.findByLoginName(phone);
        if (null != customer) {
            // 登陆
            customerService.login(customer.getLoginName(), customer.getPassword());
            return ResultBean.success("登录成功");
        }
        /**
         * 注册
         */
        customer = new Customer();
        customer.setLoginName(phone);
        customer.setCreatedAt(now);
        customer.setPhone(phone);
        customer.setStatus(1);
        customer.setPassword(code + "");

        // 返回保存成功的客户信息，只提供默认的昵称
        ResultBean<Customer> bean = customerService.register(customer);
        // 登陆
        customerService.login(bean.getBean().getLoginName(), bean.getBean().getPassword());
        return ResultBean.success("注册成功");
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private static String sendSms(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}

