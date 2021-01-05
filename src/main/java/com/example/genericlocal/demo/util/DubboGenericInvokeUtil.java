package com.example.genericlocal.demo.util;


import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;



public class DubboGenericInvokeUtil {
    private static Logger logger = LogManager.getLogger(DubboGenericInvokeUtil.class);
    private static ApplicationConfig application = new ApplicationConfig();
    static {
        application.setName("dubbo-generic-consumer");
    }

    /**
     * 获取注册中心信息
     *
     * @param address zk注册地址
     * @param group   dubbo服务所在的组
     * @return
     */
    private static RegistryConfig getRegistryConfig(String address, String group, String version) {
        RegistryConfig registryConfig = new RegistryConfig();
        if (StringUtils.isNotEmpty(address)) {
            registryConfig.setAddress(address);
        }
        if (StringUtils.isNotEmpty(version)) {
            registryConfig.setVersion(version);
        }
        if (StringUtils.isNotEmpty(group)) {
            registryConfig.setGroup(group);
        }
        return registryConfig;
    }


    private static ReferenceConfig getReferenceConfig(String interfaceName, String address,
                                                      String group, String version) {

        ReferenceConfig referenceConfig = new ReferenceConfig<>();
        try {
            referenceConfig.setApplication(application);
            referenceConfig.setRegistry(getRegistryConfig(address, group, version));
            referenceConfig.setInterface(interfaceName);
            if (StringUtils.isNotEmpty(version)) {
                referenceConfig.setVersion(version);
            }
            referenceConfig.setGeneric(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return referenceConfig;
    }

    public static Object invoke(String interfaceName, String methodName, List<String> paramTypeList, List<Object> paramList, String address, String version) {
        ReferenceConfig reference = getReferenceConfig(interfaceName, address, null, version);
        if (null != reference) {
            GenericService genericService = (GenericService) reference.get();
            if (genericService == null) {
                logger.debug("GenericService 不存在:{}", interfaceName);
                return null;
            }

            String[] paramTypeStrArr = paramTypeList.toArray(new String[paramTypeList.size()]);

            Object[] paramValueObjectArr = paramList.toArray(new Object[paramList.size()]);

            //dubbo接口泛化调用
            Object resultParam = genericService.$invoke(methodName, paramTypeStrArr, paramValueObjectArr);
            return resultParam;
        }
        return null;
    }

}