package com.example.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @author: huang
 * @date: 2026/5/7
 */
public class ByteAlg {


    /**
     * 括号是否匹配
     *
     * @param
     * @return
     */
    public boolean bracket(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                Character pop = stack.pop();
                if ((c == ')' && pop != '(') ||
                        c == ']' && pop != '[' ||
                        c == '}' && pop != '{'
                ) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     * 爬楼梯
     *
     * @param n
     * @return
     */
    public int claim(int n) {
        if (n <= 2) {
            return n;
        }
        int prev2 = 1;
        int prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev1 = prev2;
            prev2 = curr;
        }
        return prev1;
    }


    /**
     * 求所有集合的子集合
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> getSubsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            for (int i = 0; i < nums.length; i++) {
                ArrayList<Integer> tmp = new ArrayList<>(res.get(i));
                tmp.add(num);
                res.add(tmp);
            }
        }
        return res;
    }

    /**
     *
     * @param nums
     * @return
     */
    public Integer getSecondNums(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (Integer num : nums) {
            set.add(num);
            if (set.size() > 2) {
                set.remove(set.first());
            }
        }
        return set.size() < 2 ? null : set.last();
    }

    public int[] getIndex(int[] nums, int target) {

        int[] index = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                index[0] = i;
                index[1] = map.get(nums[i]);
            }
            map.put(target - nums[i], i);
        }
        return index;
    }


    /**
     * 删除数组重复元素
     *
     * @param num
     * @return
     */
    public int deleteDuplicateNum(int[] num) {
        int n = num.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (num[fast] != num[fast - 1]) {
                num[slow] = num[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }







}
