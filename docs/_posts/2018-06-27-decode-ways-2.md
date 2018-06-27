---
title: Decode Ways II
tags: DP
---

## 问题
[leetcode link](https://leetcode.com/problems/decode-ways-ii/description/)

## 总体思路
- 思路完全和Decode Ways I一样，只要处理 * 就好

## 方案
时间O(n), 空间O(n)

{% highlight java %}
public int numDecodings(String s) {
    long[] count = new long[s.length()+1]; //i -> # of decodings of first i characters in s
    Arrays.fill(count, 0);

    char[] charArray = s.toCharArray();

    count[0] = 1L; // empty string has 1 decode way
    count[1] = charArray[0] == '*' ? 9 : charArray[0] == '0' ? 0 : 1;

    for (int i = 2;i < count.length ; i++){
        //decode ith as single digit
        if (charArray[i-1]!='0'){
            if (charArray[i-1] == '*'){
                count[i] += 9 * count[i-1];
            } else {
                count[i] += count[i-1];
            }
        }

        //decode i-1, i as couple digits
        if (charArray[i-2] == '*' && charArray[i-1] == '*'){
            //11-19, 21-26
            count[i] += count[i-2] * 15;
        } else if (charArray[i-2] == '*'){
            //treat * as '1'
            count[i] += count[i-2];
            //treat * as '2'
            if (charArray[i-1] <= '6') count[i] += count[i-2];
        } else if (charArray[i-1] == '*'){
            if (charArray[i-2] == '1') count[i] += count[i-2] * 9;
            else if (charArray[i-2] == '2') count[i] += count[i-2] * 6;
        } else {
          String twoDigits = new String(charArray, i-2, 2);
          if (twoDigits.compareTo("10") >= 0 && twoDigits.compareTo("26") <= 0){
              count[i] += count[i-2];
          }
        }

        //no need to check forward
        if (count[i] == 0){
            break;
        }

        count[i] %= 1e9 + 7;
    }

    return (int)(count[count.length-1]);
}
{% endhighlight %}
