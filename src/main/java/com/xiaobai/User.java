package com.xiaobai;

import com.xiaobai.annotations.IoC;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@IoC
public class User {
    public String pro1;
    public String pro2;
    public Stu stu;
}
