---
title: Maximum Subarray
tags: Array, DP
---

## 问题
[leetcode link](https://leetcode.com/problems/maximum-subarray/description/)

## 总体思路
- 可以用DP的思想，但也不用存所有的中间结果 O(n) = O(n-1) > 0 ? O(n-1) + a(n) : a(n)
- 提示看到divide and conquer
  - 分成两部分，找左右做大的，再找一定穿过中间的 -> O(nlog(n))


## 方案
时间O(n)

{% highlight java %}
public int maxSubArray(int[] nums) {
    int max = Integer.MIN_VALUE;
    int maxEndHere = 0;

    //O(n) = O(n-1) > 0 ? O(n-1) + a(n) : a(n)
    for (int num : nums){
        maxEndHere = maxEndHere > 0 ? maxEndHere + num : num;
        max = Math.max(max, maxEndHere);
    }
    return max;
}
{% endhighlight %}
