package com.wsj.wechat.web.action;

import java.io.IOException;
import java.util.List;

import com.iplas.sw.pojo.domain.hibernate.hbm.ExpertReg;
import com.wsj.wx.service.IRegExpertService;
import com.opensymphony.xwork2.Action;
import com.uap.common.util.massgeUtils;
import com.uap.common.utils.AppContextUtils;
import com.uap.common.utils.DateUtils;
import com.uap.core.service.IDaoService;
import com.uap.core.web.jmesa.action.BaseJmesaAction;

/**
 * Company:  广州快塑电子商务有限公司  
 * Title:    塑问
 * 类描述 :     行家注册-主页面
 * @version: 1.0
 * @since:   2017-01-16
 * @serial:  
 * @author 侯烽泰
 */
public class RegExpertAction  extends BaseJmesaAction{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ====================================================================
	 * 【塑问】-【行家首页】 
	 * ====================================================================
	 */
	public String index(){
		
		return successView;
	}
	
	/**
	 * ====================================================================
	 * 【塑问】-【行家】 注册跳转页面
	 * ====================================================================
	 */
	public String regPage(){
		
		return successView;
	}
	/**
	 * ====================================================================
	 * 【塑问】-【行家注册】 
	 * ====================================================================
	 * @throws IOException 
	 */
	public String reg() throws IOException{
		getResponse().setContentType("text/html;charset=utf-8");
		ExpertReg reg = (ExpertReg)getModel(ExpertReg.class);
		
		IDaoService daoService = (IDaoService) AppContextUtils.getBean(IDaoService.class.getName());
		List<ExpertReg> list = (List<ExpertReg>) daoService.findByPropertyName(ExpertReg.class,"phone", reg.getPhone());
		
		if(list.size()==0){//尚未注册过
			
			reg.setRegTime(DateUtils.currentDate());
			
			IRegExpertService regService = (IRegExpertService) AppContextUtils.getBean(IRegExpertService.class.getName());
			boolean flag = regService.regExpert(reg);
			
			massgeUtils.massge(flag);
		}else{//已经注册过
			getResponse().getWriter().print("00");
		}
		
		return Action.NONE;
	}
}
