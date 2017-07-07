package com.wsj.sms.services.impl;

import com.wsj.sms.entity.SmsLog;
import com.wsj.sms.repository.SmsLogRepository;
import com.wsj.sms.services.SmsLogService;
import com.wsj.sys.bean.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.transform.Result;

/**
 * Created by jimmy on 2017/7/7.
 */
public class SmsLogServiceImpl implements SmsLogService {
    @Autowired
    private SmsLogRepository smsLogRepository;

    @Override
    public ResultBean<SmsLog> saveSmsLog(SmsLog smsLog) {
        if (smsLog.getId() > 0) return ResultBean.failure("已存在短信");
        if (smsLog.getType() > 0) return ResultBean.failure("短信类型不能为空");
        SmsLog log = smsLogRepository.save(smsLog);
        return ResultBean.success("保存成功", log);
    }
}
