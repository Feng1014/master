package src.class03;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 轮盘电话机最小的拨号次数
 * https://leetcode.cn/problems/freedom-trail/
 */
public class FreedomTrail {

    public static int findRotateSteps(String ring, String key) {
        int N = ring.length();
        char[] str = ring.toCharArray();
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(str[i])) {
                map.put(str[i], new ArrayList<>());
            }
            map.get(str[i]).add(i);
        }
        int M = key.length();
        char[] strKey = key.toCharArray();
        int[][] dp = new int[N][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = -1;
            }
        }
        return process(0, 0, strKey, map, N, dp);
    }

    public static int process(int preButton, int index, char[] str, HashMap<Character, ArrayList<Integer>> map, int N, int[][] dp) {
        if (dp[preButton][index] != -1) {
            return dp[preButton][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == str.length) {
            return 0;
        } else {
            char cur = str[index];
            ArrayList<Integer> nextPos = map.get(cur);
            for (int next : nextPos
            ) {
                int cost = dial(preButton, next, N) + 1 + process(next, index + 1, str, map, N, dp);
                ans = Math.min(ans, cost);
            }
        }
        dp[preButton][index] = ans;
        return ans;
    }

    public static int dial(int p1, int p2, int N) {
        return Math.min(Math.abs(p1 - p2), Math.min(p1, p2) + N - Math.max(p1, p2));
    }
}
