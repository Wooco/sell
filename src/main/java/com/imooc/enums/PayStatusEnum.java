package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by wujianchuan
 * 2017/9/14 21:21
 */
@AllArgsConstructor
@Getter
public enum PayStatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;
    private String message;
}
