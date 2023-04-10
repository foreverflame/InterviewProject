package com.example.test.algorithm;

/**
 * @description: 数组相关测试
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class ArrayTest {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 5, 8, 9, 12, 30, 99};
        int key = 30, low = 0, high = array.length - 1;
        int findKeyIndex = binarySearch(array, key, low, high);
        System.out.println("key index:" + findKeyIndex);
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
