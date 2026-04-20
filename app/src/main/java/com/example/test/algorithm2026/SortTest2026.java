package com.example.test.algorithm2026;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2026/4/20
 */
public class SortTest2026 {

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


    private static void quickSort(int[] array, int low, int high) {
        if (high <= low) {
            return;
        }
        int i, j, t, temp;
        i = low;
        j = high;
        temp = array[low];
        while (i < j) {
            while (array[j] >= temp && i < j) {
                j--;
            }
            while (array[i] <= temp && i < j) {
                i--;
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


    private static void bubbleSort(int[] array) {
        int temp;
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    private static void selectSort(int[] array) {
        int temp, minIndex;
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[minIndex] > array[j + 1]) {
                    minIndex = j + 1;
                }
            }
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }


}
