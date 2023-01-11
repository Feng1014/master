package src.class01;

/**
 * 求矩阵中最长的递增路径
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPath {

    /**
     * 分别求以矩阵中每个元素为起点的最长路径，最后再比较所有元素中的最长递增路径
     *
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process(matrix, i, j));
            }
        }
        return ans;
    }

    /**
     * 矩阵中每个元素都可以走上下左右四个方向，故求每个元素的四个方向中最大的递增路径，再加上本身和四个方向相差一步，四个方向中的最大值加1即是该元素的最长递增路径
     *
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public static int process(int[][] arr, int i, int j) {
        int up = i > 0 && arr[i - 1][j] > arr[i][j] ? process(arr, i - 1, j) : 0;
        int down = i < arr.length - 1 && arr[i + 1][j] > arr[i][j] ? process(arr, i + 1, j) : 0;
        int left = j > 0 && arr[i][j - 1] > arr[i][j] ? process(arr, i, j - 1) : 0;
        int right = j < arr[0].length - 1 && arr[i][j + 1] > arr[i][j] ? process(arr, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    /**
     * 加入缓存的递归，记忆化搜索
     *
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j, dp));
            }
        }
        return ans;
    }

    /**
     * 如果缓存表为0值，则表示矩阵中i,j位置的元素没有参与运算。矩阵元素参与运算后把值放入缓存表中，后续任意元素遇到同样位置时，可以直接从缓存表中获取值。
     *
     * @param arr
     * @param i
     * @param j
     * @param dp
     * @return
     */
    public static int process1(int[][] arr, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = i > 0 && arr[i - 1][j] > arr[i][j] ? process1(arr, i - 1, j, dp) : 0;
        int down = i < arr.length - 1 && arr[i + 1][j] > arr[i][j] ? process1(arr, i + 1, j, dp) : 0;
        int left = j > 0 && arr[i][j - 1] > arr[i][j] ? process1(arr, i, j - 1, dp) : 0;
        int right = j < arr[0].length - 1 && arr[i][j + 1] > arr[i][j] ? process1(arr, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }

}
