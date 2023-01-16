package src.class02;

/**
 * 给定二维收入数组income[N][2]，表示N个司机被分配到A和B两个地方分别获得的报酬，问将N个司机平均分配后能够获得的最大收入
 */
public class Drive {

    public static int maxMoney(int[][] income) {
        if (income == null || income.length == 0 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        int M = N >> 1;
        return process(income, 0, M);
    }

    /**
     * 从index位置开始，所有的司机都分配给A和B，其中A地还需要rest个司机。返回司机的最大收益
     *
     * @param income
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[][] income, int index, int rest) {
        if (index == income.length) {
            return 0;
        }
        if (income.length - index == rest) {
            return income[index][0] + process(income, index + 1, rest - 1);
        }
        if (rest == 0) {
            return income[index][1] + process(income, index + 1, rest);
        }
        int p1 = income[index][0] + process(income, index + 1, rest - 1);
        int p2 = income[index][1] + process(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static int maxMoney1(int[][] income) {
        int N = income.length;
        int M = N >> 1;
        int[][] dp = new int[N + 1][M + 1];
        /**第一层循环表示index的所有值，从0到N，逆序for循环，有助于初始动态规划的计算*/
        for (int i = N - 1; i >= 0; i--) {
            /**第二层循环表示rest的所有值，从0到M*/
            for (int j = 0; j <= M; j++) {
                /**如果剩余的司机数刚好A地全部需要*/
                if (N - i == j) {
                    dp[i][j] = income[i][0] + dp[i + 1][j - 1];
                } else if (j == 0) {
                    /**如果A地已经分配结束，则将剩余司机全部分配至B地*/
                    dp[i][j] = income[i][1] + dp[i + 1][j];
                } else {
                    /**比较将司机分配到A地和B地所能获得的最大收益*/
                    int p1 = income[i][0] + dp[i + 1][j - 1];
                    int p2 = income[i][1] + dp[i + 1][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][M];
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 20;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] income = randomMatrix(len, value);
            int money = maxMoney(income);
            int money1 = maxMoney1(income);
            if (money != money1) {
                System.out.println("error");
            }
        }
        System.out.println("finished");
    }

}
