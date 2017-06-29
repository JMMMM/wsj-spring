package com.wsj.wechat.api.constant;

/**
 * return_code为通信标识，非交易标识，交易是否成功需要查看result_code来判断
 * return_code为FAIL时，产生return_msg字段
 * result_code为业务结果，为FAIL时产生err_code和err_code_des字段
 */
public enum WeiXinResultStatus {

    /**
     * return_code和result_code皆为SUCCESS时业务结果处理成功
     */
    Success(200, "业务结果SUCCESS","SUCCESS"),
    /**
     * return_code为SUCCESS，result_code为FAIL时业务结果处理失败
     */
    Failure(300, "业务结果FAIL","FAIL"),
    /**
     * return_code为FAIL时通信标识失败
     */
    Error(500, "通信标识FAIL","ERROR");

    public int code;
    public String name;
    public String ename;

    WeiXinResultStatus(int code, String name, String ename) {
        this.code = code;
        this.name = name;
        this.ename = ename;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
    
}
