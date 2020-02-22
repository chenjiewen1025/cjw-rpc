package com.ebig.cjwrpc.server;

import com.ebig.cjwrpc.Request;
import com.ebig.cjwrpc.common.utils.ReflectionUtils;

public class ServiceInvoker {


    public Object invoke(ServiceInstance instance, Request request){
        return ReflectionUtils.invoke(instance.getTarget(),
                instance.getMethod(),request.getParameters());
    }
}
