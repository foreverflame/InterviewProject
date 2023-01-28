package com.example.algorithm;

import java.util.HashSet;


public class A {


    public boolean compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return true;
        }

        boolean isSame = false;
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        String[] array1 = v1.split(".");
        String[] array2 = v2.split(".");

        for (int i = 0; i < array1.length; i++) {
            int i1 = Integer.parseInt(array1[i]);
            set1.add(i1);
        }

        for (int j = 0; j < array2.length; j++) {
            int j2 = Integer.parseInt(array2[j]);
            set2.add(j2);
        }

        if (set1.size() != set2.size()) {
            return false;
        } else {

            for (Integer integer1 : set1) {
                for (Integer integer2 : set2) {
                    if (integer1 != integer2) {
                        isSame = false;
                    } else {
                        isSame = true;
                    }

                }
            }
        }


        return isSame;
    }
}
