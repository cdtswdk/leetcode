import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: chendongtao
 * @Date: 2021/5/24 21:37
 * @Description:
 */
public class likou_lcci_1 {
    public static void main(String[] args) {
        System.out.println(replaceSpaces1("nwmog q k  gW  c    H  DYpIE    Lcz         gV    Bj   vkH X g       l                                                                                        ", 72));
    }

    /**
     * 面试题 01.01. 判定字符是否唯一
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        if (astr.length() == 0) {
            return true;
        }
        Map<Character, Boolean> map = new HashMap<>();
        char[] chs = astr.toCharArray();
        for (char ch : chs) {
            if (map.getOrDefault(ch, false)) {
                return false;
            } else {
                map.put(ch, true);
            }
        }
        return true;
    }

    //位运算
    public boolean isUnique1(String astr) {
        if (astr.length() == 0) {
            return true;
        }
        int mark = 0;
        char[] chs = astr.toCharArray();
        for (char ch : chs) {
            int move_bit = ch - 'a';
            int move = 1 << move_bit;
            if ((mark & move) != 0) {
                return false;
            } else {
                mark |= move;
            }
        }
        return true;
    }

    /**
     * 面试题 01.02. 判定是否互为字符重排
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        for (char ch : chs1) {
            int count = map.getOrDefault(ch, 0);
            map.put(ch, ++count);
        }
        for (char ch : chs2) {
            if (map.getOrDefault(ch, 0) == 0) {
                return false;
            } else {
                int count = map.getOrDefault(ch, 0);
                map.put(ch, --count);
            }
        }
        return true;
    }

    /**
     * 面试题 01.03. URL化
     *
     * @param S
     * @param length
     * @return
     */
    public static String replaceSpaces(String S, int length) {
        if (length == 0) {
            return S;
        }
        S = S.substring(0, length);
        String tmp = S.trim();
        if (tmp.length() == 0) {
            char[] res = new char[3 * length];
            for (int i = 0, j = 0; i < length; i++) {
                res[j++] = '%';
                res[j++] = '2';
                res[j++] = '0';

            }
            return new String(res);
        }
        int count = 0;
        char[] chs1 = S.toCharArray();
        for (char c : chs1) {
            if (c == ' ') {
                count++;
            }
        }
        char[] res = new char[length + 2 * count];
        for (int i = 0, j = 0; i < length; i++) {
            char c = chs1[i];
            if (c != ' ') {
                res[j++] = c;
            } else {
                res[j++] = '%';
                res[j++] = '2';
                res[j++] = '0';
            }
        }
        return new String(res);
    }

    //双指针
    public static String replaceSpaces1(String S, int length) {
        int allLen = S.length() - 1;
        char[] chs = S.toCharArray();
        for (int i = length - 1; i >= 0; i--) {
            if (S.charAt(i) == ' ') {
                chs[allLen] = '0';
                chs[allLen - 1] = '2';
                chs[allLen - 2] = '%';
                allLen -= 3;
            } else {
                chs[allLen] = S.charAt(i);
                allLen--;
            }
        }
        S = new String(chs);
        System.out.println(S.substring(allLen + 1));
        return S.substring(allLen + 1);
    }
}
