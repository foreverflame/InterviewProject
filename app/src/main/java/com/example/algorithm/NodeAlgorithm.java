package com.example.algorithm;


import java.util.Arrays;

public class NodeAlgorithm {

    public static class ListNode<E> {
        ListNode(E data) {
            this.value = data;
        }

        E value;
        ListNode<E> next;
    }


    //递归
    private ListNode<Integer> merge(ListNode<Integer> l1, ListNode<Integer> l2) {

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


    //迭代合并
    private ListNode<Integer> mergeSimple(ListNode<Integer> l1, ListNode<Integer> l2) {

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
    private void detectRecycle(ListNode<Integer> head) {

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

    public static class MyQueue<E> {

        ListNode<E> head;
        ListNode<E> tail;

        private boolean isEmpty() {
            return head == tail;
        }

        private void put(E data) {
            ListNode<E> newHead = new ListNode<>(data);

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
            E data = head.value;
            head = head.next;
            return data;
        }
    }

}
