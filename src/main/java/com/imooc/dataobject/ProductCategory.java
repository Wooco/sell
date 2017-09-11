package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 类目
 * Created by wujianchuan
 * 2017/9/10 21:58
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
}
