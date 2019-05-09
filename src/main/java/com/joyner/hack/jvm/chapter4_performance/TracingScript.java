/*
package com.joyner.hack.jvm.chapter4_performance;

*/
/* BTrace Script Template *//*

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
    @OnMethod(
            clazz="com.joyner.hack.jvm.chapter4_performance.BTraceTest",
            method="add",
            location=@Location(Kind.RETURN)
    )

    public static void func(@Self com.joyner.hack.jvm.chapter4_performance.BTraceTest instance,int a,int b,@Return int result) {
        println("调用堆栈:");
        jstack();
        println(strcat("方法参数A:",str(a)));
        println(strcat("方法参数B:",str(b)));
        println(strcat("方法结果:",str(result)));
    }
}*/
