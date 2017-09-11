package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wujianchuan
 * 2017/9/10 22:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOne() {

        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void update() {

        ProductCategory productCategory = repository.findOne(2);
        productCategory.setCategoryType(12);
        repository.save(productCategory);
    }
}