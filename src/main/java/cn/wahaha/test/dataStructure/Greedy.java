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

    // 跳跃游戏2

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int step = 0;
        // 这里有个陷阱，end需要置为0才行；
        // int end = nums[0] + 0;
        int end = 0;
        int maxPosition = 0;

        for(int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if(i == end){
                step++;
                end = maxPosition;
            }
        }

        return step;
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

    // 种花问题
    /**
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     * 给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，
     * 能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int count = 0;
        int pre = -1;

        for(int i = 0; i < len; i++) {
            if(flowerbed[i] == 1) {
                if(pre < 0) {
                    count = (i - 0) / 2;
                } else {
                    count += (i - pre - 2) / 2;
                }
                pre = i;
            }
            if(count >= n) {
                return true;
            }

        }
        // 数组长度为0 或者 花坛没有种花
        if(pre < 0) {
            count = (len + 1) / 2;
        } else {
            count += (len - 1 - pre) / 2;
        }

        return count >= n;
    }

}
