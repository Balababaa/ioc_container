package com.xiaobai;

import com.xiaobai.annotations.IoC;
import org.reflections.Configuration;
import org.reflections.Reflections;

import java.util.Set;

public class Appication {
    public static void main(String[] args) {
        new IoCInitConifg();
        User user = (User) new BeanFactory().getBeanByName("com.xiaobai.User");
        System.out.println(user);
        System.out.println(user.getStu());
    }
}
