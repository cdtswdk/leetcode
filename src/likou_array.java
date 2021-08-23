

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class likou_array {
    public static void main(String[] args) {
        /*int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(transpose(matrix)));*/
        /*int [] num1 = new int[]{1,3},num2 = new int[]{2};
        System.out.println(findMedianSortedArrays(num1, num2));*/
        /*int[] num = new int[]{-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(sortedSquares(num)));*/
        /*int[] nums = new int[]{3, 2, 3};
        System.out.println(majorityElement1(nums));*/

        /*int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        System.out.println(maxDistance(grid));*/
        /*int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(orangesRotting(grid));*/
        /*int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));*/
        /*int[] nums = {-3, -2, -5, 3, -4};
        System.out.println(threeSumClosest(nums, -1));*/
        /*int[] nums = {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum1(nums, 0));*/

        /*int[] nums = {1, 0, 1, 1, 1};
        System.out.println(searchArray(nums, 0));*/
        /*int[] nums = {2, 2};
        int[] searchRange = searchRange(nums, 2);
        for (int i : searchRange) {
            System.out.println(i);
        }*/

        /*int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert1(nums, 5));*/

        /*int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange1(nums, 8)));*/
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange2(nums, 8)));

    }

    /**
     * 转置矩阵
     *
     * @param matrix
     * @return
     */
    public static int[][] transpose(int[][] matrix) {

        if (matrix == null) {
            return null;
        }
        int m, n;
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (m = 0; m < matrix.length; m++) {
            for (n = 0; n < matrix[m].length; n++) {
                temp[n][m] = matrix[m][n];
            }
        }
        return temp;
    }

    /**
     * 寻找两个正序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[m + n];
        for (int i = 0; i < m + n; i++) {
            if (i < m) {
                result[i] = nums1[i];
            } else {
                result[i] = nums2[i - m];
            }
        }
        Arrays.sort(result);
        int length = m + n;
        if (length % 2 == 0) {
            System.out.println(result[length / 2 - 1]);
            System.out.println(result[length / 2]);
            return Double.valueOf(result[length / 2 - 1] + result[length / 2]) / 2;
        } else {
            return result[length / 2];
        }
    }

    /**
     * 有序数组的平方
     *
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    //双指针
    public static int[] sortedSquares1(int[] nums) {
        int n = nums.length;
        int i, j, pos;
        int[] result = new int[n];
        for (i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                result[pos] = nums[i] * nums[i];
                i++;
            } else {
                result[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }
        return result;
    }

    /**
     * 主要元素
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {

        Arrays.sort(nums);
        int count = 0, a = nums[nums.length / 2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == a) {
                count++;
            }
        }
        if (count > nums.length / 2) {
            return a;
        }
        return -1;
    }

    //摩尔投票法
    public static int majorityElement1(int[] nums) {
        int count = 0, major = -1;
        for (int num : nums) {
            if (count == 0) {
                major = num;
                count++;
            } else {
                if (num == major) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        int vote = 0;
        for (int num : nums) {
            if (num == major) {
                vote++;
            }
        }
        if (vote > nums.length / 2) {
            return major;
        } else {
            return -1;
        }
    }

    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(level);
        }
        return list;
    }

    /**
     * 找每一层的最右节点
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        List<Integer> list = new ArrayList<>();

        list.add(root.val);
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (i == queue.size() - 1) {
                    list.add(node.val);
                }
            }
        }
        return list;
    }

    /**
     * 1162.地图分析
     *
     * @param grid
     * @return
     */
    public static int maxDistance(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();

        //把陆地节点加进队列
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }
        int distance = -1;
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            distance++;
            int m = queue.size();
            for (int i = 0; i < m; i++) {
                int[] node = queue.poll();
                int r1 = node[0];
                int c1 = node[1];
                for (int[] move : moves) {
                    int r2 = r1 + move[0];
                    int c2 = c1 + move[1];
                    if (inArea(grid, r2, c2)) {
                        if (grid[r2][c2] == 0) {
                            grid[r2][c2] = 2;
                            queue.add(new int[]{r2, c2});
                        }
                    }
                }
            }
        }
        return distance;
    }

    private static boolean inArea(int[][] grid, int r2, int c2) {
        return r2 >= 0 && r2 < grid.length && c2 >= 0 && c2 < grid[0].length;
    }

    /**
     * 515.在树中找每行的最大值
     *
     * @param root
     * @return
     */
    public static List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int n = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }

    /**
     * 637.二叉树的层平均值
     *
     * @param root
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Double> result = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            double average, total = 0;

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                total += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            average = total / n;
            result.add(average);
        }
        return result;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();

        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        int flag = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> temp = new ArrayList<>();
            TreeNode[] nodes = new TreeNode[n];
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                nodes[i] = node;
            }
            if (flag % 2 == 0) {
                for (TreeNode node : nodes) {
                    temp.add(node.val);
                }
            } else {
                for (int j = nodes.length - 1; j >= 0; j--) {
                    temp.add(nodes[j].val);
                }
            }
            result.add(temp);
            flag++;
        }
        return result;
    }

    /**
     * 542. 01 矩阵
     *
     * @param matrix
     * @return
     */
    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
        //全为0 或全为1
        if (queue.isEmpty() || queue.size() == matrix.length * matrix[0].length) {
            return matrix;
        }
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int distance = 0;
        while (!queue.isEmpty()) {
            distance++;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int[] node = queue.poll();
                int r1 = node[0];
                int c1 = node[1];
                for (int[] move : moves) {
                    int r2 = r1 + move[0];
                    int c2 = c1 + move[1];
                    if (inArea(matrix, r2, c2)) {
                        if (matrix[r2][c2] == -1) {
                            matrix[r2][c2] = matrix[r1][c1] + 1;
                            queue.add(new int[]{r2, c2});
                        }
                    }
                }
            }
        }

        return matrix;
    }

    /**
     * 994. 腐烂的橘子
     *
     * @param grid
     * @return
     */
    public static int orangesRotting(int[][] grid) {
        if (grid == null) {
            return -1;
        }
        //新鲜橘子的数量
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int distance = 0;
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (count > 0 && !queue.isEmpty()) {
            distance++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                int r1 = node[0];
                int c1 = node[1];
                for (int[] move : moves) {
                    int r2 = r1 + move[0];
                    int c2 = c1 + move[1];
                    if (inArea(grid, r2, c2) && grid[r2][c2] == 1) {
                        grid[r2][c2] = 2;
                        count--;
                        queue.add(new int[]{r2, c2});
                    }
                }
            }
        }
        if (count > 0) {
            return -1;
        }
        return distance;
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
     * 16. 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 10000;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    int k0 = k - 1;
                    while (j < k0 && nums[k0] == nums[k]) {
                        k0--;
                    }
                    k = k0;
                } else {
                    int j0 = j + 1;
                    while (j0 < k && nums[j0] == nums[j]) {
                        j0++;
                    }
                    j = j0;
                }
            }
        }
        return res;
    }

    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length <= 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int s = n - 1;
                for (int k = j + 1; k < n; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    while (k < s && nums[i] + nums[j] + nums[k] + nums[s] > target) {
                        s--;
                    }
                    if (s == k) {
                        break;
                    }
                    if (nums[i] + nums[j] + nums[k] + nums[s] == target) {
                        List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[s]));
                        result.add(temp);
                    }
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> fourSum1(int[] nums, int target) {
        if (nums == null || nums.length <= 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1, s = n - 1;
                while (k < s) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[s];
                    if (sum == target) {
                        List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[s]));
                        result.add(temp);
                        while (k < s && nums[k] == nums[k + 1]) {
                            k++;
                        }
                        k++;
                        while (k < s && nums[s] == nums[s - 1]) {
                            s--;
                        }
                        s--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        s--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 31. 下一个排列
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int i = nums.length - 2, j = nums.length - 1;
        while (i < j && i >= 0) {
            if (nums[i] >= nums[j]) {
                i--;
                j--;
            } else {
                int k = nums.length - 1;
                while (k > i) {
                    if (nums[k] <= nums[i]) {
                        k--;
                    } else {
                        int temp = nums[i];
                        nums[i] = nums[k];
                        nums[k] = temp;
                        break;
                    }
                }

                Arrays.sort(nums, i + 1, nums.length);
                break;
            }
            if (i == 0 && j == 1 && nums[i] > nums[j]) {
                Arrays.sort(nums);
                break;
            }
        }
        for (int num : nums) {
            System.out.print(num);
        }
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    //二分法
    public static int search1(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 81. 搜索旋转排序数组 II
     *
     * @param nums
     * @param target
     * @return
     */
    public static boolean searchArray(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return true;
            }
            //左边区域有序
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            }
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                int ml = mid - 1;
                while (ml >= 0 && nums[ml] == nums[mid]) {
                    ml--;
                }
                int mr = mid + 1;
                while (mr < n && nums[mr] == nums[mid]) {
                    mr++;
                }
                return new int[]{ml + 1, mr - 1};
            }
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    //分成两个区间
    public static int[] searchRange1(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return new int[]{-1, -1};
        }
        int leftIndex = findFirstPosition(nums, target);
        if (leftIndex == -1) {
            return new int[]{-1, -1};
        }

        int rightIndex = findLastPosition(nums, target);

        return new int[]{leftIndex, rightIndex};
    }

    private static int findFirstPosition(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            //小于的话mid分配在左区间
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    private static int findLastPosition(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            //小于的话mid分配在左区间
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    public static int[] searchRange2(int[] nums, int target) {
        int leftIndex = searchrange(nums, target);
        int rightIndex = searchrange(nums, target + 1);

        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{leftIndex, rightIndex - 1};
    }

    private static int searchrange(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 35. 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target <= nums[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    //分成两个区间
    public static int searchInsert1(int[] nums, int target) {
        int len = nums.length;
        if (target > nums[len - 1]) {
            return len;
        }
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

