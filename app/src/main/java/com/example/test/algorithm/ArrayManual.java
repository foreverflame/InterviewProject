package com.example.test.algorithm;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @description: 数组相关测试
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class ArrayManual {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 5, 8, 9, 12, 30, 99};
        int key = 30, low = 0, high = array.length - 1;
        int findKeyIndex = binarySearch(array, key, low, high);
        System.out.println("key index:" + findKeyIndex);
    }


    /**
     * 获取数组中第几个数
     */
    public static int getSecondNumber(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            set.add(nums[i]);
            if (set.size() > 2) {
                set.remove(set.first());
            }
        }
        return set.size() < 2 ? set.last() : set.first();
    }


    /**
     * 获取数组中和为某个数的索引
     */
    public static int[] getIndex(int[] array, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] index = new int[2];
        for (int i = 0; i < array.length - 1; i++) {
            if (hashMap.containsKey(array[i])) {
                index[0] = i;
                index[1] = hashMap.get(array[i]);
            }
            hashMap.put(target - array[i], i);
        }
        return index;
    }


    /**
     * 有序数组二分查找,返回要找的值的索引
     */
    public static int binarySearch(int[] array, int key, int low, int high) {
        if (key < array[low] || key > array[high] || low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (key < array[mid]) {
            return binarySearch(array, key, low, mid - 1);
        } else if (key > array[mid]) {
            return binarySearch(array, key, mid + 1, high);
        } else {
            return mid;
        }
    }
}
