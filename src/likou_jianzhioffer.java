import sun.reflect.generics.tree.Tree;

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

        /*int[] nums = {2, 2, 2, 0, 1};
        System.out.println(minArray(nums));*/

        /*char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
//        char[][] board = {{'a', 'b'}, {'c', 'd'}};
//        char[][] board = {{'a', 'a'}};
//        char[][] board = {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
        System.out.println(exist(board, "ABCESEEEFS"));*/

        /*System.out.println(movingCount1(3, 2, 17));*/

        /*ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        deleteNode(node1, 9);*/

        /*System.out.println(hammingWeight(00000000000000000000000000001011));*/

        /*System.out.println(myPow(2.00000, -2147483648));*/

        /*int[] nums = {5, 4, 3, 2, 1};
        exchange(nums);*/

        /*System.out.println(cuttingRope2_2(10));*/

        /*ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        reverseList2(node1);*/

        /*int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));*/

        /*int len = lengthOfLongestSubstring("abcabcbb");
        System.out.println(len);*/

        String s = "aab", p = "c*a*b*";
        System.out.println(isMatch1(s, p));

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
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        if ("".equals(word) || word == null) {
            return false;
        }

        char[] chars = word.toCharArray();
        Deque<int[]> queue = new ArrayDeque<>();
        int m = board.length, n = board[0].length;
        int[][] flags = new int[m][n];
        //把开头等于字符串的第一个字母加进队列中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == chars[0]) {
                    queue.push(new int[]{i, j});
                    flags[i][j] = 1;
                } else {
                    flags[i][j] = -1;
                }
            }
        }
        int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] node = queue.pop();
            int index = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    flags[i][j] = -1;
                }
            }
            boolean path = findPath(board, chars, flags, moves, node, index);
            if (path) {
                return true;
            }
        }
        return false;
    }

    private static boolean findPath(char[][] board, char[] word, int[][] flags, int[][] moves, int[] node, int index) {
        index++;
        if (index == word.length) {
            return true;
        }
        int r1 = node[0], c1 = node[1];
        flags[r1][c1] = 1;
        //对当前节点的四个方向进行遍历
        for (int[] move : moves) {
            int r2 = r1 + move[0], c2 = c1 + move[1];
            if (inArea(board, r2, c2) && flags[r2][c2] == -1 && board[r2][c2] == word[index]) {
                int[] newNode = {r2, c2};
                boolean path = findPath(board, word, flags, moves, newNode, index);
                if (path) {
                    return true;
                }
            }
        }
        flags[r1][c1] = -1;
        return false;
    }

    private static boolean inArea(char[][] board, int r2, int c2) {
        return r2 >= 0 && r2 <= board.length - 1 && c2 >= 0 && c2 <= board[0].length - 1;
    }

    /**
     * 剑指 Offer 13. 机器人的运动范围
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public static int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int count = 0;
        int[][] flags = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(flags[i], -1);
        }
        count += dfsMovingCount(m, n, k, count, 0, 0, flags);
        return count;
    }

    private static int dfsMovingCount(int m, int n, int k, int count, int row, int col, int[][] flags) {
        if (row >= m || row < 0 || col >= n || col < 0 || flags[row][col] == 1) {
            return 0;
        }
        int tempRow = row, tempCol = col, temp = 0;
        while (tempRow != 0) {
            temp += tempRow % 10;
            tempRow /= 10;
        }
        while (tempCol != 0) {
            temp += tempCol % 10;
            tempCol /= 10;
        }
        if (temp > k) {
            return 0;
        }
        count++;
        flags[row][col] = 1;
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] move : moves) {
            int r2 = row + move[0], c2 = col + move[1];
            count = Math.max(count, dfsMovingCount(m, n, k, count, r2, c2, flags));
        }
        return count;
    }

    //BFS
    public static int movingCount1(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int res = 1;
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        int[][] moves = {{0, 1}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] node = queue.pop();
            int r1 = node[0], c1 = node[1];
            for (int[] move : moves) {
                int r2 = r1 + move[0], c2 = c1 + move[1];
                if (r2 < 0 || r2 >= m || c2 < 0 || c2 >= n || visited[r2][c2] || panduan(r2, c2) > k) {
                    continue;
                }
                res++;
                visited[r2][c2] = true;
                queue.push(new int[]{r2, c2});
            }
        }
        return res;
    }

    private static int panduan(int r2, int c2) {

        int tempRow = r2, tempCol = c2, temp = 0;
        while (tempRow != 0) {
            temp += tempRow % 10;
            tempRow /= 10;
        }
        while (tempCol != 0) {
            temp += tempCol % 10;
            tempCol /= 10;
        }
        return temp;
    }

    /**
     * 剑指 Offer 14- I. 剪绳子
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) (Math.pow(3, a - 1) * 4);
        }
        return (int) (Math.pow(3, a) * 2);
    }

    //动态规划
    public int cuttingRope_1(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i] % 1000000007, Math.max((j * (i - j)) % 1000000007, (j * dp[i - j])) % 1000000007);
            }
        }
        return dp[n];
    }

    //贪心
    public int cuttingRope_2(int n) {
        if (n < 4) {
            return n - 1;
        }
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }

    /**
     * 剑指 Offer 14- II. 剪绳子 II
     *
     * @param n
     * @return
     */
    public int cuttingRope2(int n) {
        //贪心算法
        if (n < 4) {
            return n - 1;
        }
        long res = 1;
        while (n > 4) {
            res = res * 3 % 1000000007;
            n = n - 3;
        }
        return (int) (res * n % 1000000007);
    }

    //快速幂求余
    public static int cuttingRope2_1(int n) {
        if (n < 4) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        long res = 1, x = 3;
        for (int a = n / 3 - 1; a > 0; a /= 2) {
            if (a % 2 == 1) {
                res = (res * x) % p;
            }
            x = (x * x) % p;
        }
        if (b == 0) {
            return (int) ((res * 3) % p);
        }
        if (b == 1) {
            return (int) ((res * 4) % p);
        }
        return (int) ((res * 6) % p);
    }

    //快速幂求余
    public static int cuttingRope2_2(int n) {
        if (n < 4) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        long res = 1, x = 3;
        for (int i = 1; i < n / 3; i++) {
            res = (res * x) % p;
        }
        if (b == 0) {
            return (int) ((res * 3) % p);
        }
        if (b == 1) {
            return (int) ((res * 4) % p);
        }
        return (int) ((res * 6) % p);
    }

    /**
     * 剑指 Offer 15. 二进制中1的个数 you need to treat n as an unsigned value
     *
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        return Integer.toBinaryString(n).replaceAll("0", "").length();
    }

    //按位与 1 & 0 = 0
    public static int hammingWeight1(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n = n >>> 1;
        }

        return res;
    }

    //n & (n-1)
    public static int hammingWeight2(int n) {
        int res = 0;
        while (n != 0) {
            res += 1;
            n = n & (n - 1);
        }
        return res;
    }

    /**
     * 剑指 Offer 16. 数值的整数次方
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        double res = 1;
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b = b >> 1;
        }
        return res;
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        int[] res = new int[(int) Math.pow(10, n) - 1];

        for (int i = 0; i < Math.pow(10, n) - 1; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode temp = head;
        while (temp.next != null) {
            ListNode next = temp.next;
            if (next.val == val) {
                temp.next = next.next;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    //双指针
    public static ListNode deleteNode1(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = pre.next;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = cur.next;
        }
        return head;
    }

    //单指针
    public static ListNode deleteNode2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        if (cur.next != null) {
            cur.next = cur.next.next;
        }
        return head;
    }

    /**
     * 剑指 Offer 19. 正则表达式匹配
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //空正则
                if (j == 0) {
                    dp[i][j] = i == 0;
                } else {
                    //非空正则，分为两种情况
                    //最后一个字符为非*
                    if (p.charAt(j - 1) != '*') {
                        if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        //最后一个字符为*，分为看和不看
                        //看
                        if (j >= 2) {
                            dp[i][j] |= dp[i][j - 2];
                        }
                        //不看
                        if (i >= 1 && j >= 2 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }
        }
        return dp[n][m];
    }

    //递归
    public static boolean isMatch1(String s, String p) {
        if (s.length() == 0) {
            if (p.length() % 2 != 0) {
                return false;
            }
            int i = 1;
            while (i < p.length()) {
                if (p.charAt(i) != '*') {
                    return false;
                }
                i += 2;
            }
            return true;
        }
        //字符串长度不为0，但是匹配串长度为0，直接返回false
        if (p.length() == 0) {
            return false;
        }
        char c1 = s.charAt(0), c2 = p.charAt(0), c3 = 'a';
        if (p.length() > 1) {
            c3 = p.charAt(1);
        }
        //分为后一位是不是'*'的情况
        if (c3 != '*') {
            if (c1 == c2 || c2 == '.') {
                return isMatch1(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        } else {
            if (c1 == c2 || c2 == '.') {
                return isMatch1(s.substring(1), p) || isMatch1(s, p.substring(2));
            } else {
                return isMatch1(s, p.substring(2));
            }
        }
    }

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     *
     * @param nums
     * @return
     */
    public static int[] exchange(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[j] % 2 == 1) {
                if (nums[i] % 2 == 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    j--;
                } else {
                    i++;
                }
            } else {
                j--;
            }
        }
        return nums;
    }

    //快慢指针
    public static int[] exchange1(int[] nums) {
        int low = 0, fast = 0;
        while (fast < nums.length) {
            if ((nums[fast] & 1) == 1) {
                int temp = nums[low];
                nums[low] = nums[fast];
                nums[fast] = temp;
                low++;
            }
            fast++;
        }
        return nums;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode first = head, second = head;
        int m = k;
        while (m > 0) {
            if (first == null) {
                return null;
            }
            first = first.next;
            m--;
        }
        while (first != null) {
            second = second.next;
            first = first.next;
        }
        return second;
    }

    public ListNode getKthFromEnd1(ListNode head, int k) {
        ListNode first = head, second = head;
        int t = 0;
        while (first != null) {
            if (t >= k) {
                second = second.next;
            }
            first = first.next;
            t++;
        }
        if (t < k) {
            return null;
        }
        return second;
    }

    /**
     * 剑指 Offer 24. 反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = head;
        ListNode second = first.next;
        first.next = null;
        while (second != null) {
            ListNode temp = second.next;
            second.next = first;
            first = second;
            second = temp;
        }
        return first;
    }

    public static ListNode reverseList1(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //递归
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}



