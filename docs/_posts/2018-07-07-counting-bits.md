---
title: Counting Bits
tags: Bit, DP
---

## 问题
[leetcode link](https://leetcode.com/problems/counting-bits/description/)

## 总体思路
- 方案1: 直接用Integer.bitCount
- 方案2: 自己算出bit count （用到了一个bit hack)
- 方案3: DP, bitCount(n) = bitCount(n>>1) + (n & 1)
  - 看到别人的答案，我自己完全想不到

## 方案1
时间O(n), 空间O(n)

{% highlight java %}
public int[] countBits(int num) {
    int[] result = new int[num+1];
    for (int i = 0;i < result.length;i++){
        result[i] = Integer.bitCount(i);
    }
    return result;
}
{% endhighlight %}

## 方案2
时间O(n), 空间O(n)

{% highlight java %}
public int[] countBits(int num) {
    int[] result = new int[num+1];
    for (int i = 0;i < result.length;i++){
        int count = 0, x = i;
        while(x > 0){
            //bit hack to turn off rightmost 1-bit
            x = x & (x-1);
            count++;
        }
        result[i] = count;
    }
    return result;
}
{% endhighlight %}

## 方案3
时间O(n), 空间O(n)

{% highlight java %}
public int[] countBits(int num) {
    int[] result = new int[num+1];
    result[0] = 0;

    for (int i = 1;i < result.length;i++){
        result[i] = result[i>>1] + (i&1);
    }
    return result;
}
{% endhighlight %}
