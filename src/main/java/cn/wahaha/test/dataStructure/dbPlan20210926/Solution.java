package cn.wahaha.test.dataStructure.dbPlan20210926;

import java.util.Map;

public class Solution {
    // ======================== 第一天 ========================
    // 斐波那契数列
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }

    /**
     * 泰波那契序列 Tn 定义如下： 
     *
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     *
     * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
     */
    // 思路，动态规划：滚动数组
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;

        int a = 0, b = 0, c = 1, d = 1;
        for (int i = 3; i <= n; i++) {
            a = b;
            b = c;
            c = d;
            d = a + b + c;
        }
        return d;
    }

    // ======================== 第二天 ========================
    /**爬楼梯：
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int a = 1, b = 1, c = 2;
        for (int i = 3; i <= n; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;

    }

    /**使用最小花费爬楼梯：
     *
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
     * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[n] = Math.min(dp[n - 1] + cost[n - 1], dp[n - 2] + cost[n - 2]);
        }
        return dp[n];
    }

    // ======================== 第三天 ========================
    /**
     * 打家劫舍
     * https://leetcode-cn.com/problems/house-robber/
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];

        if (len >= 2) {
            dp[1] = Math.max(nums[0], nums[1]);
        }
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[len - 1];
    }

    /**
     * 打家劫舍2
     * https://leetcode-cn.com/problems/house-robber-ii/
     */
    public int rob_v2(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        if (len == 1) return nums[0];
        if (len == 2) return Math.max(nums[0], nums[1]);

        return Math.max(robRange(nums, 0, len - 2), robRange(nums, 1, len - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int dpPre = nums[start], dpCurrent = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = dpCurrent;
            dpCurrent = Math.max(dpPre + nums[i], dpCurrent);
            dpPre = temp;
        }
        return dpCurrent;
    }

    /** 删除并获得点数
     * https://leetcode-cn.com/problems/delete-and-earn/
     */
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int i = 0; i < nums.length; i++) {
            maxVal = Math.max(maxVal, nums[i]);
        }
        // 灵魂操作，对每个相同数字进行求和。
        int[] sum = new int[maxVal + 1];
        for (int val: nums) {
            sum[val] += val;
        }
        return rob(sum);
    }

    // ======================== 第四天 ========================
    /**
     * 跳跃游戏
     * https://leetcode-cn.com/problems/jump-game/
     */
    public boolean canJump(int[] nums) {
        int rightMost = nums[0] + 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= len - 1) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 跳跃游戏 II : 注意，这里总是能跳到最后的位置！不用考虑！
     * https://leetcode-cn.com/problems/jump-game-ii/
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int end = 0, step = 0;
        int rightMost = nums[0] + 0;
        for (int i = 0; i < len - 1; i++) {
            rightMost = Math.max(rightMost, i + nums[i]);
            if (end == i) {
                step++;
                end = rightMost;
            }
        }
        return step;
    }

    // ======================== 第五天 ========================



    // ======================== 第六天 ========================

    // ======================== 第七天 ========================

    // ======================== 第八天 ========================

    // ======================== 第九天 ========================

    // ======================== 第十天 ========================

    // ======================== 第十一天 ========================

    // ======================== 第十二天 ========================

    // ======================== 第十三天 ========================

    // ======================== 第十四天 ========================

    // ======================== 第十五天 ========================

    // ======================== 第十六天 ========================

    // ======================== 第十七天 ========================

    // ======================== 第十八天 ========================

    // ======================== 第十九天 ========================

    // ======================== 第二十天 ========================

    // ======================== 第二十一天 ========================










}
