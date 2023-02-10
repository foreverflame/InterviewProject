package com.example.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
     * 用两个队列实现栈
     */
    public class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }


    /**
     * 用两个链表实现队列
     */
    public class MyQueue<E> {
        ListNode<E> head;
        ListNode<E> tail;

        public boolean isEmpty() {
            return head == tail;
        }

        public void put(E data) {
            ListNode<E> newHead = new ListNode<>(data);
            if (head == null && tail == null) {
                head = tail = newHead;
            } else {
                tail.next = newHead;
                tail = newHead;
            }
        }

        public E pop() {
            if (isEmpty()) {
                return null;
            }
            E data = head.value;
            head = head.next;
            return data;
        }
    }

    /**
     * 用两个栈实现队列
     */
    public class MyQueue1 {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(Integer data) {
            stack1.push(data);
        }
        public Integer pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }
}
