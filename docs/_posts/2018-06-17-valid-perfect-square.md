---
title: Valid Perfect Square
tags: math
---

## 问题
[leetcode link](https://leetcode.com/problems/valid-perfect-square/description/)

## 总体思路
- 完全仿照了Sqrt(x)
- 注意两个结束条件
  - num = m * m
  - start = end or start + 1 = end

## 方案1
时间O(logN), 空间O(1)

{% highlight java %}
public boolean isPerfectSquare(int num) {
    if (num < 0) return false;

    int start = 1, end = num;

    while(true){
        int m = (start + end)/2;

        if (num / m == m){
            return num % m == 0;
        }

        //start = end or start + 1 = end
        if (m == start){
            return false;
        }

        if (m > num / m){
            end = m;
        } else {
            start = m;
        }
    }
}
{% endhighlight %}
