package cn.wahaha.test.dataStructure.dbPlan20210926;

import java.util.Map;
/**
 * @Author zhangrenwei
 * @Description leetcode 学习计划之----21天动态规划
 * @Date 2021/10/3 11:14 下午
 **/
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
    /**
     * 最大子序和
     * https://leetcode-cn.com/problems/maximum-subarray/
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len <= 0) return -1;

        int maxSum = nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    /**
     *  环形子数组的最大和
     *  https://leetcode-cn.com/problems/maximum-sum-circular-subarray/
     *  直接两种情况，
     *  1：最大数组和在中间，和平时一样解法
     *  2：最大数组和是跨越头尾，从两边出发往中间靠拢必须都是最大，那就说明中间段就是最小，找最小，用和减去最小得到最大。
     *  3、两步得到的最大取更大的
     */
    public int maxSubarraySumCircular(int[] nums) {
        int dp = nums[0], max1 = nums[0], sum = nums[0];
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            sum += nums[i];
            dp = Math.max(nums[i], nums[i] + dp);
            max1 = Math.max(dp, max1);
        }

        int max2 = 0, min2 = 0;
        dp = nums[0];
        for (int i = 1; i < len; i++) {
            dp = Math.min(nums[i], dp + nums[i]);
            min2 = Math.min(dp, min2);
        }
        max2 = sum - min2;
        // 这里有个坑，如果元素全是负数，将得到 max2 = 0，就出问题了
        if (max2 == 0) {
            return max1;
        }

        return Math.max(max1, max2);
    }

    // ======================== 第六天 ========================

    /**
     * 乘积最大子数组
     * https://leetcode-cn.com/problems/maximum-product-subarray/
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int curMax = nums[0], curMin = nums[0], max = nums[0];
        for (int i = 1; i < len; i++) {
            int curMaxTemp = curMax;
            int curMinTemp = curMin;
            curMax = Math.max(Math.max(curMaxTemp * nums[i], nums[i]), curMinTemp * nums[i]);
            curMin = Math.min(Math.min(curMaxTemp * nums[i], nums[i]), curMinTemp * nums[i]);
            max = Math.max(max, curMax);
        }

        return max;
    }

    /**
     * 乘积为正数的最长子数组长度
     * https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product/
     * 解： 上面的题，加这道题，用数组来解题是比较好的，但是状态转移方程，当前状态只与前一个状态有关，所以我们利用滚动数组的思想，
     *      只借用两个变量，滚动使用。
     */
    public int getMaxLen(int[] nums) {
        int len = nums.length;
        int positive = 0, negative = 0, maxLength = 0;
        if (nums[0] > 0) {
            positive++;
            maxLength++;
        } else if (nums[0] < 0) {
            negative++;
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                positive++;
                negative = negative > 0 ? negative + 1 : 0;
            } else if (nums[i] < 0) {
                int posTemp = positive;
                int negTemp = negative;
                negative = posTemp + 1;
                positive = negTemp > 0 ? negTemp + 1 : 0;
            } else {
                negative = 0;
                positive = 0;
            }

            maxLength = Math.max(maxLength, positive);
        }

        return maxLength;
    }

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
