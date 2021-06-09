import java.util.*;

/**
 * @Auther: chendongtao
 * @Date: 2021/4/7 8:28
 * @Description:
 */
public class likou_array_1 {
    public static void main(String[] args) {
        /*int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> result = combinationSum2(candidates, target);
        System.out.println(result);*/

        /*int[] nums = {3, 4, -1, 1};
        System.out.println(firstMissingPositive2(nums));*/

        /*int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap3(nums));*/

//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(matrix);
        for (int[] mat : matrix) {
            for (int m : mat) {
                System.out.print(m);
            }
            System.out.println();
        }
    }

    /**
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len == 0) {
            return result;
        }
        //排序进行剪枝
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs1(candidates, 0, len, target, path, result);
        /*List<Integer> combine = new ArrayList<>();
        dfs2(candidates,target,result,combine,0);*/
        return result;
    }

    /**
     * 回溯算法
     *
     * @param candidates
     * @param begin
     * @param len
     * @param target
     * @param path
     * @param result
     */
    private static void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            path.add(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));
            dfs(candidates, begin, len, target - candidates[i], path, result);
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 回溯+剪枝算法
     *
     * @param candidates
     * @param begin
     * @param len
     * @param target
     * @param path
     * @param result
     */
    private static void dfs1(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));
            dfs1(candidates, i, len, target - candidates[i], path, result);
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }

    /**
     * 官方题解
     *
     * @param candidates
     * @param target
     * @param result
     * @param combine
     * @param idx
     */
    private static void dfs2(int[] candidates, int target, List<List<Integer>> result, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            result.add(combine);
            return;
        }
        //直接跳过当前选择下一个
        dfs2(candidates, target, result, combine, idx + 1);
        //选择当前的数字
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            System.out.println("递归之前 => " + combine + "，剩余 = " + (target - candidates[idx]));
            dfs2(candidates, target - candidates[idx], result, combine, idx);
            System.out.println("递归之后 => " + combine);
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 40. 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs3(candidates, target, result, path, 0);
        return result;
    }

    private static void dfs3(int[] candidates, int target, List<List<Integer>> result, Deque<Integer> path, int idx) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            //大剪枝
            if (target < candidates[i]) {
                break;
            }
            //小剪枝
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            dfs3(candidates, target - candidates[i], result, path, i + 1);
            path.removeLast();
        }
    }

    /**
     * 41. 缺失的第一个正数
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int res = 1;
        if (nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == res) {
                res++;
            }
        }
        return res;
    }

    public static int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static int firstMissingPositive3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int left_max = 0, right_max = 0;
            for (int j = i - 1; j >= 0; j--) {
                left_max = Math.max(left_max, height[j]);
            }
            for (int j = i + 1; j < size; j++) {
                right_max = Math.max(right_max, height[j]);
            }
            int min = Math.min(left_max, right_max);
            if (min > height[i]) {
                ans += min - height[i];
            }
        }
        return ans;
    }

    //动态规划
    public static int trap2(int[] height) {
        int ans = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];
        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 2; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                ans += min - height[i];
            }
        }
        return ans;
    }

    //双指针
    public static int trap3(int[] height) {
        int ans = 0, left = 1, right = height.length - 2;
        int max_left = 0, max_right = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    ans += min - height[left];
                }
                left++;
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    ans += min - height[right];
                }
                right--;
            }
        }
        return ans;
    }

    //栈
    public static int trap4(int[] height) {
        int ans = 0;
        int current = 0;
        Stack<Integer> stack = new Stack<>();
        while (current < height.length) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                Integer pop = stack.pop();
                int h = height[pop];
                if (stack.isEmpty()) {
                    break;
                }
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                ans += distance * (min - h);
            }
            stack.push(current);
            current++;
        }
        return ans;
    }

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    //贪心算法-正向遍历
    public int jump1(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    /**
     * 48. 旋转图像
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_new[j][n - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix_new[i], 0, matrix[i], 0, n);
        }
    }

    //原地旋转
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n / 2); i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    //用翻转代替旋转
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
