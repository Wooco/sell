package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by wujianchuan
 * 2017/9/14 21:47
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
