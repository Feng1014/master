package src.class03;

import java.util.HashSet;

/**
 * 在一个字符串数组中，如果两个字符串的包含的种类相同，则认为这两个字符串是同一类。
 * aabbbcccc和abc是同一类字符串
 * 返回字符串数组中有多少类
 */
public class HowManyTypes {

    public static int types(String[] strs) {
        HashSet<Integer> types = new HashSet<>();
        for (String str : strs) {
            int key = 0;
            char[] s = str.toCharArray();
            for (int i = 0; i < s.length; i++) {
                key |= 1 << (s[i] - 'a');
            }
            types.add(key);
        }
        return types.size();
    }

    public static int types1(String[] strs) {
        HashSet<String> types = new HashSet<>();
        for (String str : strs) {
            char[] s = str.toCharArray();
            boolean[] map = new boolean[26];
            for (int i = 0; i < s.length; i++) {
                map[s[i] - 'a'] = true;
            }
            String key = "";
            for (int i = 0; i < 26; i++) {
                if (map[i]) {
                    key += String.valueOf(i + 'a');
                }
            }
            types.add(key);
        }
        return types.size();
    }

    public static String[] generateStringArray(int possibilities, int strMaxSize, int arrMaxSize) {
        String[] array = new String[(int) ((Math.random() * arrMaxSize) + 1)];
        for (int i = 0; i < array.length; i++) {
            array[i] = generateRandomString(possibilities, strMaxSize);
        }
        return array;
    }

    public static String generateRandomString(int possibilities, int strMaxSize) {
        char[] ans = new char[(int) ((Math.random() * strMaxSize) + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 20;
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            String[] strs = generateStringArray(possibilities, strMaxSize, arrMaxSize);
            int types = types(strs);
            int types1 = types1(strs);
            if (types != types1) {
                System.out.println("error");
            }
        }
        System.out.println("finished");
    }
}
