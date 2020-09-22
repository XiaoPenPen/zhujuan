package com.game.zhujuan.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel
public class Result {
	@ApiModelProperty("状态码,1 成功，0 失败，-2 返回值为空")
	private Integer code;
	private String message;
	@ApiModelProperty("数据")
	private Object data;
	private Map attribute;

	public static Result init(){
		return init(null);
	}

	public static Result init(Object data){
		Result result = new Result();
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMessage("SUCCESS");
		result.setData(data);
		return result;
	}


	public static Result error(String msg) {
		Result result = new Result();
		result.setCode(ErrorCode.FAILURE.getCode());
		result.setMessage(msg);
		return result;
	}



	public Result(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result() {
	}


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Map getAttribute() {
		return attribute;
	}

	public void setAttribute(Map attribute) {
		this.attribute = attribute;
	}
}
