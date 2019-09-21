package com.integration.springbootentity.utils;

import com.integration.springbootentity.PojoBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Mapper {
    /**
     * 复制同名字段
     * @param src
     * @param target
     * @return
     * @throws Exception
     */
    public static PojoBase copy(PojoBase src, PojoBase target) throws Exception {
        Class<?> classType = target.getClass();
        Class<?> classTypeSrc = src.getClass();
        Field[] fields = classType.getDeclaredFields();
        for (Field field : fields) {
            try {
                //获取所有属性的访问权限
                field.setAccessible(true);
                //获取字段名称，根据字段名称生成对应的方法名，用于反射
                String fieldName = field.getName();
                String methodName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                String getStr = "get" + methodName;
                String setStr = "set" + methodName;
                //调用原对象的get方法为拷贝对象的set方法赋值
                Method getMethod = classTypeSrc.getMethod(getStr);
                Method setMethod = classType.getMethod(setStr, field.getType());
                Object getMethodResult = getMethod.invoke(src);
                //调用set方法把原对象的值复制到拷贝对象
                setMethod.invoke(target, getMethodResult);
            }catch (NoSuchMethodException e)
            {
                System.out.print(String.format("%s,%s\n",e.getMessage(),"字段未copy"));
            }
        }
        return target;
    }
}
