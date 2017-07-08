package com.wsj.sms.repository;

import com.wsj.manager.staffs.entity.Staff;
import com.wsj.sms.entity.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by jimmy on 2017/7/7.
 */
public interface SmsLogRepository extends JpaRepository<SmsLog, Integer> {
    @Query("select s from SmsLog s where s.phone=?1 and s.identifyingCode=?2 and s.type=?3")
    SmsLog findSmsLogByCondition(String phone, String identifyingCode, int type);

    @Query("select count(s) from SmsLog s where s.ipAddress=?1 and s.createdAt>?2")
    int countLogByIpAddress(String ipAddress,Date createdAt);
}
