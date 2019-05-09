package com.joyner.hack.jvm.chapter2_hotspot_object;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}

