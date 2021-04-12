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

    /*public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }*/
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

    /*public static int myAtoi(String s) {

        s = s.trim();

        int len = s.length();
        StringBuilder result = new StringBuilder();
        boolean isPosNum = true;
        char[] chars = s.toCharArray();

        for (int i = 0; i < len; i++) {
            if (i == 0 && chars[i] == '-') {
                isPosNum = false;
                continue;
            }
            if (chars[i] >= 'a' && chars[i] <= 'z' || chars[i] >= 'A' && chars[i] <= 'Z') {
                break;
            }
        }
        if(result.length() == 0){
            result.append(0);
        }
        long parseLong = Long.parseLong(result.toString());


        return 0;
    }*/

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

    /*public static int maxArea(int[] height) {
        if(height == null || height.length == 0){
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
    }*/
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

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> tmp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                        boolean flag = true;
                        for (List<Integer> list : res) {
                            List<Integer> one = new ArrayList<>(list), two = new ArrayList<>(tmp);
                            Collections.sort(one);
                            Collections.sort(two);
                            if (one.equals(two)) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            res.add(tmp);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {


        return null;
    }
}


