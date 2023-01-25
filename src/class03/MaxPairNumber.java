package src.class03;

import java.util.Arrays;

/**
 * 给定一个数组，给定K值，当两个元素相差为K时，可以进行比赛。问给定数组中最多能进行多少场比赛
 */
public class MaxPairNumber {

    public static int maxPairNum1(int[] arr, int K) {
        if (K < 0) {
            return -1;
        }
        return process(arr, 0, K);
    }

    public static int process(int[] arr, int index, int K) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == K) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, r, index);
                ans = Math.max(ans, process(arr, index + 1, K));
                swap(arr, r, index);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int maxPairNum(int[] arr, int K) {
        if (K < 0 || arr == null || arr.length < 2) {
            return 0;
        }
        Arrays.sort(arr);
        int N = arr.length;
        int L = 0;
        int R = 0;
        int ans = 0;
        boolean[] used = new boolean[N];
        while (L < N && R < N) {
            if (used[L]) {
                L++;
            } else if (L >= R) {
                R++;
            } else {
                int distance = arr[R] - arr[L];
                if (distance == K) {
                    ans++;
                    used[R++] = true;
                    L++;
                } else if (distance < K) {
                    R++;
                } else {
                    L++;
                }
            }
        }
        return ans;
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = maxPairNum1(arr1, k);
            int ans2 = maxPairNum(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
