package com.example.test.algorithm;

/**
 * @description: 链表相关操作
 * @author: huangyonghuang
 * @date: 2023/1/30
 */
public class NodeTest {

    public static class ListNode<E> {
        E value;

        ListNode(E data) {
            this.value = data;
        }

        ListNode(E data, ListNode<E> node) {
            this.value = data;
            this.next = node;
        }

        ListNode<E> next;
    }

    public static void main(String[] args) {
        ListNode<Integer> l1 = new ListNode<>(0);
        l1.next = new ListNode<>(2);
        l1.next.next = new ListNode<>(5);
        l1.next.next.next = new ListNode<>(7);
        l1.next.next.next.next = new ListNode<>(8);

        ListNode<Integer> l2 = new ListNode<>(1);
        l2.next = new ListNode<>(3);
        l2.next.next = new ListNode<>(4);
        l2.next.next.next = new ListNode<>(5);

        int length = length(l1);
        System.out.println(length);

    }


    /**
     * 递归合并两条有序链表
     */
    public ListNode<Integer> merge(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
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

    /**
     * 迭代合并两条链表
     */
    public ListNode<Integer> mergeSimple(ListNode<Integer> l1, ListNode<Integer> l2) {
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
        if (l1 == null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return dummyHead.next;
    }


    /**
     * 反转链表
     */
    public ListNode<Integer> reverse(ListNode<Integer> head) {
        ListNode<Integer> prev = null;
        ListNode<Integer> curr = head;
        while (curr != null) {
            ListNode<Integer> nextTmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }
        return prev;
    }

    /**
     * 移除链表中倒数第N个节点
     */
    public ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {
        ListNode<Integer> dummy = new ListNode<>(0, head);
        ListNode<Integer> first = head;
        ListNode<Integer> second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode<Integer> ans = dummy.next;
        return ans;
    }


    /**
     * 返回链表的中间节点
     */
    private static ListNode<Integer> middleNode(ListNode<Integer> head) {
        if (head == null) {
            return null;
        }
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /**
     * 给定某个节点，将这个节点从链表中删除
     */
    private static void deleteNode(ListNode<Integer> node) {
        node.value = node.next.value;
        node.next = node.next.next;
    }


    /**
     * 删除链表中倒数第n个节点
     */
    private static ListNode<Integer> deleteNthNode(ListNode<Integer> head, int n) {
        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 递归方法求链表的长度
     */
    private static int length(ListNode head) {
        if (head == null)
            return 0;
        return length(head.next) + 1;
    }


}
