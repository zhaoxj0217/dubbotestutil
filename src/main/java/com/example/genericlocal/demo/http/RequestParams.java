package com.example.genericlocal.demo.http;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 调用入参
 */
@Data
public class RequestParams implements Serializable{
    /**
     * 调用的接口全名
     */
    private String interfaceName;
    /**
     * 调用的接口方法名
     */
    private  String method;
    /**
     * 接口入参类型
     */
    private List<String> paramType;
    /**
     * 接口入参值
     */
    private List<Object> paramValue;
    /**
     * dubbo 注册地址
     */
    private String address;

    /**
     * 接口版本号
     */
    private String version;

}
