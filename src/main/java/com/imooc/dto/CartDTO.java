package com.imooc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by wujianchuan
 * 2017/9/16 15:28
 */
@Data
@AllArgsConstructor
public class CartDTO {

    private String productId;
    private Integer productQuantity;
}
