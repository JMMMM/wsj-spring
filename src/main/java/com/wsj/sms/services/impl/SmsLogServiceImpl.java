package com.wsj.sms.services.impl;

import com.wsj.sms.entity.SmsLog;
import com.wsj.sms.repository.SmsLogRepository;
import com.wsj.sms.services.SmsLogService;
import com.wsj.sys.bean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;


/**
 * Created by jimmy on 2017/7/7.
 */
@Service
public class SmsLogServiceImpl implements SmsLogService {
    @Autowired
    private SmsLogRepository smsLogRepository;

    @Override
    public ResultBean<SmsLog> saveSmsLog(SmsLog smsLog) {
//        if (smsLog.getId() > 0) return ResultBean.failure("已存在短信");
        if (smsLog.getType() == null) return ResultBean.failure("短信类型不能为空");
        SmsLog log = smsLogRepository.save(smsLog);
        return ResultBean.success("保存成功", log);
    }

    @Override
    public SmsLog findSmsLogByCondition(String phone, String identifyingCode, int type) {
        return smsLogRepository.findSmsLogByCondition(phone,identifyingCode,type);
    }

    /**
     * 获取5分钟内同一ip请求次数
     * @param ipAddress
     * @return
     */
    @Override
    public int countSmsLogByIpAddress(String ipAddress) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,-5);
        return smsLogRepository.countLogByIpAddress(ipAddress,calendar.getTime());
    }


}
