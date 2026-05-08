package com.example.algorithm;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * 二叉树
 * https://blog.csdn.net/My_Jobs/article/details/43451187
 */
public class TreeNode {


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        int[] preOrder = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
        int[] inOrder = {8, 4, 9, 2, 10, 5, 1, 6, 3, 7};
        binaryTree.initTree(preOrder, inOrder);
        System.out.println("二叉树的后序遍历");
        binaryTree.postOrder();
    }


    //二叉树的数据结构
    public static class MyTreeNode {
        MyTreeNode left;
        MyTreeNode right;
        int data;

        MyTreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }


    //构建二叉树
    public static class BinaryTree {
        MyTreeNode root;
        BinaryTree() {
            root = null;
        }
        //二叉树中插入数据
        private void insert(int data) {
            MyTreeNode newMyTreeNode = new MyTreeNode(data);
            if (root == null) {
                root = newMyTreeNode;
            } else {
                MyTreeNode current = root;
                MyTreeNode parent;
                while (true) {
                    //用parent 节点存储current
                    parent = current;
                    if (data < current.data) {
                        current = current.left;
                        if (current == null) {
                            parent.left = newMyTreeNode;
                            return;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            parent.right = newMyTreeNode;
                            return;
                        }
                    }
                }
            }
        }

        //构建二叉树
        private void buildTree(int[] data) {
            for (int i = 0; i < data.length; i++) {
                insert(data[i]);
            }
        }

        //二叉树的先序遍历 根-左-右
        private void preOrder(MyTreeNode localMyTreeNode) {
            if (localMyTreeNode != null) {
                System.out.println(localMyTreeNode.data);
                preOrder(localMyTreeNode.left);
                preOrder(localMyTreeNode.right);
            }
        }

        //二叉树的中序遍历 左-根-右
        private void centerOrder(MyTreeNode localMyTreeNode) {
            if (localMyTreeNode != null) {
                centerOrder(localMyTreeNode.left);
                System.out.println(localMyTreeNode.data);
                centerOrder(localMyTreeNode.right);
            }
        }

        //二叉树的后序遍历 左-右-根
        private void postOrder(MyTreeNode localMyTreeNode) {
            if (localMyTreeNode != null) {
                postOrder(localMyTreeNode.left);
                postOrder(localMyTreeNode.right);
                System.out.println(localMyTreeNode.data + "");
            }
        }

        private void postOrder() {
            this.postOrder(this.root);
        }

        /**
         * 深度优先 利用栈
         * 其实深度遍历就是上面的前序、中序和后序。但是为了保证与广度优先遍历相照应，也写在这。代码也比较好理解，其实就是前序遍历
         */
        private void depthTraversal() {
            if (root == null) {
                System.out.println("root is empty");
            }
            Stack<MyTreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                MyTreeNode pop = stack.pop();
                if (pop.left != null) {
                    stack.push(pop.left);
                }
                if (pop.right != null) {
                    stack.push(pop.right);
                }
            }
            System.out.println("/n");
        }


        /**
         * 广度优先 利用队列 （就是我们经常说的层次遍历）
         */
        private void levelTraversal() {
            if (root == null) {
                System.out.println("root is empty");
            }
            ArrayDeque<MyTreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                //队列
                MyTreeNode myTreeNode = queue.remove();
                if (myTreeNode.left != null) {
                    queue.add(myTreeNode.left);
                }
                if (myTreeNode.right != null) {
                    queue.add(myTreeNode.right);
                }
            }
            System.out.println("/n");
        }

        /**
         * 已知先序遍历，中序遍历，求后序遍历
         */
        private void initTree(int[] pre, int[] in) {
            this.root = initLocalTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        }

        private MyTreeNode initLocalTree(int[] pre, int start1, int end1, int[] in, int start2, int end2) {

            if (end1 < start1 || end2 < start2) {
                return null;
            }
            //二叉树的根节点数据
            int rootData = pre[start1];
            //二叉树头结点
            MyTreeNode head = new MyTreeNode(rootData);
            //找找头结点再中序遍历中的索引值
            int indexInInOrder = findIndexInInOrder(in, rootData, start2, end2);
            //中序遍历的偏移量
            int offset = indexInInOrder - start2 - 1;
            //构建左子树
            MyTreeNode left = initLocalTree(pre, start1 + 1, start1 + 1 + offset, in, start2, start2 + offset);
            //构建右子树
            MyTreeNode right = initLocalTree(pre, start1 + offset + 2, end1, in, indexInInOrder + 1, end2);
            head.left = left;
            head.right = right;
            return head;
        }

        private int findIndexInInOrder(int[] in, int rootData, int start2, int end2) {
            for (int i = start2; i <= end2; i++) {
                if (in[i] == rootData) {
                    return i;
                }
            }
            return -1;
        }
    }


    /**
     * 二叉树对称
     */
    public boolean isSymmetric(MyTreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(MyTreeNode t1, MyTreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.data == t2.data)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    /**
     * 二叉树对称迭代法
     */
    public boolean isSymmetricByDiedai(MyTreeNode root) {
        Queue<MyTreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            MyTreeNode t1 = q.poll();
            MyTreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.data != t2.data) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

}
