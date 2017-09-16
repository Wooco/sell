package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by wujianchuan
 * 2017/9/16 15:02
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品数量不正确");

    private Integer code;
    private String message;
}
