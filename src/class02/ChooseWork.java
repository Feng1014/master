package src.class02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 给定一组工作，每个工作有难度等级和薪资；再给定一组工人，包括工人的能力，只有工人能力大于工作难度时，才能获得报酬。问每个工人获得的最大报酬。
 */
public class ChooseWork {

    public static class Job {
        private int hard;
        private int money;

        public Job(int h, int m) {
            hard = h;
            money = m;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
        }
    }

    /**
     * 贪心算法和二分查找
     * 首先将工作按照难度升序排列，难度相等按照薪资降序排列。排列后的工作按照后一个工作难度必须大于前一个工作难度且后一个工作报酬必须大于前一个工作报酬的规则存放在有序表中。
     * 工人使用二分在有序表中查找小于自己能力且最靠近自己的工作，获得该工作的报酬。每个工人都使用二分获得最大报酬，返回报酬数组。
     *
     * @param jobs
     * @param ability
     * @return
     */
    public static int[] chooseWork(Job[] jobs, int[] ability) {
        Arrays.sort(jobs, new JobComparator());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobs[0].hard, jobs[0].money);
        Job pre = jobs[0];
        for (int i = 1; i < jobs.length; i++) {
            if (pre.hard != jobs[i].hard && jobs[i].money > pre.money) {
                pre = jobs[i];
                map.put(jobs[i].hard, jobs[i].money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }

}
