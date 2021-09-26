package cn.wahaha.test.dataStructure;

import java.util.List;

public class DynamicProgramming {
    // 题目： 最长公共子序列
    // 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
    // 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    // 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
    // 若这两个字符串没有公共子序列，则返回 0。
    public int longestCommonSubsequence(String text1, String text2) {
        int rowLength = text1.length();
        int colLength = text2.length();
        int table[][] = new int[rowLength + 1][colLength + 1];

        for (int i = 0; i < rowLength + 1; i++) {
            table[i][0] = 0;
        }

        for (int i = 0; i < colLength + 1; i++) {
            table[0][i] = 0;
        }

        for (int row = 1; row < table.length; row++) {
            char ch1 = text1.charAt(row - 1);
            for (int col = 1; col < table[row].length; col++) {
                char ch2 = text2.charAt(col - 1);
                if (ch1 == ch2) {
                    table[row][col] = table[row - 1][col - 1] + 1;
                } else {
                    table[row][col] = Math.max(table[row - 1][col], table[row][col - 1]);
                }
            }

        }

        int[] lastRow = table[rowLength];
        return lastRow[colLength];

    }

    // 题目：打家劫舍
    // 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    // 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    // 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    //
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        int[] result = new int[length];
        result[0] = nums[0];
        if (length >= 2) {
            result[1] = Math.max(nums[0], nums[1]);
        }

        for (int i = 2; i < length; i++) {
            result[i] = Math.max(nums[i] + result[i - 2], result[i - 1]);
        }

        return result[length - 1];
    }

    // 题目： 不同路径
    // 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
    //机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
    //问总共有多少条不同的路径？。
    public int uniquePaths(int m, int n) {
        if(m == 0 || n ==0) return -1;
        if(m == 1|| n == 1) return 1;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    // 题目：(矩形)最小路径和
    // 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    //
    //说明：每次只能向下或者向右移动一步。
    public int minPathSum(int[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        if(grid == null || rowLength == 0 || colLength == 0) return 0;

//        int minSum = 0;
//        if (rowLength == 1){
//            for (int i = 0; i < grid[0].length; i++) {
//                minSum += grid[0][i];
//            }
//            return minSum;
//        }
//
//        if (grid[0].length == 1){
//            for (int i = 0; i < rowLength; i++) {
//                minSum += grid[i][0];
//            }
//            return minSum;
//        }


        int[][] dp = new int[rowLength][colLength];
        dp[0][0] = grid[0][0];
//        dp[0][1] = grid[0][0] + grid[0][1];
//        dp[1][0] = grid[0][0] + grid[1][0];
        for (int i = 1; i < rowLength; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < colLength; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < rowLength; i++) {
            for (int j = 1; j < colLength; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[rowLength - 1][colLength - 1];
    }

    // 题目：三角形最小路径和
    // 给定一个三角形 triangle ，找出自顶向下的最小路径和。
    //
    //每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
    //输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    //输出：11
    //解释：如下面简图所示：
    //   2
    //  3 4
    // 6 5 7
    //4 1 8 3
    //自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
    //
    // 这他娘的想下，也是二维dp 最小路径和，到底层最小的值 关键是要找到状态转移方程，以及确定边界条件
    public int minimumTotal(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) return -1;
        int size = triangle.size();

        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < size; i++) {
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        for (int i = 2; i < size; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }
        }

        int min = dp[size - 1][0];
        for (int i = 1; i < size; i++) {
            min = Math.min(min, dp[size - 1][i]);
        }


        return min;
    }

    // 题目： 最长回文子串
    // 最最重要的是理解状态转移方程

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串

        // 2.初始化
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        //3.状态转移
        // 先填右上角，原答案说先填左下角，我完全没明白
        // 填表规则：先一列一列填，再一行一行填
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // 头尾之间没有字符串剩余 j - i == 1; 或者只剩一个，j-i == 2，都是回文
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 状态转移
                        // abcdcba 举个例子，这里两个a相等，是不是回文取决于两个a里面的字符串是不是回文
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 上述如果相等，则判断是否要更新回文子串长度和起始位置
                if (dp[i][j] && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, maxLen + begin);
    }
}
