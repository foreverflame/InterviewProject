package com.example.test.algorithm;

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

        String str = "abcabcbb";
//        int count = lengthOfLongestSubstring(str);

        String s = longestPalindrome1(str);
        System.out.println(s);

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

}
