package com.joyner.hack.jvm.chapter8_stack_frame;

public class Test extends  StaticResolution {


    public static void main(String[] args) {
        System.out.println(new Test().testInt());
    }

    public void println(String x) {
        System.out.println("ok");
    }
    private static int testInt(){
        int a = 0;
        try{
            System.out.println("try");
            int m = 33/a;
            return a;
        }catch (Exception e){
            System.out.println("catch");
            a = 0;
            e.printStackTrace();
            return a;
        }finally {
            System.out.println("finally");
            a = 10;
        }
    }
}
