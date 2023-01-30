package src.class03;

/**
 * 给定一个0和1组成的数组，问边界全由1组成的正方形的最大面积
 * https://leetcode.cn/problems/largest-1-bordered-square/
 */
public class Largest1BorderedSquare {

    public static int largest1BorderedSquare(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int[][] right = new int[N][M];
        int[][] down = new int[N][M];
        setBorderRightDown(m, right, down);
        for (int size = Math.min(N, M); size > 0; size--) {
            if (hasSizeBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }

    public static void setBorderRightDown(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i >= 0; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i >= 0; i--) {
            if (m[r - 1][i] == 1) {
                down[r - 1][i] = 1;
                right[r - 1][i] = right[r - 1][i + 1] + 1;
            }
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 2; j >= 0; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public static boolean hasSizeBorder(int size, int[][] right, int[][] dowm) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && dowm[i][j] >= size && right[i + size - 1][j] >= size && dowm[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }
}
