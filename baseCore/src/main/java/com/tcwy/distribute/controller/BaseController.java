package com.tcwy.distribute.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.aspectj.weaver.NewConstructorTypeMunger;

import com.tcwy.distribute.result.BaseResult;
@ApiModel(description = "通用响应返回对象")
public class BaseController {
	@ApiModelProperty(value="返回代码")
	public int code =0;
	@ApiModelProperty(value="返回信息")
	public String msg="操作成功";
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
	
 }
