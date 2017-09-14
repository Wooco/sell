package com.imooc.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by wujianchuan
 * 2017/9/14 18:53
 */
@Data
public class ResultVO<T> {

    private Integer code;
    private String msg;
    private T data;
}
