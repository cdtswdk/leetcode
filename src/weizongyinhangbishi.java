import java.util.HashSet;
import java.util.Scanner;

/**
 * @Auther: chendongtao
 * @Date: 2021/4/6 19:42
 * @Description:
 */
public class weizongyinhangbishi {
    public static void main(String[] args) {
        one2();
    }

    public static void one() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder height = new StringBuilder();
        for (int i = 0; i < n; i++) {
            height.append(scanner.next());
        }
//        System.out.println(height);
        char[] chs = height.toString().toCharArray();
        if (chs.length == 1) {
            System.out.println(1);
            return;
        }
        int count = 0;
        boolean flag = false;
        int pre = -1, cur = -1, next = -1;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                cur = chs[i] - '0';
                next = chs[i + 1] - '0';
                if (cur >= next) {
                    count++;
                    flag = true;
                }
            } else if (i == n - 1) {
                cur = chs[i] - '0';
                pre = chs[i - 1] - '0';
                if (cur >= pre) {
                    count++;
                    flag = true;
                }
            } else {
                cur = chs[i] - '0';
                pre = chs[i - 1] - '0';
                next = chs[i + 1] - '0';
                if (cur >= pre && cur >= next) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void one1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder height = new StringBuilder();
        for (int i = 0; i < n; i++) {
            height.append(scanner.next());
        }
        char[] chs = height.toString().toCharArray();
        if (chs.length == 1) {
            System.out.println(1);
            return;
        }
        int count = 0;
        boolean flag, flag1, flag2;
        int pre = -1, cur = -1, next = -1;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                cur = chs[i] - '0';
                int j = i + 1;
                flag = false;
                while (j < n) {
                    next = chs[j] - '0';
                    if (cur < next) {
                        flag = true;
                        break;
                    }
                    j++;
                }
                if (!flag) {
                    count++;
                }
            } else if (i == n - 1) {
                cur = chs[i] - '0';
                int j = i - 1;
                flag = false;
                while (j >= 0) {
                    pre = chs[j] - '0';
                    if (cur < pre) {
                        flag = true;
                        break;
                    }
                    j--;
                }
                if (!flag) {
                    count++;
                }
            } else {
                cur = chs[i] - '0';
                int j = i - 1, k = i + 1;
                flag1 = false;
                flag2 = false;
                while (j >= 0) {
                    pre = chs[j] - '0';
                    if (cur < pre) {
                        flag1 = true;
                        break;
                    }
                    j--;
                }
                while (k < n) {
                    next = chs[k] - '0';
                    if (cur < next) {
                        flag2 = true;
                        break;
                    }
                    k++;
                }
                if (!flag1 && !flag2) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void one2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        StringBuilder height = new StringBuilder();
        for (int i = 0; i < n; i++) {
            height.append(scanner.next());
        }
        char[] chs = height.toString().toCharArray();
        if (chs.length == 1) {
            System.out.println(1);
            return;
        }
        int count = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int cur = chs[i] - '0';
            max = Math.max(cur, max);
        }
        for (int i = 0; i < n; i++) {
            if (chs[i] - '0' == max) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void two() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split("\\s");
        int n = Integer.parseInt(split[0]), m = Integer.parseInt(split[1]);
        int[] buckets = new int[n + 1];
        int[][] links = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            buckets[i + 1] = scanner.nextInt();
        }
        for (int j = 0; j < m; j++) {
            String s = scanner.nextLine();
            String[] split1 = s.split("\\s");
            links[Integer.parseInt(split1[0])][Integer.parseInt(split1[1])] = 1;
            links[Integer.parseInt(split1[1])][Integer.parseInt(split1[0])] = 1;
        }

        for (int i = 0; i < n + 1; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < n + 1; j++) {
                if (links[i + 1][j + 1] == 1) {
                    set.add(i+1);
                    set.add(j+1);
                }
            }

        }
    }
}
