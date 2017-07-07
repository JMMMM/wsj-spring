package com.wsj.tools;


import com.wsj.manager.staffs.entity.Staff;
import com.wsj.sys.enums.SysConstants;

import javax.servlet.http.HttpSession;

/**
 * Created by jimmy on 2017/6/25.
 */
public class OperatorUtil {

    public static Staff getOperatorName(HttpSession httpSession){
        return (Staff)httpSession.getAttribute(SysConstants.ManagerLoginSession.getName());
    }
}
