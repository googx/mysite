package com.thesunboy.test.jvm;

import java.util.HashMap;
import java.util.Map;

public class JvmDenig {

    private static Map<String, Object> map = new HashMap<>();

    public static void main(String[] args) {
        int _1m = 1024 * 1024;

        byte[] b = new byte[_1m * 1];
        byte[] b1 = new byte[_1m * 4];
//        map.put("b", b);
//        map.put("b1", b1);
        byte[] b2 = new byte[_1m * 10];
//        map.put("b1", b2);
        byte[] b3 = new byte[_1m * 10];
    }


}
