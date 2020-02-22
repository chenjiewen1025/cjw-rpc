package com.ebig.cjwrpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * ���乤����
 */
public class ReflectionUtils {

    /**
     * ����class ����ʵ��
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * ��ȡ���з���
     *
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethod(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> methodList = new ArrayList<>();
        for (Method i : methods) {
            if (Modifier.isPublic(i.getModifiers())) {
                methodList.add(i);
            }
        }
        return methodList.toArray(new Method[0]);
    }

    /**
     * ����ָ�������ĳ������
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
