import java.util.*;

/**
 * @Auther: chendongtao
 * @Date: 2021/4/28 20:15
 * @Description:
 */
public class likou_jianzhioffer_3 {
    private static int[] nums, tmp;

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

        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 9};
        System.out.println(missingNumber1(nums));
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
    public int kthLargest(TreeNode root, int k) {
        return -1;
    }
}
