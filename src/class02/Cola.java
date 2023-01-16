package src.class02;

/**
 * 给定零钱数组qian[100,50,10]，给定每种零钱对应的张数zhang[3,4,1]，给定一瓶可乐250元，需要购买两瓶可乐，问需要几次购买次数
 * 第一次购买250元的可乐，需要支付3张100，找回一张50.
 * 第二次购买250元的可乐，需要支付5张50，
 * 一共执行了8次投币，则有8次购买行为
 */
public class Cola {

    /**
     * @param m 需要买m瓶可乐
     * @param a 100元有a张
     * @param b 50元有b张
     * @param c 10元有c张
     * @param x 一瓶可乐需要x元
     * @return
     */
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {a, b, c};
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    /**
     * 发生了购买行为
     *
     * @param qian
     * @param zhang
     * @param rest  还需要rest元才能完成购买
     * @return
     */
    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int buy = buy(qian, zhang, rest - qian[first]);
            if (buy == -1) {
                return -1;
            }
            return 1 + buy;
        }
    }

    public static void giveRest(int[] qian, int[] zhang, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            zhang[i] += (oneTimeRest / qian[i]) * times;
            oneTimeRest %= qian[i];
        }
    }

    public static int putTimes(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {a, b, c};
        int puts = 0;
        int preQianRest = 0;
        int preQianZhang = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            int curQianFirstZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (zhang[i] >= curQianFirstZhang) {
                giveRest(qian, zhang, i + 1, (preQianRest + curQianFirstZhang * qian[i]) - x, 1);
                zhang[i] -= curQianFirstZhang;
                puts += preQianZhang + curQianFirstZhang;
                m--;
            } else {
                preQianRest += zhang[i] * qian[i];
                preQianZhang += zhang[i];
                continue;
            }
            int curQianBuyOneColaZhang = (x + qian[i] - 1) / qian[i];
            int curQianBuyColas = Math.min(zhang[i] / curQianBuyOneColaZhang, m);
            int oneTimeRest = curQianBuyOneColaZhang * qian[i] - x;
            giveRest(qian, zhang, i + 1, oneTimeRest, curQianBuyColas);
            puts += curQianBuyOneColaZhang * curQianBuyColas;
            m -= curQianBuyColas;
            zhang[i] -= curQianBuyOneColaZhang * curQianBuyColas;
            preQianRest += qian[i] * zhang[i];
            preQianZhang += zhang[i];
        }
        return m == 0 ? puts : -1;
    }

    public static void main(String[] args) {
        int colaMax = 10;
        int zhangMax = 10;
        int priceMax = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int right = right(m, a, b, c, x);
            int times = putTimes(m, a, b, c, x);
            if (right != times) {
                System.out.println("error");
            }
        }
        System.out.println("finished");
    }
}
