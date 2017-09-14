package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wujianchuan
 * 2017/9/14 22:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {

        OrderDetail detail = new OrderDetail();
        detail.setDetailId("123455");
        detail.setOrderId("123456");
        detail.setProductIcon("http://xxx.jpg");
        detail.setProductId("123123123");
        detail.setProductName("皮蛋粥");
        detail.setProductPrice(new BigDecimal(1.5));
        detail.setProductQuantity(1);
        OrderDetail result = repository.save(detail);
        Assert.assertNotEquals(null, result);
    }

    @Test
    public void findByOrderId() throws Exception {

        List<OrderDetail> orderDetailList = repository.findByOrderId("123456");
        Assert.assertNotEquals(0, orderDetailList.size());
    }
}
