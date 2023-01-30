package com.example.test;

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

        ListNode<E> next;
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
}
