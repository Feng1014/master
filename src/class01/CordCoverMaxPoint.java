package src.class01;

import java.util.Arrays;

/**
 * 给定一个有序数组，表示X轴上的点；给定一个数K，表示绳子的长度；求绳子最多能压住X轴上几个点
 */
public class CordCoverMaxPoint {

    /**
     * 暴力解法 双层循环
     *
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint1(int[] arr, int L) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            res = Math.max(res, i - pre);
        }
        return res;
    }

    /**
     * 以右边界为基准做为贪心条件，再使用二分查找有多少个数字能够被绳子遮住
     *
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint2(int[] arr, int L) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int nearst = nearst(arr, i, arr[i] - L);
            res = Math.max(res, i - nearst + 1);
        }
        return res;
    }

    public static int nearst(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    /**
     * 滑动窗口，左边界和右边界同时移动，时间复杂度降为o(N)
     *
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint3(int[] arr, int L) {
        int left = 0;
        int right = 0;
        int res = 0;
        int N = arr.length;
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            res = Math.max(res, (right - left++));
        }
        return res;
    }

    public static int[] generateArray(int len, int max) {
        int size = (int) ((Math.random() * len) + 1);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 200;
        int testTimes = 10000;
        for (int j = 0; j < testTimes; j++) {
            int[] arr = generateArray(len, max);
            int L = (int) (Math.random() * len);
            int index1 = maxPoint1(arr, L);
            int index2 = maxPoint2(arr, L);
            int index3 = maxPoint3(arr, L);
            if (index1 != index2 || index1 != index3 || index2 != index3) {
                System.out.println("error");
            }
        }
        System.out.println("finished");
    }
}
