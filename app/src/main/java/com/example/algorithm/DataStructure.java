package com.example.algorithm;


import java.util.Arrays;


public class DataStructure {


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


    public static void main(String[] args) {

        //l1和l2两个链表是有序的
        ListNode<Integer> l1 = new ListNode<>(0);
        l1.next = new ListNode<>(2);
        l1.next.next = new ListNode<>(5);
        l1.next.next.next = new ListNode<>(7);
        l1.next.next.next.next = new ListNode<>(8);

        ListNode<Integer> l2 = new ListNode<>(1);
        l2.next = new ListNode<>(3);
        l2.next.next = new ListNode<>(4);
        l2.next.next.next = new ListNode<>(5);

//        ListNode<Integer> resultList = mergeTwoLists(l1, l2);
//        printList(resultList);
//        ListNode<Integer> integerListNode = mergeTwoListsBySimple(l1, l2);

        ListNode<Integer> integerListNode = reverse(l1);
        printList(integerListNode);

    }


    private static void printList(ListNode<Integer> last) {
        while (last != null) {
            System.out.println(last.value + ",");
            last = last.next;
        }
    }


    /**
     * 判断一个链表是否有环，并且打印返回环的第一个节点
     */
    public static Node detectRecycle(Node head) {
        //使用快慢指针，判断是否有环
        if (head == null) {
            return null;
        }

        Node p = head; //慢指针
        Node p2 = head;//快指针
        boolean hasCycle = false;

        while (p.next != null && p2.next.next != null) {
            p = p.next;
            p2 = p2.next.next;
            if (p == p2) {
                hasCycle = true;
                break;
            }
        }

        //如果有环，找到入环的开始节点
        if (hasCycle) {
            Node q = head;
            while (p != q) {
                p = p.next;
                q = q.next;
            }
            return q;

        } else {
            return null;

        }
    }


    /**
     * 合并两个有序链表，简单的迭代法
     */
    private static ListNode<Integer> mergeTwoListsBySimple(ListNode<Integer> l1, ListNode<Integer> l2) {
        // 类似归并排序中的合并过程
        ListNode<Integer> dummyHead = new ListNode<>(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }

    /**
     * 递归方式合并两个有序链表
     * 说实话这种方法是没有理解的，看下能否用迭代的方式写一个合并链表的方法
     */
    private static ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode<Integer> head;
        if (l1.value <= l2.value) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }


    // 递归反转链表，递归实现，需要理解其思想
    private static ListNode<Integer> reverse(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode<Integer> reverseNode = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reverseNode;
    }


    /**
     * 链表反转
     */
    private static ListNode<Integer> reverseBySimple(ListNode<Integer> head) {
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


    //用数组实现栈
    public class MyStack<E> {
        int size;

        Object[] stack;

        MyStack() {
            stack = new Object[10];
        }


        private boolean isEmpty() {
            return size == 0;
        }

        private E peek() {
            if (isEmpty()) {
                return null;
            }
            return (E) stack[size - 1];
        }

        private E pop() {
            E peek = peek();
            stack[size - 1] = null;
            size--;
            return peek;
        }


        private E put(E data) {
            //扩充栈的大小
            ensuryCapsule(size + 1);

            stack[size++] = data;
            return data;
        }

        private void ensuryCapsule(int size) {
            int length = stack.length;
            if (size > length) {
                int newlength = 10;//每次都扩充10
                stack = Arrays.copyOf(stack, newlength);
            }

        }
    }


    //用链表实现队列
    public static class MyQueue<E> {
        Node<E> head = null;
        Node<E> tail = null;

        private boolean isEmpty() {
            return head == tail;
        }

        private void put(E data) {
            Node<E> newNode = new Node<E>(data);

            if (head == null && tail == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                //newnode 为最后一个节点
                tail = newNode;
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

    /**
     * 递归二分查找
     *
     * @return 找到位置的索引
     */
    private int binarySearch(int[] array, int key, int low, int high) {

        if (array[low] > key || array[high] < key || low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        //如果是用递归的话，就用if
        if (array[mid] > key) {
            return binarySearch(array, key, low, mid - 1);
        } else if (array[mid] < key) {
            return binarySearch(array, key, mid + 1, high);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找，遍历法 时间复杂度logn 控件复杂度1
     * 坑比较多，尤其是边界，一定要考虑清楚
     */
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;//注意
        while (left <= right) {//注意，需要加上等于号
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;//注意
            } else if (nums[mid] > target) {
                right = mid - 1;//注意
            }

        }
        return -1;


    }


}
