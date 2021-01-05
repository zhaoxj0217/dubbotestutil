package com.example.genericlocal.demo.http;

import com.example.genericlocal.demo.util.DubboGenericInvokeUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟请求入口
 */
@RestController
@RequestMapping(value = "/generic")
public class GenericController {


    @RequestMapping(value = "/invoke")
    @ResponseBody
    public Object invoke(@RequestBody RequestParams request) {
        return DubboGenericInvokeUtil.invoke(request.getInterfaceName(), request.getMethod(),
                request.getParamType(),request.getParamValue(), request.getAddress(), request.getVersion());
    }




}




