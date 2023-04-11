package com.example.test.algorithm;

/**
 * @description: 快速排序、冒泡排序、选择排序
 * @author: huangyonghuang
 * @date: 2023/1/30
 */
public class SortTest {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 9, 3, 5, 4, 8, 7};
        int low = 0;
        int high = array.length - 1;
//        quickSort(array, low, high);
//        bubbleSort(array);
//        selectSort(array);
        for (int j : array) {
            System.out.println(j);
        }
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
        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);
    }


    /**
     * 最好时间复杂度O(n),平均时间复杂度O(n^2)
     * https://blog.csdn.net/NathanniuBee/article/details/83413879
     */
    private static void bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    /**
     * https://zhuanlan.zhihu.com/p/123048793
     */
    private static void selectSort(int[] array) {
        int temp, minIndex;
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            for (int j = minIndex; j < array.length - 1; j++) {
                if (array[j + 1] < array[minIndex]) {
                    minIndex = j + 1;
                }
            }
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
