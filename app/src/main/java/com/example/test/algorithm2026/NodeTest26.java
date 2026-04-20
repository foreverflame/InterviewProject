package com.example.test.algorithm2026;

public class NodeTest26 {

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


    public ListNode<Integer> mergeNode(ListNode<Integer> l1, ListNode<Integer> l2) {

        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode<Integer> head = null;
        if (l1.value < l2.value) {
            head = l1;
            head.next = mergeNode(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeNode(l1, l2.next);
        }

        return head;
    }


    private ListNode<Integer> reverseNode(ListNode<Integer> head) {
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


    /**
     * 递归方法求链表的长度
     */
    private static int length(ListNode head) {
        if (head == null)
            return 0;
        return length(head.next) + 1;
    }

}
