package com.example.test.algorithm;

/**
 * @description: 二叉树
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class TreeNodeTest {

    public static void main(String[] args) {

    }


    //二叉树的数据结构
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int data;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }


    public static class BinaryTree {
        TreeNode root;

        private BinaryTree() {
            root = null;
        }

        private void insert(int data) {
            TreeNode newNode = new TreeNode(data);
            if (root == null) {
                root = newNode;
            } else {
                TreeNode current = root;
                TreeNode parent;
                while (true) {
                    parent = current;
                    if (data < current.data) {
                        current = current.left;
                        if (current == null) {
                            parent.left = newNode;
                            return;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            parent.right = newNode;
                            return;
                        }
                    }
                }
            }
        }
    }


    /**
     * 先序遍历
     */
    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    private void centerOrder(TreeNode node) {
        if (node != null) {
            centerOrder(node.left);
            System.out.println(node.data);
            centerOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    private void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }
    }


    /**
     * 递归判断二叉树是否对称
     */
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.data == t2.data) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }


    /**
     * 二叉树的深度
     */
    private int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 验证二叉搜索数
     * https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn08xg/?discussion=69ga70
     */
    public boolean isValidBST(TreeNode node) {
        return isValidBST(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.data <= min || node.data >= max) {
            return false;
        }
        //左子树
        return isValidBST(node.left, min, node.data) && isValidBST(node.right, node.data, max);
    }


}
