package com.xiaobai;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BeanFactory {

    private ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap;

    private static Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());

    public Object getBeanByName(String beanName) {
        Object object = beanMap.get(beanName);
        if (object != null) {
            return object;
        }

        object = getInstance(beanName);

        if (object != null) {
            beanMap.put(beanName, object);
            return object;
        }
        return null;
    }

    public Object getInstance(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition != null) {
            try {
                Class clazz = Class.forName(beanDefinition.getClassName());
                Object o = clazz.newInstance();
                setField(o);
                return o;
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    private void setField(Object o) {
        Field[] fields = o.getClass().getFields();

        for (Field field : fields) {
            String className = field.getType().getName();
            Object value = getBeanByName(className);
            if (value != null) {
                field.setAccessible(true);
                try {

                    field.set(o, value);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage());
                }
            }

        }

    }

    public static ConcurrentHashMap<String, BeanDefinition> getBeanDefinationMap() {
        return beanDefinitionMap;
    }

    public static void setBeanDefinationMap(ConcurrentHashMap<String, BeanDefinition> beanDefinationMap) {
        BeanFactory.beanDefinitionMap = beanDefinationMap;
    }
}
