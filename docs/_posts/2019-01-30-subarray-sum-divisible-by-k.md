---
title: Subarray Sums Divisible by K
tags: Array
---

## 问题
[leetcode link](https://leetcode.com/problems/subarray-sums-divisible-by-k/)

来自google面经

## 总体思路
- BF方法，需要O(n^3)
- 对于数组的sum问题，不需要对于每个subarray都计算一遍sum，所以很容易简化到O(n^2)
- 看到了GfG提示，才明白O(n+K)的算法：把找“subarray divisible by k”的问题转化为
把sum(0-i) mod k的结果给数出来。相同余数的放到一个bucket里。最后的结果就是在每个bucket里挑两个

## 细节
- 余数为0的要单独处理
- 余数可能为负数

## 方案
时间O(n+K)
空间O(n+K)

{% highlight java %}
public int subarraysDivByK(int[] A, int K) {
    int result = 0;
    int remainA[] = new int[A.length];
    int remainCount[] = new int[K];

    Arrays.fill(remainCount, 0);

    // 1. calculate the sum: sum[i] = A[0] + ... + A[i]
    remainA[0] = A[0];
    for (int i = 1; i < A.length; i++) {
        remainA[i] = remainA[i-1] + A[i];
    }

    // 2. calculate the remain: remainA[i] = sum[i] % k
    // 3. count for each remain
    for (int i = 0; i < A.length; i++) {
        remainA[i] %= K;

        // remainA[i] can < 0
        if ( remainA[i] < 0 ) remainA[i] += K;

        remainCount[remainA[i]]++;
    }

    // 4. sum up possible subarray in each remain "bucket", including remain = 0
    for (int i = 0; i < K; i++) {
        // pick two element in the same bucket
        result += remainCount[i] * (remainCount[i] - 1) / 2;
    }

    // 5. add the divisible by K (remain is 0)
    result += remainCount[0];

    return result;
}
{% endhighlight %}

## Ref

https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/
