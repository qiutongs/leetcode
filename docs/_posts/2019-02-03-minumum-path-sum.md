---
title: Minimum Path Sum
tags: Array, DP
---

## 问题
[leetcode link](https://leetcode.com/problems/minimum-path-sum/)

## 总体思路
- 直白的DP问题


## 方案
时间O(mn),
空间O(mn),

{% highlight java %}
public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;

    int[][] pathSum = new int[m][n];

    // initialize starting point
    pathSum[0][0] = grid[0][0];

    // initialize first row
    for (int j = 1; j < n; j++) {
        pathSum[0][j] = pathSum[0][j-1] + grid[0][j];
    }

    // initialize first column
    for (int i = 1; i < m; i++) {
        pathSum[i][0] = pathSum[i-1][0] + grid[i][0];
    }

    // Opt(m,n) = Min{ Opt(m-1)(n), Opt(m)(n-1) } + grid(m,n)
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            pathSum[i][j] = Math.min(pathSum[i-1][j], pathSum[i][j-1]) + grid[i][j];
        }
    }

    return pathSum[m-1][n-1];
}
{% endhighlight %}
