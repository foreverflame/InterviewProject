package com.example.android;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Sort_algro {


    public static class ListNode<E> {
        ListNode(E data) {
            this.value = data;
        }

        E value;

        ListNode<E> next;
    }

    public static class Node<E> {
        E data;

        Node<E> next = null;

        Node(E data) {
            this.data = data;

        }
    }

    private void quickSort(int[] array, int low, int high) {
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

            t = array[i];
            array[i] = array[j];
            array[j] = t;
        }

        //i,j 如果相遇，则和基准值交换位置
        array[low] = array[j];
        array[j] = temp;

        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);
    }


    private void bubbleSort(int[] array) {
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

    private void selectSort(int[] array) {
        int temp, minMindex;
        for (int i = 0; i < array.length - 1; i++) {
            minMindex = i;
            for (int j = minMindex; j < array.length - 1; j++) {
                if (array[j + 1] < array[minMindex]) {
                    minMindex = j + 1;
                }
            }
            temp = array[i];
            array[i] = array[minMindex];
            array[minMindex] = temp;
        }
    }

    //递归
    private ListNode<Integer> merge(ListNode<Integer> l1, ListNode<Integer> l2) {

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode<Integer> head;

        if (l1.value < l2.value) {
            head = l1;
            head.next = merge(l1.next, l2);
        } else {
            head = l2;
            head.next = merge(l1, l2.next);
        }

        return head;
    }


    //迭代合并
    private ListNode<Integer> mergesimple(ListNode<Integer> l1, ListNode<Integer> l2) {

        ListNode<Integer> dummyHead = new ListNode<>(0);

        ListNode<Integer> curr = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                curr = l1;
                curr = curr.next;
                l1 = l1.next;
            } else {
                curr = l2;
                curr = curr.next;
                l2 = l2.next;
            }
        }

        //如果一条为null，则链接到另外一条
        if (l1 == null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }

        return dummyHead.next;
    }


    //判断链表是否有环,并且找到环的位置
    private void detecyRecycle(ListNode<Integer> head) {

        if (head == null) {
            return;
        }
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;
        boolean hasRecycle = false;

        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasRecycle = true;
                break;
            }
        }

        if (hasRecycle) {
            ListNode<Integer> entryNode = head;
            while (entryNode != slow) {
                entryNode = entryNode.next;
                slow = slow.next;
            }
        } else {
            return;
        }


    }


    private ListNode<Integer> reverseBySimple(ListNode<Integer> head) {
        ListNode<Integer> prev = null;
        ListNode<Integer> curr = head;
        while (curr != null) {
            ListNode<Integer> nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }


    private ListNode<Integer> reverse(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode<Integer> p = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return p;
    }


    public class MyStack<E> {
        int size;
        Object[] stack;

        MyStack() {
            this.stack = new Object[]{10};
        }

        private boolean isEmpty() {
            return size == 0;
        }

        private E peek() {

            if (isEmpty()) {
                return null;
            }
            Object object = stack[size - 1];
            return (E) object;
        }

        private E pop() {
            Object peek = peek();
            stack[size - 1] = null;
            size--;
            return (E) peek;
        }

        private E push(E data) {
            ensureSize(size + 1);
            stack[size + 1] = data;
            size++;
            return data;

        }

        private void ensureSize(int size) {
            int length = stack.length;
            if (size > length) {
                int newlenth = 10;
                stack = Arrays.copyOf(stack, newlenth);
            }

        }

    }

    public static class Myqueue<E> {

        Node<E> head;
        Node<E> tail;

        private boolean isEmpty() {
            return head == tail;
        }

        private void put(E data) {
            Node<E> newHead = new Node<>(data);

            if (head == null && tail == null) {

                head = tail = newHead;
            } else {
                tail.next = newHead;
                tail = newHead;
            }
        }


        private E pop() {
            if (isEmpty()) {
                return null;
            }

            E data = head.data;
            head = head.next;
            return data;

        }
    }


    //找出一个数组中和为某个数的索引
    private static int[] getSumIndex(int[] array, int target) {
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

    //数组的所有子集合
    private static List<List<Integer>> subSet(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        res.add(integers);

        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                ArrayList<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }

    private static void merge(int[] A, int m, int[] B, int n) {
        while (m > 0 && n > 0) {
            A[m + n - 1] = A[m - 1] > B[n - 1] ? A[m-- - 1] : B[n-- - 1];
        }

        while (n > 0) {
            A[n - 1] = B[n - 1];
            n--;
        }
    }

    private static int kMax(int[] nums) {
        if (nums == null || nums.length == 0) throw new RuntimeException("error");
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < nums.length - 1; i++) {
            set.add(nums[i]);
            if (set.size() > 2) {
                set.remove(set.first());
            }
        }
        return set.size() < 2 ? set.last() : set.first();
    }

    //求数组中的众数
    private static int majorNumbers(int[] nums) {
        int x = 0, votes = 0;

        for (Integer num : nums) {
            if (votes == 0) {
                x = num;
            }
            votes += num == x ? 1 : -1;

        }
        return x;
    }


    //删除数组中重复元素
    private static int deleteDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
           return 0;
        }

        int p = 0; //慢指针
        int q = 1; //快指针
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
