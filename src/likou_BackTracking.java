import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: chendongtao
 * @Date: 2021/8/17 12:31
 * @Description:
 */
public class likou_BackTracking {
    public static void main(String[] args) {
        /*List<String> list = restoreIpAddresses("25525522135");
        for (String s : list) {
            System.out.println(s);
        }*/
        /*int[] nums = {1, 2, 3};
        List<List<Integer>> permute = permute(nums);
        for (List<Integer> list : permute) {
            for (Integer i : list) {
                System.out.printf(i + "");
            }
            System.out.println("");
        }*/
//        combine(4, 2);
//        int[] nums = {1, 2, 3};
//        subsets(nums);
        int[] nums = {4, 4, 4, 1, 4};
        subsetsWithDup(nums);
    }

    /**
     * 46. 全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        boolean[] used = new boolean[nums.length];
        int depth = 0;
        List<Integer> path = new ArrayList<>();
        dfs_permute(nums, len, depth, used, result, path);
        return result;
    }

    private static void dfs_permute(int[] nums, int len, int depth, boolean[] used, List<List<Integer>> result, List<Integer> path) {
        if (depth == len) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs_permute(nums, len, depth + 1, used, result, path);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * 47. 全排列 II
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        int depth = 0;
        dfs_permuteUnique(nums, len, depth, used, result, path);
        return result;
    }

    private static void dfs_permuteUnique(int[] nums, int len, int depth, boolean[] used, List<List<Integer>> result, List<Integer> path) {
        if (depth == len) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;
                dfs_permuteUnique(nums, len, depth + 1, used, result, path);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs_combine(n, k, 0, 1, result, path);
        return result;
    }

    private static void dfs_combine(int n, int k, int depth, int begin, List<List<Integer>> result, List<Integer> path) {
        if (depth == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {

            path.add(i);
            dfs_combine(n, k, depth + 1, i + 1, result, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 78. 子集 回溯
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs_subsets(nums, 0, path, result);
        return result;
    }

    private static void dfs_subsets(int[] nums, int begin, List<Integer> path, List<List<Integer>> result) {

        result.add(new ArrayList<>(path));
        if (begin == nums.length) {
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            dfs_subsets(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    // 迭代
    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); mask++) {
            temp.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    temp.add(nums[i]);
                }
            }
            result.add(new ArrayList<>(temp));
        }
        return result;
    }

    // 递归
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs_subsets_1(nums, 0, temp, result);
        return result;
    }

    private static void dfs_subsets_1(int[] nums, int cur, List<Integer> temp, List<List<Integer>> result) {
        if (cur == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[cur]);
        dfs_subsets_1(nums, cur + 1, temp, result);
        temp.remove(temp.size() - 1);
        dfs_subsets_1(nums, cur + 1, temp, result);

    }

    /**
     * 90. 子集 II 不能包含重复的子集
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs_subsetsWithDup(nums, 0, used, path, result);
        return result;
    }

    private static void dfs_subsetsWithDup(int[] nums, int begin, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        if (begin == nums.length) {
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs_subsetsWithDup(nums, i + 1, used, path, result);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    /**
     * 93. 复原 IP 地址
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        if (len > 12 || len < 4) {
            return result;
        }
        List<String> path = new ArrayList<>();
        int spiltTime = 0;
        dfs_ip(s, len, spiltTime, 0, path, result);
        return result;
    }

    // 深度搜索
    private static void dfs_ip(String s, int len, int spiltTime, int begin, List<String> path, List<String> result) {
        if (begin == len) {
            if (spiltTime == 4) {
                result.add(String.join(".", path));
            }
            return;
        }
        // 剪枝
        if (len - begin < (4 - spiltTime) || (len - begin) > 3 * (4 - spiltTime)) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (begin + i >= len) {
                break;
            }
            int ipSegment = judgeIfIpSegment(s, begin, begin + i);
            if (ipSegment != -1) {
                path.add(ipSegment + "");
                dfs_ip(s, len, spiltTime + 1, begin + i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private static int judgeIfIpSegment(String s, int begin, int end) {
        int len = end - begin + 1;
        if (len > 1 && s.charAt(begin) == '0') {
            return -1;
        }
        // 转换成数值
        int res = 0;
        for (int i = begin; i <= end; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }
        if (res > 255) {
            return -1;
        }

        return res;
    }

}
