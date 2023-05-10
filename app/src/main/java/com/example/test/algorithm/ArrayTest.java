package com.example.test.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @description: 数组相关测试
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class ArrayTest {

    public static void main(String[] args) {
//        int[] array = new int[]{1, 2, 5, 8, 9, 12, 30, 99};
//        int key = 30, low = 0, high = array.length - 1;
//        int findKeyIndex = binarySearch(array, key, low, high);
//        System.out.println("key index:" + findKeyIndex);
//        String str = "abcabcbb";
//        int count = lengthOfLongestSubstring(str);
//        String s = longestPalindrome1(str);
//        System.out.println(s);

//        int[] array1 = new int[]{0, 1, 1, 1, 1, 2, 2, 3, 3, 4};
//        int count = removeDuplicates(array1);
//        System.out.println(count);

//        int[] array1 = new int[]{1, 2, 3, 4, 5, 6, 7};
//        reverseNumK(array1, 3);
//        for (int j : array1) {
//            System.out.println(j);
//        }

//        int[] array1 = new int[]{1, 1, 9, 9, 9};
//        int i = singleNumber(array1);
//        System.out.println(i);

        int[] array1 = new int[]{1, 9};
        int[] ints = plusOne(array1);
        for (int j : ints) {
            System.out.println(j);
        }

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

    /**
     * 字符串最长子串长度 滑动窗口
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }

    /**
     * 最长回文子串
     * https://leetcode.cn/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-fa-he-dong-tai-gui-hua-by-reedfa/
     */
    public static String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }


    /**
     * 移除有序数组中重复元素，保持元素数组相对顺序不变
     */
    private static int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < A.length; right++) {
            if (A[left] != A[right]) {
                A[++left] = A[right];
            }
        }
        return ++left;
    }

    /**
     * 股票的最优收益
     */
    private int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max = max + (prices[i] - prices[i - 1]);
            }
        }
        return max;
    }


    /**
     * 旋转数组
     * https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2skh7/
     */
    private static int[] reverseNumK(int[] num, int k) {
        int length = num.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[i] = num[i];
        }
        for (int j = 0; j < length; j++) {
            num[(j + k) % length] = temp[j];
        }
        return num;
    }

    /**
     * 异或运算
     * 返回数组中出现一次的数
     */
    private static int singleNumber(int[] nums) {
        int reduce = 0;
        for (int num : nums) {
            reduce = reduce ^ num;
        }
        return reduce;
    }


    /**
     * 返回两个数组的交集
     * 通过两个指针
     */
    private static int[] interSet(int[] num1, int[] num2) {
        //要先排序
        Arrays.sort(num1);
        Arrays.sort(num2);
        int i = 0;
        int j = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while (i < num1.length && j < num2.length) {
            if (num1[i] < num2[j]) {
                i++;
            } else if (num1[i] > num2[j]) {
                j++;
            } else {
                list.add(num1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (int k = 0; k < list.size(); k++) {
            res[index++] = list.get(k);
        }
        return res;
    }

    /**
     * 数组加1进位
     */
    private static int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] temp = new int[length + 1];
        temp[0] = 1;
        return temp;
    }

    /**
     * 把数组中0的数都移动到末尾
     */
    private static void removeZero(int[] num) {
        if (num == null || num.length == 0) {
            return;
        }
        int index = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i] != 0) {
                num[index++] = num[i];
            }
        }
        while (index < num.length) {
            num[index++] = 0;
        }
    }


    /**
     * 获取数组中和为某个数的索引
     * [1,3,4,6,8,12] target=10
     * 用hash表存储数组
     */
    public static int[] getIndex(int[] array, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int[] index = new int[2];
        for (int i = 0; i < array.length - 1; i++) {
            if (hashMap.containsKey(array[i])) { //6已经存入hashmap中
                index[0] = hashMap.get(array[i]);
                index[1] = i;
            }
            hashMap.put(target - array[i], i);
            // 9-0,7-1,6-2
        }
        return index;
    }


    /**
     * 合并两个有序数组
     * 从后往前排
     */
    public static void getIndex(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums1[j--];
            }
        }
        while (j > 0) {
            nums1[k--] = nums2[j--];
        }
    }


}