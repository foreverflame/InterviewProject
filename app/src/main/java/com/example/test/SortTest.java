package com.example.test;

/**
 * @description: 快速排序、冒泡排序、选择排序
 * @author: huangyonghuang
 * @date: 2023/1/30
 */
public class SortTest {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 9, 3, 5, 3, 8, 7};
        int low = 0;
        int high = array.length - 1;
        quickSort(array, low, high);
        bubbleSort(array);
        selectSort(array);
    }


    /**
     * 最好时间复杂度O(n),平均时间复杂度O(nlogn)
     * https://www.cnblogs.com/captainad/archive/2019/06/10/10999697.html
     */
    private static void quickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int i, j, temp, t;
        i = low;
        j = high;
        temp = array[low];
        while (i < j) {
            while (temp <= array[j] && i < j) {
                j--;
            }
            while (temp >= array[i] && i < j) {
                i++;
            }
            if (i < j) {
                t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        array[low] = array[j];
        array[j] = temp;
        //分别递归左右列表
        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);
    }


    /**
     * 最好时间复杂度O(n),平均时间复杂度O(n^2)
     * https://blog.csdn.net/NathanniuBee/article/details/83413879
     */
    private static void bubbleSort(int[] array) {
        int temp;
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - j - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
    }


    /**
     *
     * https://zhuanlan.zhihu.com/p/123048793
     */
    private static void selectSort(int[] array) {
        int temp;
        int minIndex;
        for (int j = 0; j < array.length - 1; j++) {
            minIndex = j;
            for (int i = minIndex; i < array.length - 1; i++) {
                if (array[i + 1] < array[minIndex]) {
                    minIndex = i + 1;
                }
            }
            temp = array[minIndex];
            array[minIndex] = array[j];
            array[j] = temp;
        }
    }
}
