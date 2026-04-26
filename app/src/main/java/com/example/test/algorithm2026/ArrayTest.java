package com.example.test.algorithm2026;

import java.util.HashMap;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2026/4/22
 */
public class ArrayTest {


    public int[] getTargetIndex(int[] array, int target) {
        int[] index = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                index[0] = i;
                index[1] = map.get(array[i]);
            }
            map.put(target - array[i], i);
        }
        return index;
    }
}
