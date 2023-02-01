package com.example.test.algorithm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: lruCache 实现
 * @Author: huangyonghuang
 * @CreateDate: 2023/2/1 22:03
 */
public class LruCacheTest {

    class DLinkNode {
        String key;
        int value;
        DLinkNode pre;
        DLinkNode post;
    }

    private ConcurrentHashMap<String, DLinkNode> cache = new ConcurrentHashMap<>();
    private int count;
    private int capacity;
    private DLinkNode head, tail;

    public LruCacheTest(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkNode();
        head.pre = null;

        tail = new DLinkNode();
        head.post = null;

        head.post = tail;
        tail.pre = head;
    }


    public void put(String key, int value) {
        DLinkNode node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
            return;
        }
        DLinkNode newNode = new DLinkNode();
        newNode.key = key;
        newNode.value = value;
        cache.put(key, newNode);
        addNode(newNode);
        ++count;
        if (count > capacity) {
            DLinkNode tail = popTail();
            cache.remove(tail.key);
            --count;
        }
    }


    public int get(String key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addNode(node);
    }

    private void removeNode(DLinkNode node) {
        DLinkNode pre = node.pre;
        DLinkNode post = node.post;
        pre.post = post;
        post.pre = pre;
    }

    private void addNode(DLinkNode node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
    }

    private DLinkNode popTail() {
        DLinkNode res = tail.pre;
        removeNode(res);
        return res;
    }


}