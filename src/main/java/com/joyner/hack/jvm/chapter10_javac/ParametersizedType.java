package com.joyner.hack.jvm.chapter10_javac;

import java.util.HashMap;
import java.util.Map;

public class ParametersizedType {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hello", "你好");
        map.put("how are you?", "吃了没？");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
    }


}
