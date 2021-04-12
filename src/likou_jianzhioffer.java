import java.util.*;

/**
 * @Auther: chendongtao
 * @Date: 2021/4/8 12:24
 * @Description:
 */
public class likou_jianzhioffer {
    static List<Integer> tmp = new ArrayList<>();
    static Map<Integer, Integer> indexMap;

    public static void main(String[] args) {
        /*int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));*/
        /*int[][] matrix = {{1, 4, 7, 11, 15},{2, 5, 8, 12, 19},{3, 6, 9, 16, 22}
        };
        System.out.println(findNumberIn2DArray(matrix, 5));*/

        /*String s = "We are happy.";
        System.out.println(replaceSpace1(s));*/

        /*int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        buildTree(preorder, inorder);*/

        /*System.out.println(fib1(3));*/

        int[] nums = {2, 2, 2, 0, 1};
        System.out.println(minArray(nums));

    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }

    /**
     * 剑指 Offer 04. 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length, m = matrix[0].length;
        int row = n - 1, col = 0;
        while (row >= 0 && col < m) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                row--;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 05. 替换空格
     *
     * @param s
     * @return
     */
    public static String replaceSpace(String s) {
        return s.replaceAll("\\s", "%20");
    }

    public static String replaceSpace1(String s) {
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == ' ') {
                builder.append("%20");
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     *
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        ListNode node = head;
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        Collections.reverse(list);
        int[] array = new int[list.size()];
        for (Integer n : list) {
            array[i++] = n;
        }
        return array;
    }

    //栈
    public static int[] reversePrint1(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] print = new int[stack.size()];
        for (int i = 0; i < print.length; i++) {
            print[i] = stack.pop();
        }
        return print;
    }

    //递归
    public static int[] reversePrint2(ListNode head) {
        recur(head);
        int[] print = new int[tmp.size()];
        for (int i = 0; i < print.length; i++) {
            print[i] = tmp.get(i);
        }
        return print;
    }

    private static void recur(ListNode head) {
        if (head == null) {
            return;
        }
        recur(head.next);
        tmp.add(head.val);
    }

    /**
     * 剑指 Offer 07. 重建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length, m = inorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private static TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        //前序遍历的第一个节点就是根节点
        int preorder_root = preorder_left;
        //中序遍历中定位根节点
        Integer inorder_root = indexMap.get(preorder[preorder_root]);
        //建立根节点
        TreeNode root = new TreeNode(preorder[preorder_root]);
        //左子树的节点数目
        int left_tree_size = inorder_root - inorder_left;

        //递归左子树
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + left_tree_size, inorder_left, inorder_root - 1);
        //递归右子树
        root.right = myBuildTree(preorder, inorder, preorder_left + 1 + left_tree_size, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     */
    class CQueue {

        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                return -1;
            }
        }
    }

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    //迭代
    public static int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int first = 0, second = 1;

        int third = 0;
        for (int i = 2; i <= n; i++) {
            third = (first + second) % 1000000007;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     *
     * @param n
     * @return
     */
    public static int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int first = 1, second = 2;
        int third = 0;
        for (int i = 3; i <= n; i++) {
            third = (first + second) % 1000000007;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     *
     * @param numbers
     * @return
     */
    public static int minArray(int[] numbers) {
        int len = numbers.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }
        return numbers[left];
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        if ("".equals(word) || word == null) {
            return false;
        }
        return false;
    }
}



