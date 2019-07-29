package com.tcwy.distribute.result;

import com.base.core.tools.BaseTools;
import com.base.core.tools.CsrfToken;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "通用响应返回对象")
public class BaseResult<T> {
	public BaseResult(){
		BaseTools.getResponse().setHeader(CsrfToken.getKey(), CsrfToken.setCsrfTokenInSession());
	}
	
	@ApiModelProperty(value="返回代码")
	public int code =0;
	@ApiModelProperty(value="返回信息")
	public String msg="操作成功";
	@ApiModelProperty(value="返回数据")
	public T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
