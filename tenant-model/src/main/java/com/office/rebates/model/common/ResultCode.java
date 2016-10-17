package com.office.rebates.model.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by gabriel on 14-12-12.
 */
public class ResultCode<T> {
    private String errMsg;
    private Integer errCode;
    private T data;


    public ResultCode() {
        this.errMsg = "success";
        this.errCode = 0;
        this.data = null;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
