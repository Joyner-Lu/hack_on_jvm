package com.joyner.hack.jvm.chapter7_load_class;

/**
 * 述代码运行之后，只会输出“SuperClass init！”，而不会输出“SubClass init！”。
 * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，
 * 只会触发父类的初始化而不会触发子类的初始化。 至于是否要触发子类的加载和验证，在虚拟机规范中并未明确规定，
 * 这点取决于虚拟机的具体实现。 对于Sun HotSpot虚拟机来说，可通过-XX:+TraceClassLoading参数观察到此操作会导致子类的加载。

 */
public class NotInitialization1 {

    public static void main(String[] args) {
        int x = SubClass.value;
    }

}