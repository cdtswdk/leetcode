import java.rmi.MarshalException;
import java.util.*;

/**
 * @Auther: chendongtao
 * @Date: 2021/4/28 20:15
 * @Description:
 */
public class likou_jianzhioffer_3 {
    private static int[] nums, tmp;
    private static int value, count;
    private static int depth;

    public static void main(String[] args) {
        /*String str = "aabbc";
        System.out.println(firstUniqChar(str));*/
        /*int[] nums = {7, 3, 2, 6, 0, 1, 5, 4};
        System.out.println(reversePairs(nums));*/

        /*ListNode nodeA = new ListNode(4);
        ListNode nodeA_1 = new ListNode(1);
        nodeA.next = nodeA_1;
        ListNode nodeB = new ListNode(5);
        ListNode nodeB_1 = new ListNode(0);
        ListNode nodeB_2 = new ListNode(1);
        nodeB.next = nodeB_1;
        nodeB_1.next = nodeB_2;

        ListNode node1 = new ListNode(8);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        nodeA_1.next = node1;
        nodeB_2.next = node1;
        node1.next = node2;
        node2.next = node3;

        System.out.println(getIntersectionNode_1(nodeA, nodeB).val);*/
        /*ListNode nodeA = new ListNode(0);
        ListNode nodeA_1 = new ListNode(9);
        ListNode nodeA_2 = new ListNode(1);
        nodeA.next = nodeA_1;
        nodeA_1.next = nodeA_2;
        ListNode nodeB = new ListNode(3);

        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        nodeA_2.next = node1;
        nodeB.next = node1;
        node1.next = node2;

        System.out.println(getIntersectionNode_1(nodeA, nodeB).val);*/
        /*ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(1);
        System.out.println(getIntersectionNode_1(nodeA, nodeB).val);*/
        /*int[] nums = {5, 6, 8, 8, 8, 10};
        System.out.println(search1(nums, 8));*/

        /*int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 9};
        System.out.println(missingNumber1(nums));*/

        /*TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node4.left = node6;
        node2.right = node5;
        System.out.println(kthLargest(node1, 3));*/

//        System.out.println(Arrays.deepToString(findContinuousSequence(15)));

//        System.out.println(reverseWords("  ab   bc cd"));
//        System.out.println(reverseLeftWords("abcdefg", 3));

        /*int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));*/

        MaxQueue maxQueue = new MaxQueue();
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.pop_front());
        maxQueue.push_back(94);
        maxQueue.push_back(16);
        maxQueue.push_back(89);
        System.out.println(maxQueue.pop_front());
        maxQueue.push_back(22);
        System.out.println(maxQueue.pop_front());
    }

    /**
     * 剑指 Offer 49. 丑数
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(n2, Math.min(n3, n5));
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;
        }
        return dp[n - 1];
    }

    //最小堆
    public int nthUglyNumber1(int n) {
        int[] factors = {2, 3, 5};
        HashSet<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.offer(1L);
        int res = 0;
        for (int i = 0; i < n; i++) {
            long poll = queue.poll();
            res = (int) poll;
            for (int factor : factors) {
                long nextUgly = factor * poll;
                if (set.add(nextUgly)) {
                    queue.offer(nextUgly);
                }
            }
        }
        return res;
    }

    public static char firstUniqChar(String s) {
        if (s.length() == 0) {
            return ' ';
        }
        int[] target = new int[26];
        for (int i = 0; i < s.length(); i++) {
            target[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (target[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    //哈希表
    public static char firstUniqChar1(String s) {
        HashMap<Character, Boolean> map = new HashMap<>();
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            map.put(ch, !map.containsKey(ch));
        }
        for (char ch : chs) {
            if (map.get(ch)) {
                return ch;
            }
        }
        return ' ';
    }

    //有序哈希表
    public static char firstUniqChar2(String s) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            map.put(ch, !map.containsKey(ch));
        }
        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    /**
     * 剑指 Offer 51. 数组中的逆序对
     *
     * @param num
     * @return
     */
    public static int reversePairs(int[] num) {
        if (num.length < 2) {
            return 0;
        }
        nums = num;
        tmp = new int[num.length];
        return mergeSort(0, num.length - 1);
    }

    //归并排序
    private static int mergeSort(int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = (l + r) / 2;
        int res = mergeSort(l, mid) + mergeSort(mid + 1, r);
        for (int k = l; k <= r; k++) {
            tmp[k] = nums[k];
        }
        //合并阶段
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) {
                nums[k] = tmp[j++];
            } else if (j == r + 1 || tmp[i] <= tmp[j]) {
                nums[k] = tmp[i++];
            } else {
                nums[k] = tmp[j++];
                res += mid - i + 1;
            }
        }
        return res;
    }

    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    public static ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int countA = 0, countB = 0;
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != null) {
            countA++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            countB++;
            nodeB = nodeB.next;
        }
        ListNode tmpA = headA, tmpB = headB;
        if (countA == countB) {
            while (tmpA != null && tmpB != null) {
                if (tmpA.val != tmpB.val) {
                    tmpA = tmpA.next;
                    tmpB = tmpB.next;
                } else {
                    return tmpA;
                }
            }
        } else {
            int div = Math.abs(countB - countA);
            boolean flag = countA < countB;
            while (tmpA != null && tmpB != null) {
                if (tmpA != tmpB) {
                    if (flag) {
                        if (div > 0) {
                            tmpB = tmpB.next;
                            div--;
                        } else {
                            tmpA = tmpA.next;
                            tmpB = tmpB.next;
                        }
                    } else {
                        if (div > 0) {
                            tmpA = tmpA.next;
                            div--;
                        } else {
                            tmpA = tmpA.next;
                            tmpB = tmpB.next;
                        }
                    }
                } else {
                    return tmpA;
                }
            }
        }
        return null;
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        //搜索右边界right
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] <= target) i = mid + 1;
            else
                j = mid - 1;
        }
        int right = i;
        //若数组中无target，则提前返回
        if (j >= 0 && nums[j] != target) {
            return 0;
        }
        //搜索左边界left
        i = 0;
        j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        int left = j;
        return right - left - 1;
    }

    public static int search1(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    private static int helper(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= target) i = m + 1;
            else j = m - 1;
        }
        return i;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                res = i;
                break;
            }
        }
        return res == -1 ? nums.length : res;
    }

    //二分法
    public static int missingNumber1(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == m) i = m + 1;
            else {
                j = m - 1;
            }
        }
        return i;
    }

    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     *
     * @param root
     * @param k
     * @return
     */
    public static int kthLargest(TreeNode root, int k) {
        findKthLargest(root, k);
        return value;
    }

    public static void findKthLargest(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        findKthLargest(root.right, k);
        if (count == k) {
            return;
        }
        if (++count == k) {
            value = root.val;
        }
        findKthLargest(root.left, k);
    }

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //层序遍历BFS
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>() {{
            add(root);
        }};
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(getTreeDepth(root.left) - getTreeDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }


    private int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;
    }

    //先序遍历+剪枝
    public boolean isBalanced1(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = recur(root.left);
        if (left == -1) {
            return -1;
        }
        int right = recur(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     *
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        if (nums.length == 2) {
            return nums;
        }
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, ++count);
        }
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                res[index++] = entry.getKey();
            }
        }
        return res;
    }

    //异或
    public int[] singleNumbers1(int[] nums) {
        int x = 0, y = 0, n = 0, m = 1;
        for (int num : nums) {
            n ^= num;
        }
        while ((n & m) == 0) {
            m <<= 1;
        }
        for (int num : nums) {
            if ((num & m) == 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            map.put(num, ++count);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    //遍历统计
    public int singleNumber1(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>= 1;
            }
        }
        int res = 0, m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= count[31 - i] % 3;
        }
        return res;
    }

    //有限状态自动机
    public int singleNumber2(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    /**
     * 剑指 Offer 57. 和为s的两个数字
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return new int[]{};
    }

    //双指针
    public int[] twoSum1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{nums[left], nums[right]};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{};
    }

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        for (int l = 1, r = 1, sum = 0; r < target; r++) {
            sum += r;
            while (sum > target) {
                sum -= l++;
            }
            if (sum == target) {
                int[] temp = new int[r - l + 1];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = l + i;
                }
                list.add(temp);
            }
        }
        return list.toArray(new int[0][]);
    }

    //使用公式
    public static int[][] findContinuousSequence1(int target) {
        int i = 1;
        double j = 2.0;
        List<int[]> list = new ArrayList<>();
        while (i < j) {
            j = (-1 + Math.sqrt(1 + 4 * (2 * target + (long) i * i - i))) / 2;
            if (i < j && j == (int) j) {
                int[] temp = new int[(int) j - i + 1];
                for (int k = i; k <= (int) j; k++) {
                    temp[k - i] = k;
                }
                list.add(temp);
            }
            i++;
        }
        return list.toArray(new int[0][]);
    }

    //枚举+暴力
    public static int[][] findContinuousSequence2(int target) {
        List<int[]> list = new ArrayList<>();
        int limit = (target - 1) / 2;
        int sum = 0;
        for (int i = 1; i <= limit; i++) {
            for (int j = i; ; j++) {
                sum += j;
                if (sum > target) {
                    sum = 0;
                    break;
                } else if (sum == target) {
                    int[] temp = new int[j - i + 1];
                    for (int k = i; k <= j; k++) {
                        temp[k - i] = k;
                    }
                    list.add(temp);
                    sum = 0;
                    break;
                }
            }
        }
        return list.toArray(new int[0][]);
    }

    //枚举+数学优化
    public static int[][] findContinuousSequence3(int target) {
        List<int[]> list = new ArrayList<>();
        int limit = (target - 1) / 2;
        for (int x = 1; x <= limit; x++) {
            long delta = 1 - 4 * (x - (long) x * x - 2 * target);
            if (delta < 0) {
                continue;
            }
            int delta_sqrt = (int) Math.sqrt(delta + 0.5);
            if ((long) delta_sqrt * delta_sqrt == delta && (delta_sqrt - 1) % 2 == 0) {
                int y = (-1 + delta_sqrt) / 2;
                if (x < y) {
                    int[] temp = new int[y - x + 1];
                    for (int i = x; i <= y; i++) {
                        temp[i - x] = i;
                    }
                    list.add(temp);
                }
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    //双指针
    public static int[][] findContinuousSequence4(int target) {
        List<int[]> list = new ArrayList<>();
        for (int l = 1, r = 2; l < r; ) {
            int sum = (l + r) * (r - l + 1) / 2;
            if (sum == target) {
                int[] temp = new int[r - l + 1];
                for (int i = l; i <= r; i++) {
                    temp[i - l] = i;
                }
                list.add(temp);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return list.toArray(new int[0][]);
    }

    //剑指 Offer 58 - I. 翻转单词顺序
    public static String reverseWords(String s) {
        String[] split = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(split));
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            res.append(split[i]).append(" ");
        }
        return res.toString().trim();
    }

    //双指针
    public static String reverseWords1(String s) {
        s = s.trim();
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            //找到首个空格
            while (i >= 0 && s.charAt(i) != ' ') i--;
            //拼接单词
            res.append(s.substring(i + 1, j + 1)).append(" ");
            //跳过单词间空格
            while (i >= 0 && s.charAt(i) == ' ') i--;
            //j指向下一个单词的尾字符
            j = i;
        }
        return res.toString().trim();
    }

    //剑指 Offer 58 - II. 左旋转字符串
    public static String reverseLeftWords(String s, int n) {
        if (n >= s.length()) {
            return s;
        }
        return s.substring(n) + s.substring(0, n);
    }

    //列表遍历拼接
    public static String reverseLeftWords1(String s, int n) {
        StringBuilder res = new StringBuilder();
        for (int i = n; i < n + s.length(); i++) {
            res.append(s.charAt(i % s.length()));
        }
        return res.toString();
    }

    //字符串遍历拼接
    public static String reverseLeftWords2(String s, int n) {
        String res = "";
        for (int i = n; i < n + s.length(); i++) {
            res += s.charAt(i % s.length());
        }
        return res;
    }

    //剑指 Offer 59 - I. 滑动窗口的最大值
    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        int maxValue = Integer.MIN_VALUE;
        Queue<Integer> queue = new LinkedList<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            if (r - l < k) {
                queue.offer(nums[r]);
            } else if (r - l == k) {
                Iterator<Integer> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    maxValue = Math.max(maxValue, iterator.next());
                }
                res.add(maxValue);
                maxValue = Integer.MIN_VALUE;
                queue.poll();
                queue.offer(nums[r]);
                l++;
            }
        }
        //加入最后一个窗口的最大值
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            maxValue = Math.max(maxValue, iterator.next());
        }
        if (maxValue != Integer.MIN_VALUE) {
            res.add(maxValue);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    //双端队列
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            if (i > 0 && deque.peekFirst() == nums[i - 1]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
            if (i >= 0) {
                res[i] = deque.peekFirst();
            }
        }
        return res;
    }

    //双端队列 未形成窗口 -> 已形成窗口
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        //未形成窗口
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        //已形成窗口
        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }

    /**
     * 剑指 Offer 60. n个骰子的点数
     *
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] * (1.0 / 6.0);
                }
            }
            dp = temp;
        }
        return dp;
    }

    public double[] dicesProbability1(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= 6 * i; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (j - k <= 0) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double[] ans = new double[5 * n + 1];
        double all = Math.pow(6, n);

        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = (dp[n][i] * 1.0) / all;
        }
        return ans;
    }

    //优化
    public double[] dicesProbability2(int n) {
        int[] dp = new int[6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 6 * i; j >= i; j--) {
                dp[j] = 0;
                for (int k = 1; k <= 6; k++) {
                    if (j - k < i - 1) {
                        break;
                    }
                    dp[j] += dp[j - k];
                }
            }
        }
        double all = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = dp[i] * 1.0 / all;
        }
        return ans;
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {
                index++;
            }
            if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[4] - nums[index] < 5;
    }

    //set+遍历
    public boolean isStraight1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
        }
        return max - min < 5;
    }

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }

    //递归
    public int lastRemaining1(int n, int m) {
        return f(n, m);
    }

    private int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    //迭代
    public int lastRemaining2(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; i++) {
            f = (m + f) % i;
        }
        return f;
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int res = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= min) {
                min = prices[i];
            } else {
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }

    public int maxProfit1(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

    /**
     * 剑指 Offer 64. 求1+2+…+n
     *
     * @param n
     * @return
     */
    public int sumNums(int n) {
        return recur(n);
    }

    public int recur(int n) {
        if (n == 1) {
            return 1;
        }
        return n + recur(n - 1);
    }

    //短路效应
    public int sumNums1(int n) {
        boolean x = n > 1 && (n += sumNums1(n - 1)) > 0;
        return n;
    }

    /**
     * 剑指 Offer 65. 不用加减乘除做加法
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     *
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        int[] res = new int[a.length];

        for (int i = 0, cur = 1; i < a.length; i++) {
            res[i] = cur;
            cur *= a[i];
        }
        for (int i = a.length - 1, cur = 1; i >= 0; i--) {
            res[i] *= cur;
            cur *= a[i];
        }
        return res;
    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     *
     * @param a
     * @return
     */
    public int[] constructArr1(int[] a) {
        if (a.length == 0) {
            return new int[0];
        }
        int[] res = new int[a.length];
        res[0] = 1;
        for (int i = 1; i < a.length; i++) {
            res[i] = res[i - 1] * a[i - 1];
        }
        for (int i = a.length - 2, tmp = 1; i >= 0; i--) {
            tmp *= a[i + 1];
            res[i] *= tmp;
        }
        return res;
    }

    /**
     * 剑指 Offer 67. 把字符串转换成整数
     *
     * @param str
     * @return
     */
    public int strToInt(String str) {
        return 0;
    }
}

/**
 * 剑指 Offer 59 - II. 队列的最大值
 */
class MaxQueue {

    private Queue<Integer> queueA;
    private Deque<Integer> deque;

    public MaxQueue() {
        queueA = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        if (!deque.isEmpty()) {
            return deque.peek();
        }
        return -1;
    }

    public void push_back(int value) {
        queueA.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.addLast(value);
    }

    public int pop_front() {
        int popValue = -1;
        if (!queueA.isEmpty()) {
            popValue = queueA.poll();
            if (!deque.isEmpty() && popValue == deque.peekFirst()) {
                deque.pollFirst();
            }
        }
        return popValue;
    }
}
