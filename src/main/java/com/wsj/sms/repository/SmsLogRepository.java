package com.wsj.sms.repository;

import com.wsj.manager.staffs.entity.Staff;
import com.wsj.sms.entity.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jimmy on 2017/7/7.
 */
public interface SmsLogRepository extends JpaRepository<SmsLog, Integer> {
}
