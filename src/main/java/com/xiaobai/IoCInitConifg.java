package com.xiaobai;

import com.xiaobai.annotations.IoC;
import org.reflections.Reflections;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@IoC
public class IoCInitConifg {
    public IoCInitConifg() {
        ConcurrentHashMap<String, BeanDefinition> beanDefinationMap = new ConcurrentHashMap<String, BeanDefinition>();
        Reflections reflections = new Reflections("com.xiaobai");
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(IoC.class);

        for(Class type : types){
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClassName(type.getName());
            beanDefinition.setSuperName(type.getSuperclass().getName());
            beanDefinition.setAlias(type.getSimpleName());
            beanDefinationMap.put(type.getName(), beanDefinition);

            BeanFactory.setBeanDefinationMap(beanDefinationMap);
        }
    }
}
