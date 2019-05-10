package com.joyner.hack.jvm.chapter10_javac;

import java.util.ArrayList;
import java.util.List;

public class GenericTypes01 {

  /*  public static String method(List<String> list) {
        System.out.println("invoke method(List<String> list)");
        return "";
    }*/

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
        //method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
