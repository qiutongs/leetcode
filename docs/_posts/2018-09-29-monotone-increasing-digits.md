---
title: Monotone Increasing Digits
tags: Array, DP
---

## 问题
[leetcode link](https://leetcode.com/problems/monotone-increasing-digits/description/)

## 总体思路
- 有点数字的trick，有Greedy的思想在里面
  - 当发现当前的digit比前一个大，就把当前的-1，前一个改成9.


## 方案
时间O(n)

{% highlight java %}
public int monotoneIncreasingDigits(int N) {
    int[] result = new int[32];
    int i = 0;
    result[0] = N % 10;
    N /= 10;

    while( N > 0){
        int d = N % 10;
        if (d > result[i]){
            result[i+1] = d - 1;
            int j = i;
            while(j >= 0 && result[j] != 9){
                result[j--] = 9;
            }
        } else {
            result[i+1] = d;
        }

        i++;
        N /= 10;
    }

    int n = 0;
    for (int p = i; p >= 0; p--){
        n = n * 10 + result[p];
    }
    return n;
}
{% endhighlight %}
