

public class likou1 {
    public static void main(String[] args) {
//        int[] num1 = new int[]{1, 2, 3, 0, 0, 0}, num2 = new int[]{2, 5, 6};
//        merge(num1, 3, num2, 3);
//        for (int i : num1) {
//            System.out.println(i);
//        }

//        System.out.println(longestPalindrome("ac"));
//        System.out.println(convert("PAYPALISHIRING", 3));

        System.out.println(myAtoi("3.14159"));
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
            if (i == 0 && chars[i] == '-') {
                result.append(chars[i]);
                isPosNum = false;
                continue;
            }
            if (chars[i] >= 'a' && chars[i] <= 'z' || chars[i] >= 'A' && chars[i] <= 'Z') {
                break;
            } else if (chars[i] >= '1' && chars[i] <= '9' || chars[i] == '.') {
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
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
