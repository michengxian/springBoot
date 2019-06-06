package com.imooc.financial.tools;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  CONSTRUCTOR:用于描述构造器
 *  FIELD:用于描述域
 *  LOCAL_VARIABLE:用于描述局部变量
 *  METHOD:用于描述方法
 *  PACKAGE:用于描述包
 *  PARAMETER:用于描述参数
 *  TYPE:用于描述类、接口(包括注解类型) 或enum声明
 */
@Target(ElementType.FIELD)

//当你的程序跑起来了，电脑才开始阅读这些注解
@Retention(RetentionPolicy.RUNTIME)

public @interface MethodNote {

    /**
     * 字段简称
     */
    public String note() default "这个人很懒，什么都没写";


    /**
     * 是否可以为空
     */
    boolean nullable()default true;

    /**
     * 长度
     */
    int length() default 255;


    /**
     * 精确度
     */
    int precision() default 0;

}
