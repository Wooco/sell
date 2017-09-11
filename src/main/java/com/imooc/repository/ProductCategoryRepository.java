package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wujianchuan
 * 2017/9/10 22:08
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
