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
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int twoBack = 1;   // n-2
        int oneBack = 2;   // n-1
        for (int i = 3; i <= n; i++) {
            int current = oneBack + twoBack;
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    public int climbStairs1(int n) {
        int[] memo = new int[n + 1];  // 0 表示没算过
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (n <= 2) {
            return n;
        }
        // 算过了直接返回
        if (memo[n] != 0) {
            return memo[n];
        }
        // 没算过就算，并存起来
        memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
        return memo[n];
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
     *
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


    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode<Integer> mergeNode(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode<Integer> head;
        if (l1.value <= l2.value) {
            head = l1;
            head.next = mergeNode(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeNode(l1, l2.next);
        }
        return head;

    }

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public ListNode<Integer> reverseNode(ListNode<Integer> head) {
        ListNode<Integer> prev = null;
        ListNode<Integer> curr = head;
        while (curr != null) {
            ListNode<Integer> tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public boolean isSystem(TreeNode.MyTreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode.MyTreeNode t1, TreeNode.MyTreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.data == t2.data) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }


    public void quickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int i, j, temp, t;
        i = low;
        j = high;
        temp = array[low];
        while (i < j) {
            while (array[j] >= temp && i < j) {
                j--;
            }
            while (array[i] <= temp && i < j) {
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
     * 冒泡排序
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
     */
    private static void selectSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
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

    /**
     * 爬楼梯，用滚动窗口
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int oneBack = 2;
        int twoBack = 1;
        //i 值从3开始
        for (int i = 3; i <= n; i++) {
            int current = oneBack + twoBack;
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    /**
     * 删除数组中重复的元素，用快慢指针
     *
     * @param array
     * @return
     */
    public int removeDuplicate(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int n = array.length;
        int slow = 1;
        int fast = 1;
        while (fast < n) {
            if (array[fast] != array[fast - 1]) {
                array[slow] = array[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public void mergeNums(int[] A, int m, int[] B, int n) {
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


    // 时间复杂度 O(log n), 空间复杂度 O(1)
    // [1,2,3,3,3,3,4,5,9]
    public int[] searchRange2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int first = -1;
        int last = -1;
        // 找第一个等于target的位置
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                first = middle;
                right = middle - 1; //重点
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        // 最后一个等于target的位置
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                last = middle;
                left = middle + 1; //重点
            } else if (nums[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return new int[]{first, last};
    }


}
