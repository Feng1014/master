package src.class03;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 求字符串中无重复最长子串的长度
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        int ans = 1;
        int pre = 1;
        /**从下标为1的字母开始枚举*/
        for (int i = 1; i < N; i++) {
            /**当前下标的字母距离上次出现相同字母之间的距离*/
            int p1 = i - map[str[i]];
            /**pre表示以当前字符的左边第一个字符为结尾的子串最大无重复子串的长度。
             * 以当前字符为结尾的子串最长无重复子串长度由左边第一个字符为结尾的子串的最长无重复子串长度递归得来*/
            int p2 = pre + 1;
            /**比较二者较小的值，即是以当前字符为结尾的子串的最长无重复子串的长度*/
            int cur = Math.min(p1, p2);
            /**以给定字符串中的每个字符为结尾的子串进行比较，求得最长无重复子串的长度*/
            ans = Math.max(ans, cur);
            /**pre更新为当前字符的最长无重复长度*/
            pre = cur;
            /**i位置的字符上次出现的位置更新为i*/
            map[str[i]] = i;
        }
        return ans;
    }
}
