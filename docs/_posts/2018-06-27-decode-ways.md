---
title: Decode Ways
tags: DP
---

## 问题
[leetcode link](https://leetcode.com/problems/decode-ways/description/)

## 总体思路
- brute force: 所有的解集：所有字符都单个decode ， 有一对字符是couple decode，有两对字符是couple decode……
解集是2^n
- 观察发现这个问题可以从大问题转化为小问题 T(n) = T(n-1) + T(n-2) (对于满足限制的情况)。所以想到DP
- 0 是个要特殊处理的edge case

## 方案
时间O(n), 空间O(n)

{% highlight java %}
public int numDecodings(String s) {
    int[] count = new int[s.length()+1]; //i -> # of decodings of first i characters in s
    Arrays.fill(count, 0);

    char[] charArray = s.toCharArray();

    count[0] = 1; // empty string has 1 decode way
    count[1] = charArray[0] == '0' ? 0 : 1; // single character has 1 decode way

    for (int i = 2;i < count.length ; i++){
        if (charArray[i-1]!='0'){
            //decode ith as single digit
            count[i] += count[i-1];
        }

        String twoDigits = new String(charArray, i-2, 2);
        if (twoDigits.compareTo("10") >= 0 && twoDigits.compareTo("26") <= 0){
            //decode i-1, i as couple  digits
            count[i] += count[i-2];
        }

        //no need to check forward
        if (count[i] == 0){
            break;
        }
    }

    return count[count.length-1];

}
{% endhighlight %}
