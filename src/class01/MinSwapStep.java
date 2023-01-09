package src.class01;

/**
 * 给定一个只包含G和B的字符串，让所有的G在左侧和让所有的B在左侧分别需要移动多少步，只能在相邻字符之间进行移动。
 */
public class MinSwapStep {

    public static int minSwapStep(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int step1 = 0, step2 = 0;
        //gi和bi分别表示第几个G和B
        int gi = 0, bi = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'G') {
                step1 += i - (gi++);
            } else {
                step2 += i - (bi++);
            }
        }
        return Math.max(step1, step2);
    }

    public static int minSwapStep1(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int step1 = 0;
        int gi = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'G') {
                step1 += i - (gi++);
            }
        }
        int step2 = 0;
        int bi = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'B') {
                step2 += i - (bi++);
            }
        }
        return Math.max(step1, step2);
    }

    public static String generateGB(int maxlen) {
        char[] str = new char[(int) (Math.random() * maxlen)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int len = 100;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            String str = generateGB(len);
            int step = minSwapStep(str);
            int step1 = minSwapStep1(str);
            if (step != step1) {
                System.out.println("error");
            }
        }
        System.out.println("finished");
    }
}
