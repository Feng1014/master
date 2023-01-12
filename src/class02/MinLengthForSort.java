package src.class02;

/**
 * 给定一个乱序数组，求排序时需要执行排序的最短子数组长度？【2，6，4，8，10，9，15】需要排序的最短子数组是【6，4，8，10，9】
 */
public class MinLengthForSort {

    public static int findUnsortedSubarray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        /**从左向右寻找最后一个不符合递增顺序的元素下标。
         * maxValue表示arr[i]前的一个元素，初值为最小值。如果maxValue大于arr[i]表示从左向右的递增序已经不满足。
         * arr[i]是不满足递增序的第一个元素，寻找最后一个不满足递增序的元素，赋给right
         * right的初值为-1，表示maxValue的下标*/
        int right = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (maxValue > arr[i]) {
                right = i;
            }
            maxValue = Math.max(maxValue, arr[i]);
        }
        /**从右向左寻找最后一个不符合递减顺序的元素下标。
         * minValue表示arr[i]的后一个元素，初值为最大值，如果minValue小于arr[i]表示从右向左的递减序已经不满足。
         * arr[i]即是不满足的第一个元素，寻找最后一个不满足递减序的元素，赋给left
         * left的初值为N，表示minValue的下标*/
        int left = N;
        int minValue = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--) {
            if (minValue < arr[i]) {
                left = i;
            }
            minValue = Math.min(minValue, arr[i]);
        }
        return Math.max(0, right - left + 1);
    }
}
