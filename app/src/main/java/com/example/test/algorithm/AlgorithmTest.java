package com.example.test.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description: 数据结构
 * @author: huangyonghuang
 * @date: 2023/2/10
 */
public class AlgorithmTest {

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
