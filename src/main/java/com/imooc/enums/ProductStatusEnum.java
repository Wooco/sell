package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态
 * Created by wujianchuan
 * 2017/9/11 21:41
 */
@AllArgsConstructor
@Getter
public enum ProductStatusEnum {

    UP(0, "在架"),
    DOW(1, "下架");
    private Integer code;
    private String message;
}
