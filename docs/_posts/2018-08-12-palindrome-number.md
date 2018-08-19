---
title: Palindrome Number
tags: Math
---

## 问题
[leetcode link](https://leetcode.com/problems/palindrome-number/description/)

## 总体思路
- 直接的想法就是两个pointer，从两边向中间，但是对于数字没有办法直接做, 可以转化为string，但是就有O(n)的space cost
- 第二个直接的想法就是直接revert原数，然后和原数比较。但是反转后要处理溢出的情况。
  - 有个完美的类似方法，就是构造出左右“各一半”的两个数

## 感想
- 我以为没办法从两边到中间直接做，但其实可以先获取这个integer的长度，然后就可以得到最左边的数字了
- 对于整数，有O(1)的方式去revert
- 这个问题的edge case还是很容易出错的(1)只有一位数 (2)个位是0

## 方案
时间O(n)， 空间O(1)

{% highlight java %}
public boolean isPalindrome(int x) {

    if (x>=0 && x <=9)
        return true;
    if (x<0||x%10==0)
        return false;

    long lowHalf = 0;
    while(x > lowHalf){
      lowHalf = lowHalf * 10 + x % 10;
      x = x / 10;

      if (lowHalf == x) return true; // 1221
      if (lowHalf == x/10) return true; //121
  }
  return false;
}
{% endhighlight %}
