package com.imooc.dataobject;

import com.imooc.enums.OrderSatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wujianchuan
 * 2017/9/14 21:15
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderSatusEnum.NEW.getCode();
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    private Date createTime;
    private Date updateTime;
}
