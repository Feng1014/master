package src.class03;

import java.util.Arrays;

/**
 * 给定一个数组表示每个人的重量，一只船的容量为limit，且一只船只能装下两个人。问将所有人运输完最少需要几艘船。
 * https://leetcode.com/problems/boats-to-save-people/
 */
public class BoatsToSavePeople {

    public static int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }
        Arrays.sort(people);
        int N = people.length;
        if (people[N - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (people[i] <= limit / 2) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;
        while (L >= 0) {
            int solved = 0;
            while (R < N && people[L] + people[R] <= limit) {
                solved++;
                R++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = N - all - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }
}
