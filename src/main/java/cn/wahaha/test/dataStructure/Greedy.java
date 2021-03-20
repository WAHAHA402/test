package cn.wahaha.test.dataStructure;

/**
 * 贪心算法
 */
public class Greedy {
    // 跳跃游戏
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if(len == 1) return true;

        int rightMost = 0 + nums[0];

        for (int i = 0; i < len - 1; i++) {
            if(i <= rightMost){
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= len - 1){
                    return true;
                }
            }
        }

        return false;
    }

    // 判断子序列 贪心算法/也是双指针
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int i =0,  j = 0;

        while(i < sLen && j < tLen) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == sLen;
    }

}
