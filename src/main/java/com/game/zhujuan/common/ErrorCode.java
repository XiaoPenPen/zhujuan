package com.game.zhujuan.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ErrorCode {
	@Error(msg = "数据不存在")
	DATA_NONE(-2),
	@Error(msg = "成功")
	SUCCESS(1),
	@Error(msg = "失败")
	FAILURE(0);

	// 成员变量
	private int code;

	// 构造方法
	private ErrorCode(int code) {
		this.code = code;
	}
	// 返回错误码
	public int getCode() {
		return code;
	}

	// 返回错误名称vv
	public String getName() {
		return this.name();
	}

	// 返回错误信息
	public String getMessage() {
		Error error = null;
		try {
			error = this.getClass().getField(this.getName())
					.getAnnotation(Error.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return error.msg();
	}
	
	public static ErrorCode getECbyCode(int code){
		for(ErrorCode ec: ErrorCode.values()){
			if(code==ec.getCode())
				return ec;
		}
		return null;
		
	}

}
