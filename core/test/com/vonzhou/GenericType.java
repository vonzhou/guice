package com.vonzhou;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @version 2017/6/8.
 */
public class GenericType {
    static class Base<T> {
    }

    static class Sub<T> extends Base<T> {
    }

    public static void main(String[] args) {
        Sub<String> sub = new Sub<String>();
        Type type = sub.getClass().getGenericSuperclass();
        System.out.println(type);

        // 获取类型参数
        Type paraType = ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println(paraType);

        Base base = new Base<Map<String, String>>() {
        };
        System.out.println(((ParameterizedType) base.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

        System.out.println(new Object().getClass().getGenericSuperclass()); // null
    }
}
