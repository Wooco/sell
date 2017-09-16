package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * Created by wujianchuan
 * 2017/9/16 15:01
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
