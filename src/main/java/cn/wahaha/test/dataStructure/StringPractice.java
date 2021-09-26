package cn.wahaha.test.dataStructure;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringPractice {
    // 题目：无重复的最长子串
    public int lengthOfLongestSubstring(String s) {
        Set<Character> diffSubString = new HashSet<>();
        int ans = 0;
        int backPos = -1;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (i != 0) {
                // 这里注意移除前面一个
                diffSubString.remove(s.charAt(i - 1));
            }

            while (backPos + 1 < len && !diffSubString.contains(s.charAt(backPos + 1))) {
                diffSubString.add(s.charAt(backPos + 1));
                backPos++;
            }
            ans = Math.max(ans, backPos - i + 1);
        }

        return ans;
    }







}
