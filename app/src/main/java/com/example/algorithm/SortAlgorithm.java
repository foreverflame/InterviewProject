package com.example.algorithm;


public class SortAlgorithm {


    public static void main(String[] args) {
        int[] array = new int[]{8, 9, 5, 6, 0, 1, 3, 6, 2, 4, 7};

        //快速排序
        quickSort(array, 0, array.length - 1);

        //冒泡排序
//        bubbleSort(array);

        //选择排序
//        selectSort(array);

        for (int j : array) {
            System.out.println(j);
        }
    }


    /**
     * 最好附上链接，以便后期更好理解该算法的设计思想
     * <a href="https://www.cnblogs.com/captainad/archive/2019/06/10/10999697.html">...</a>
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
                t = array[j];
                array[j] = array[i];
                array[i] = t;

            }
        }

        array[low] = array[j];
        array[j] = temp;

        //分别递归左右列表
        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);
    }


    /**
     * 冒泡排序,相邻的数字相互比较，大的数往后面移动
     * <a href="https://blog.csdn.net/NathanniuBee/article/details/83413879">...</a>
     */
    public static void bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;  // 每轮开始重置标志位
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    swapped = true;  // 发生了交换
                }
            }
            if (!swapped) {  // 本轮无交换，数组已有序，直接退出
                break;
            }
        }
    }


    /**
     * 选择排序
     * https://zhuanlan.zhihu.com/p/123048793
     */
    private static void selectSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {  // 直接遍历，语义清晰
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }

}
