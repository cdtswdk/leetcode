/**
 * @Auther: chendongtao
 * @Date: 2021/4/12 10:41
 * @Description:
 */
public class likou2 {
    public static void main(String[] args) {
        /*int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(search2(nums, 9));*/

        System.out.println(judgeSquareSum(2));
    }

    /**
     * 704. 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    //mid在左区间
    public static int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
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

    //mid在右区间
    public static int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
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
