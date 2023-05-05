package com.example.test.algorithm;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: 二叉树
 * @author: huangyonghuang
 * @date: 2023/1/31
 */
public class TreeNodeTest {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
//        int[] preOrder = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
//        int[] inOrder = {8, 4, 9, 2, 10, 5, 1, 6, 3, 7};
//        binaryTree.initTree(preOrder, inOrder);
//        System.out.println("二叉树的后序遍历");
//        binaryTree.postOrder();

        //构建二叉树
        int[] preOrder = {1, 2, 4, 8, 9, 5, 10, 3, 6, 7};
        binaryTree.buildTree(preOrder);
        preOrder(binaryTree.root);

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

        //构建二叉树
        private void buildTree(int[] data) {
            for (int datum : data) {
                insert(datum);
            }
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

        private void postOrder() {
            this.postOrder(this.root);
        }

        //二叉树的后序遍历 左-右-根
        private void postOrder(TreeNode localNode) {
            if (localNode != null) {
                postOrder(localNode.left);
                postOrder(localNode.right);
                System.out.println(localNode.data + "");
            }
        }

        /**
         * 已知先序遍历，中序遍历，求后序遍历
         */
        private void initTree(int[] pre, int[] in) {
            this.root = initLocalTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        }

        private TreeNode initLocalTree(int[] pre, int start1, int end1, int[] in, int start2, int end2) {
            if (end1 < start1 || end2 < start2) {
                return null;
            }
            //二叉树的根节点数据
            int rootData = pre[start1];
            //二叉树头结点
            TreeNode head = new TreeNode(rootData);
            //找找头结点再中序遍历中的索引值
            int indexInInOrder = findIndexInInOrder(in, rootData, start2, end2);
            //中序遍历的偏移量
            int offset = indexInInOrder - start2 - 1;
            //构建左子树
            TreeNode left = initLocalTree(pre, start1 + 1, start1 + 1 + offset, in, start2, start2 + offset);
            //构建右子树
            TreeNode right = initLocalTree(pre, start1 + offset + 2, end1, in, indexInInOrder + 1, end2);
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
     * 先序遍历
     */
    private static void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    private static void centerOrder(TreeNode node) {
        if (node != null) {
            centerOrder(node.left);
            System.out.println(node.data);
            centerOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    private static void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }
    }


    /**
     * 二叉树的深度
     */
    private static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 验证二叉搜索树
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
        return isValidBST(node.left, min, node.data) && isValidBST(node.right, node.data, max);
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
     * 二叉树对称
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        //从两个子节点开始判断
        return isSymmetricHelper(root.left, root.right);
    }

    public boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        //如果左右子节点都为空，说明当前节点是叶子节点，返回true
        if (left == null && right == null)
            return true;
        //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.data != right.data)
            return false;
        //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }


    /**
     * 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
        //边界条件判断
        if (root == null)
            return;
        //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
        //要先把下一层的list初始化，防止下面add的时候出现空指针异常
        if (level >= list.size()) {
            list.add(new ArrayList<>());
        }
        //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
        list.get(level).add(root.data);
        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        levelHelper(list, root.left, level + 1);
        levelHelper(list, root.right, level + 1);
    }

}
