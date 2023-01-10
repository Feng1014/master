package src.class01;


import java.util.HashMap;

/**
 * 给定一个数组和target，数组中每个元素前可以加正号或负号，数组中添加完符号的所有元素都参与运算得到target，求一共有多少方法。
 * https://leetcode.cn/problems/target-sum/
 */
public class TargetSum {

    /**
     * 暴力递归
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] arr, int target) {
        return process(arr, 0, target);
    }

    public static int process(int[] arr, int index, int rest) {
        /*index等于数组的长度表示没有数组中已经没有数可以分配了，此时如果剩余需要分配的rest为0，则表示这是一种计算方法*/
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        /*index位置赋予正号，则后续参与运算的rest减去arr[index]。index位置赋予负号，则后续参与运算的rest加上arr[index]。*/
        return process(arr, index + 1, rest - arr[index]) + process(arr, index + 1, rest + arr[index]);
    }

    /**
     * 记忆化搜索，使用哈希表把每个index对应的rest，ans存储起来，如果在其它index位置遇到相同的rest，直接拿出来使用即可
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findTargetSumWays1(int[] arr, int target) {
        return process1(arr, 0, target, new HashMap<>());
    }

    public static int process1(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        int ans = 0;
        if (index == arr.length) {
            ans = rest == 0 ? 1 : 0;
        } else {
            ans = process1(arr, index + 1, rest - arr[index], dp) + process1(arr, index + 1, rest + arr[index], dp);
        }
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(rest, ans);
        return ans;
    }

    /**
     * 动态规划
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findTargetSumWays2(int[] arr, int target) {
        int sum = 0;
        for (int a : arr
        ) {
            sum += a;
        }
        return (sum < 0) || ((sum & 1) ^ (target & 1)) != 0 ? 0 : subSet(arr, (sum + target) >> 1);
    }

    /**
     * 0，1背包问题。 数组arr中，任选元素构成和为s。求有多少种构成方法
     *
     * @param arr
     * @param s
     * @return
     */
    public static int subSet(int[] arr, int s) {
        if (s < 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][s + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= s; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - arr[i - 1]];
                }
            }
        }
        return dp[n][s];
    }
}
