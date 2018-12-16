---
title: Projection Area of 3D Shapes
tags: Math
---

## 问题
[leetcode link](https://leetcode.com/problems/projection-area-of-3d-shapes/description/)

## 总体思路
- 观察而得，分别算出zy，xy，xz三面的投影，然后相加
- xy自己是一种算法，只要数出点的个数
- xz 和 yz 是相似的算法，找到最高的那个柱子



## 方案
时间O(n)

{% highlight java %}
public int projectionArea(int[][] grid) {
    return getXY(grid) + getXZ(grid) + getZY(grid);
}

private int getXY(int[][] grid){
    int sum = 0;
    for (int[] x : grid){
        for (int z : x) {
            if (z != 0) sum++;
        }
    }
    return sum;
}

private int getXZ(int[][] grid){
    int sum = 0;
    for (int[] x : grid){
        List<Integer> intList = new ArrayList<Integer>();
        for (int i : x)
        {
            intList.add(i);
        }
        sum += Collections.max(intList);
    }
    return sum;
}

private int getZY(int[][] grid){
    int sum = 0;
    int yLength = grid[0].length;
    for (int i = 0;i < yLength;i++){
        int max = 0;
        for (int j=0;j<grid.length;j++){
            max = Math.max(grid[j][i], max);
        }
        sum += max;
    }
    return sum;
}
{% endhighlight %}
