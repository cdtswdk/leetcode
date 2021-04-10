/**
 * @Auther: chendongtao
 * @Date: 2021/4/10 9:32
 * @Description:
 */
public class likou_everyday_one {
    public static void main(String[] args) {
        System.out.println(isUgly(14));
    }

    /**
     * 263. 丑数
     *
     * @param n
     * @return
     */
    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n = n / factor;
            }
        }
        return n == 1;
    }
}
