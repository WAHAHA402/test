package cn.wahaha.test.dataStructure;

/**
 * 深度优先搜索和广度优先搜索的简单介绍如下：
 * https://www.jianshu.com/p/b086986969e6
 */
public class DepthFirstSearch {
    // 题目： 岛屿数量 力扣200题
    // 思路：深度优先搜索，从第一个元素开始遍历，
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return -1;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int num = 0;
        for (int i = 0; i < nr; i++) {
            for (int j = 0; j < nc; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    dfs(grid, i, j);
                }
            }
        }

        return num;
    }

    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r-1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c + 1);
        dfs(grid, r+1, c);
    }

}
