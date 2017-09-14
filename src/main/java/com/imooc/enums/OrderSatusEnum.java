package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by wujianchuan
 * 2017/9/14 21:18
 */
@AllArgsConstructor
@Getter
public enum OrderSatusEnum {

    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "取消");

    private Integer code;
    private String message;
}
