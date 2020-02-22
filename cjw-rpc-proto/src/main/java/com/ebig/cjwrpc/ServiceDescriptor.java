package com.ebig.cjwrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 表示服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    private String clazz;

    private String method;

    private String returnType;

    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());
        Class[] params = method.getParameterTypes();
        String[] parmtypes = new String[params.length];
        for (int i = 0; i < params.length ; i++) {
            parmtypes[i] = params[i].getName();
        }
        sdp.setParameterTypes(parmtypes);
        return sdp;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) obj;
        return that.toString().equals(toString());
    }

    @Override
    public String toString() {
        return clazz + method + returnType + Arrays.toString(parameterTypes);
    }
}
