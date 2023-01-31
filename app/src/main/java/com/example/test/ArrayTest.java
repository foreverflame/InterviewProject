package com.example.test;

/**
 * @description: 数组相关测试
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class ArrayTest {

    /**
     * 有序数组二分查找,返回要找的值的索引
     */
    public int binarySearch(int[] array, int key, int low, int high) {
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
