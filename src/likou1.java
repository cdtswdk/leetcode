import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class likou1 {
    public static void main(String[] args) {
//        int[] num1 = new int[]{1, 2, 3, 0, 0, 0}, num2 = new int[]{2, 5, 6};
//        merge(num1, 3, num2, 3);
//        for (int i : num1) {
//            System.out.println(i);
//        }

//        System.out.println(longestPalindrome("ac"));
//        System.out.println(convert("PAYPALISHIRING", 3));

//        System.out.println(myAtoi("3.14159"));
//        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
//        int[] height = new int[]{1,1};
//        int[] height = new int[]{4,3,2,1,4};
//        int[] height = new int[]{1, 2, 1};
//        System.out.println(maxArea(height));

//        System.out.println(intToRoman(1994));

        int[] nums = new int[]{0};
        System.out.println(threeSum(nums));
    }

    /**
     * 88. 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] num1_copy = new int[m];
        System.arraycopy(nums1, 0, num1_copy, 0, m);

        int p1 = 0, p2 = 0;
        int p = 0;
        while (p1 < m && p2 < n) {
            nums1[p++] = num1_copy[p1] < nums2[p2] ? num1_copy[p1++] : nums2[p2++];
        }
        if (p1 < m) {
            System.arraycopy(num1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }

    }

    /**
     * 100. 相同的树
     *
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }

        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    /**
     * 5. 最长回文子串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1, begin = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(chars, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private static boolean validPalindromic(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 6. Z 字形变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        int n = 2 * numRows - 2;
        for (int i = 0; i < s.length(); i++) {
            int x = i % n;
            rows[Math.min(x, n - x)].append(s.charAt(i));
        }
        return String.join("", rows);
    }

    /**
     * 8. 字符串转换整数 (atoi)
     *
     * @param s
     * @return
     */
    public static int myAtoi(String s) {

        s = s.trim();

        String[] split = s.split("\\s+");
        s = split[0];

        int len = s.length();
        StringBuilder result = new StringBuilder();
        boolean isPosNum = true;
        char[] chars = s.toCharArray();


        for (int i = 0; i < len; i++) {

            if (chars.length == 1) {
                if (chars[0] == '+' || chars[0] == '-') {
                    break;
                }
            }

            if (i == 0 && chars[i] == '-' && chars.length > 1) {
                if (!(chars[i + 1] >= '1' && chars[i + 1] <= '9' || chars[i + 1] == '0')) {
                    break;
                }
                result.append(chars[i]);
                continue;
            }
            if (i == 0 && chars[i] == '+' && chars.length > 1) {
                if (!(chars[i + 1] >= '1' && chars[i + 1] <= '9' || chars[i + 1] == '0')) {
                    break;
                }
                result.append(chars[i]);
                continue;
            }
            if (chars[i] >= 'a' && chars[i] <= 'z' || chars[i] >= 'A' && chars[i] <= 'Z' || chars[i] == '-' || chars[i] == '+') {
                break;
            } else if (chars[i] >= '1' && chars[i] <= '9' || chars[i] == '0' || chars[i] == '.') {
                result.append(chars[i]);
            }
        }
        if (result.length() == 0) {
            result.append(0);
        }
        double parseLong = Double.parseDouble(result.toString());
        if (parseLong < -Math.pow(2, 31)) {
            parseLong = -Math.pow(2, 31);
        } else if (parseLong > Math.pow(2, 31) - 1) {
            parseLong = Math.pow(2, 31);
        }

        return (int) parseLong;
    }

    /**
     * 11. 盛最多水的容器
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int i = 0, j = height.length - 1;
        int vo = 0;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            vo = Math.max(vo, (j - i) * h);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return vo;
    }

    // 超时
    public static int maxArea_1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int vo = 0, newVo = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if (height[i] < height[j]) {
                    newVo = (j - i) * height[i];
                } else {
                    newVo = (j - i) * height[j];
                }
                vo = vo > newVo ? vo : newVo;
            }
        }
        return vo;
    }

    /**
     * 12. 整数转罗马数字
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {

        StringBuilder result = new StringBuilder();
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }

        return result.toString();
    }

    /**
     * 14. 最长公共前缀 横向比较
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    // 纵向比较
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = getLongestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    private String getLongestCommonPrefix(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < len && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    // 分治
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return getLongestCommonPrefix2(strs, 0, strs.length - 1);
        }
    }

    private String getLongestCommonPrefix2(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        }
        int mid = (end - start) / 2 + start;
        String lcpLeft = getLongestCommonPrefix2(strs, start, mid);
        String lcpRight = getLongestCommonPrefix2(strs, mid + 1, end);
        return CommonPrefix(lcpLeft, lcpRight);
    }

    private String CommonPrefix(String lcpLeft, String lcpRight) {
        int minLen = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLen; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLen);
    }

    // 二分查找
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        int low = 0, high = minLen;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    private boolean isCommonPrefix(String[] strs, int mid) {
        String str0 = strs[0].substring(0, mid);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            for (int j = 0; j < mid; j++) {
                if (str0.charAt(j) != strs[i].charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        //枚举i
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int k = n - 1;
            int target = -nums[i];
            //枚举j
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                    result.add(temp);
                }

            }
        }

        return result;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode dump = new ListNode(0, head);
        ListNode first = head, second = head;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dump.next;
    }

    /**
     * 633. 平方数之和
     *
     * @param c
     * @return
     */
    public static boolean judgeSquareSum(int c) {
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            if (left * left + right * right == c) {
                return true;
            } else if (left * left + right * right > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
}


