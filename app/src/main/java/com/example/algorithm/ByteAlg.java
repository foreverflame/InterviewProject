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

    public static class ListNode<E> {
        ListNode(E data) {
            this.value = data;
        }

        E value;
        ListNode<Integer> next;
    }

    public static class Node<E> {
        E data;
        Node<E> next = null;

        Node(E data) {
            this.data = data;
        }
    }


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


    /**
     * 合并两个有序数组，把数组放到A中
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[k] = A[i];
                i--;
            } else {
                A[k] = B[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            A[k] = B[j];
            j--;
            k--;
        }
    }

    /**
     * 二分查找
     *
     * @param num
     * @param low
     * @param high
     * @param key
     * @return
     */
    public int binarySearch(int[] num, int low, int high, int key) {
        if (num[low] > key || num[high] < key || low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (num[mid] > key) {
            return binarySearch(num, low, mid - 1, key);
        } else if (num[mid] < key) {
            return binarySearch(num, mid + 1, high, key);
        } else {
            return mid;
        }
    }


    /**
     * 用快慢指针
     * @param head
     * @return
     */
    public ListNode<Integer> findNodeRecycle(ListNode<Integer> head) {
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;
        boolean isRecycle = false;
        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isRecycle = true;
                break;
            }
        }
        if (isRecycle) {
            ListNode<Integer> start = head;
            while (start != slow) {
                start = start.next;
                slow = slow.next;
            }
            return slow;

        } else {
            return null;
        }
    }


}
