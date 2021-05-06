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
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode sameNode = null;
        if (headA == null || headB == null) {
            return null;
        }
        if (headA.next == headB.next) {
            sameNode = headA.next;
            return sameNode;
        } else {
            sameNode = getIntersectionNode(headA.next, headB);
            if (sameNode == null) {
                sameNode = getIntersectionNode(headA, headB.next);
            }
        }
        return sameNode;
    }
}
