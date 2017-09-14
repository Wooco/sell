package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by wujianchuan
 * 2017/9/14 21:41
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
}
