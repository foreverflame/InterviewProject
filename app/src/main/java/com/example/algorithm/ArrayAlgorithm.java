package com.example.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;


public class ArrayAlgorithm {

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 0, 0, 0};
        int[] B = new int[]{2, 5, 6};


        //集合的所有子集
        int[] nums = {1, 2, 3, 4, -8, 6};
        int[] C = {1, 2, 3, 2, 2, 2, 5, 4, 2};
//        List<List<Integer>> subsets = subsets(nums);
//        for (List<Integer> datas : subsets) {
//            System.out.println("----");
//            for (Integer integer : datas) {
//                System.out.println(integer + "");
//            }
//        }

        int[] sumIndex = getSumIndex(nums, 6);
//
        for (Integer integer : sumIndex) {
            System.out.println(integer);
        }

//        int secondNumbers = getSecondNumbers(nums);
//        System.out.println(secondNumbers);

//        merge(A, 3, B, 3);
//        for (int i = 0; i < A.length; i++) {
//            System.out.print(A[i] + ",");
//        }

//        int i = majorityElement(C);
//        System.out.print(i + ",");


    }

    /**
     * 求所有集合的子集
     */
    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList());
        for (int i = 0; i < nums.length; i++) {
            int all = res.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }


    /**
     * 一个数组中和为某个数的索引值
     */
    private static int[] getSumIndex(int[] array, int target) {
        //数组存储索引
        int[] index = new int[2];

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            if (hashMap.containsKey(array[i])) {
                index[0] = i;
                index[1] = hashMap.get(array[i]);
                return index;
            }
            hashMap.put(target - array[i], i);
        }

        return index;
    }

    /**
     * 数组中第k大数,通过treeset的有序
     */
    private static int getSecondNumbers(int[] nums) {
        if (nums == null || nums.length == 0) throw new RuntimeException("error");
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer elem : nums) {
            set.add(elem);
            if (set.size() > 2) {
                set.remove(set.first());
            }
        }
        return set.size() < 2 ? set.last() : set.first();   // set.last() 里面最大的元素
    }

    /**
     * 合并两个有序数组
     */
    private static void merge(int[] A, int m, int[] B, int n) {
        // 先确保将其中一个数组中的数字遍历完
        while (m > 0 && n > 0) {
            // 对比选出较大的数放在 m + n - 1 的位置，并将选出此数的指针向前移动
            A[m + n - 1] = A[m - 1] > B[n - 1] ? A[m-- - 1] : B[n-- - 1];
        }
        // 剩下的数都比已经遍历过的数小
        // 如果 m 不为 0，则 A 没遍历完，都已经在 A 中不用再管
        // 如果 n 不为 0，则 B 没遍历完，直接全移到 A 中相同的位置
        while (n > 0) {
            A[n - 1] = B[n - 1];
            n--;
        }
    }

    /**
     * 求数组中的众数，摩尔投票法
     */
    private static int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                x = num;
            }
            votes += num == x ? 1 : -1;
        }
        return x;
    }

    /**
     * 删除数组重复项
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution
     */
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }


}
