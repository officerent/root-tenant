package com.root.tenant.model.common;

public class TenantException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4788922929022195678L;
	private Integer code;
    private String msg;

    public TenantException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Integer getErrCode() {
        return code;
    }

    public String getErrMsg() {
        return msg;
    }
}

