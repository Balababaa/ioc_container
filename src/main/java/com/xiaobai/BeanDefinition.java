package com.xiaobai;

import com.xiaobai.annotations.IoC;
import lombok.Data;

@Data
@IoC
public class BeanDefinition {
    private String superName;
    private String alias;
    private String className;
}
