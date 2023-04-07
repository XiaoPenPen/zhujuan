package com.game.zhujuan.controller.handler.exception;

import com.game.zhujuan.common.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuchunpeng 2021/12/8
 */
@Data
@NoArgsConstructor
public class ParamCheckException extends RuntimeException {
    private Integer code;

    public ParamCheckException(String msg){
        super(msg);
        this.code = ErrorCode.CHECK_ERROR.getCode();
    }
}
