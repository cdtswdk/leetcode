import javafx.collections.transformation.SortedList;

import java.util.*;

/**
 * @Auther: chendongtao
 * @Date: 2021/4/22 9:54
 * @Description:
 */
public class likou_jianzhioffer_2 {
    public static void main(String[] args) {
        /*int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement(nums));*/

        /*int[] sortArray = {1, 3, 5, 4, 2};
        System.out.println(Arrays.toString(quickSort(sortArray, 0, sortArray.length - 1)));*/

        /*MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());*/

        MedianFinder1 medianFinder = new MedianFinder1();
        medianFinder.addNum(5);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());

    }

    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int res = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                res = nums[i];
            }
            if (nums[i] == res) {
                count++;
            } else {
                count--;
            }
        }
        return res;
    }

    //map
    public static int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > nums.length / 2) {
                return num;
            }
            map.put(num, count);
        }
        return -1;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }

    //堆
    public int[] getLeastNumbers1(int[] arr, int k) {
        int[] res = new int[k];
        if (k == 0) {
            return res;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    //快速排序代码模板
    public static int[] quickSort(int[] sortArray, int start, int end) {
        if (sortArray == null || sortArray.length < 2) {
            return sortArray;
        }
        int index = partition(sortArray, start, end);
        if (index > start) {
            quickSort(sortArray, start, index - 1);
        }
        if (index < end) {
            quickSort(sortArray, index + 1, end);
        }
        return sortArray;
    }

    private static int partition(int[] sortArray, int start, int end) {
        int benchmark_index = start + (int) ((end - start + 1) * Math.random());
        int index = start;
        swapArrayElement(sortArray, benchmark_index, end);
        for (int i = start; i <= end; i++) {
            if (sortArray[i] <= sortArray[end]) {
                if (i > index) {
                    swapArrayElement(sortArray, index, i);
                }
                if (i < end) {
                    index++;
                }
            }
        }
        return index;
    }

    private static void swapArrayElement(int[] sortArray, int i, int j) {
        int temp = sortArray[i];
        sortArray[i] = sortArray[j];
        sortArray[j] = temp;
    }

    //快速排序
    public int[] getLeastNumbers2(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1, k);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private void quickSort(int[] arr, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        int index = randomizedPartition(arr, start, end);
        int num = index - start + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            quickSort(arr, start, index - 1, k);
        } else {
            quickSort(arr, index + 1, end, k - num);
        }
    }

    private int randomizedPartition(int[] arr, int start, int end) {
        int benchmark_index = start + (int) ((end - start + 1) * Math.random());
        swapArrayElement(arr, benchmark_index, end);
        int index = start - 1;

        for (int i = start; i <= end - 1; i++) {
            if (arr[i] <= arr[end]) {
                index = index + 1;
                swapArrayElement(arr, index, i);
            }
        }
        swapArrayElement(arr, index + 1, end);
        return index + 1;
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                res += high * digit;
            } else if (cur == 1) {
                res += high * digit + low + 1;
            } else {
                res += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串 动态规划 + 哈希表
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if ("".equals(s) || s == null) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int res = 0, tmp = 0;
        for (int j = 0; j < s.length(); j++) {
            Integer i = map.getOrDefault(s.charAt(j), -1);
            map.put(s.charAt(j), j);
            tmp = j - i > tmp ? tmp + 1 : j - i;
            res = Math.max(res, tmp);
        }
        return res;
    }

    // 动态规划 + 线性遍历
    public static int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0, tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = i - 1;
            while (j >= 0 && s.charAt(j) != s.charAt(i)) {
                j--;
            }
            map.put(s.charAt(i), i);
            tmp = tmp < j - i ? tmp + 1 : j - i;
            res = Math.max(res, tmp);
        }
        return res;
    }

    // 双指针 + 哈希表
    public static int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int i = -1, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }
            map.put(s.charAt(j), j);
            res = Math.max(res, j - i);
        }
        return res;
    }
}

/**
 * 剑指 Offer 41. 数据流中的中位数
 */
class MedianFinder {

    private List<Integer> list;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
    }

    public double findMedian() {
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        double median;
        if (list.size() % 2 == 0) {
            median = ((double) list.get(list.size() / 2) + (double) list.get(list.size() / 2 - 1)) / 2;
        } else {
            median = list.get(list.size() / 2);
        }
        return median;
    }
}

//小顶堆存储较大的一半，大顶堆存储较小的一半
class MedianFinder1 {

    private Queue<Integer> queue1, queue2;

    /**
     * initialize your data structure here.
     */
    public MedianFinder1() {
        queue1 = new PriorityQueue<>();
        queue2 = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        if (queue1.size() != queue2.size()) {
            queue1.offer(num);
            queue2.offer(queue1.poll());
        } else {
            queue2.offer(num);
            queue1.offer(queue2.poll());
        }
    }

    public double findMedian() {
        return queue1.size() == queue2.size() ? (queue1.peek() + queue2.peek()) / 2.0 : queue1.peek();
    }
}

