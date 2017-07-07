package com.wsj.sms.services;

import com.wsj.sms.entity.SmsLog;
import com.wsj.sys.bean.ResultBean;

/**
 * Created by jimmy on 2017/7/7.
 */
public interface SmsLogService {
    ResultBean<SmsLog> saveSmsLog(SmsLog smsLog);
}
