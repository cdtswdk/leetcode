import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * @Auther: chendongtao
 * @Date: 2021/4/17 11:29
 * @Description:
 */
public class likou_jianzhioffer_1 {
    public static void main(String[] args) {
        /*ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        mergeTwoLists(node1, node4);*/

        /*TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        *//*TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);*//*
        node1.left = node2;
        *//*node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;*//*
        mirrorTree(node1);*/

        /*TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        *//*TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);*//*
        node1.left = node2;
        *//*node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;*//*
        System.out.println(isSymmetric(node1));*/

//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] matrix = {{7}, {9}, {6}};
        int[] spiralOrder = spiralOrder(matrix);
        for (int i : spiralOrder) {
            System.out.printf(i + " ");
        }

    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表 递归
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    //双指针迭代
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    /**
     * 剑指 Offer 26. 树的子结构
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean recur(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }
        return recur(a.left, b.left) && recur(a.right, b.right);
    }

    //BFS
    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(A);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == B.val) {
                if (helper(node, B)) {
                    return true;
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return false;
    }

    private boolean helper(TreeNode nodeA, TreeNode nodeB) {
        Queue<TreeNode> queueA = new LinkedList<>();
        Queue<TreeNode> queueB = new LinkedList<>();
        queueA.offer(nodeA);
        queueB.offer(nodeB);
        while (!queueB.isEmpty()) {
            nodeA = queueA.poll();
            nodeB = queueB.poll();
            if (nodeA == null || nodeA.val != nodeB.val) {
                return false;
            }
            if (nodeB.left != null) {
                queueA.offer(nodeA.left);
                queueB.offer(nodeB.left);
            }
            if (nodeB.right != null) {
                queueA.offer(nodeA.right);
                queueB.offer(nodeB.right);
            }
        }
        return true;
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = swapTreeNode(root, root.left, root.right);
        if (node == null) {
            return root;
        }
        return node;
    }

    private static TreeNode swapTreeNode(TreeNode root, TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return null;
        }
        root.left = right;
        root.right = left;

        if (root.left != null) {

            swapTreeNode(root.left, root.left.left, root.left.right);
        }
        if (root.right != null) {

            swapTreeNode(root.right, root.right.left, root.right.right);
        }

        return root;
    }

    //递归
    public static TreeNode mirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);

        return root;
    }

    //辅助栈
    public static TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        /*if (root.left.val != root.right.val) {
            return false;
        }*/
        return isSymmetricHelper(root.left, root.right);
    }

    private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        } else {
            return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
        }
    }

    //迭代法
    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     *
     * @param matrix
     * @return
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{};
        }
        int n = matrix.length, m = matrix[0].length;
        int[] res = new int[n * m];
        int index = 0;
        int firstRow = 0, lastRow = n - 1, firstCol = 0, lastCol = m - 1;
        while (true) {

            if (firstRow > lastRow || firstCol > lastCol) {
                break;
            }
            //从左到右
            for (int i = firstCol; i <= lastCol; i++) {
                res[index++] = matrix[firstRow][i];
            }
            if (index == n * m) {
                break;
            }
            firstRow++;
            //从上到下
            for (int j = firstRow; j <= lastRow; j++) {
                res[index++] = matrix[j][lastCol];
            }
            if (index == n * m) {
                break;
            }
            lastCol--;
            //从右到左
            for (int i = lastCol; i >= firstCol; i--) {
                res[index++] = matrix[lastRow][i];
            }
            if (index == n * m) {
                break;
            }
            lastRow--;
            //从下到上
            for (int j = lastRow; j >= firstRow; j--) {
                res[index++] = matrix[j][firstCol];
            }
            if (index == n * m) {
                break;
            }
            firstCol++;
        }
        return res;
    }
}

class MinStack {

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {

    }

    public void pop() {

    }

    public int top() {
        return -1;
    }

    public int min() {
        return -1;
    }
}
