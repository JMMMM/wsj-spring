package com.wsj.wechat.service.impl;

import com.iplas.sw.pojo.domain.hibernate.hbm.ExpertReg;
import com.wsj.wx.service.IRegExpertService;
import com.uap.common.utils.AppContextUtils;
import com.uap.common.utils.LogUtils;
import com.uap.common.utils.ObjectUtils;
import com.uap.core.service.IDaoService;

public class RegExpertServiceImpl implements IRegExpertService {

	@SuppressWarnings("finally")
	@Override
	public boolean regExpert(ExpertReg reg) {
		boolean flag=false;
		IDaoService daoService = (IDaoService)AppContextUtils.getBean(IDaoService.class.getName());
		
		try {
			String id = (String) daoService.saveEntity(reg);
			
			if(ObjectUtils.isNotNull(id)){
				flag = true;
			}
		} catch (Exception e) {
			LogUtils.error(e.getMessage());
		}finally{
			return flag;
		}
	}

}
