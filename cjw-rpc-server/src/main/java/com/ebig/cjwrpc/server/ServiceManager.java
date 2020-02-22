package com.ebig.cjwrpc.server;

import com.ebig.cjwrpc.Request;
import com.ebig.cjwrpc.ServiceDescriptor;
import com.ebig.cjwrpc.common.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ServiceManager {

    private Map<ServiceDescriptor,ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass,T bean){
       Method[] methods = ReflectionUtils.getPublicMethod(interfaceClass);
       for (Method method : methods){
           ServiceInstance instance = new ServiceInstance(bean,method);
           ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass,method);
           services.put(sdp,instance);
       }
    }

    public ServiceInstance lookup(Request request){
        return services.get(request.getService());
    }

}
