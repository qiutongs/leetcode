---
title: Valid Palindrome II
tags: Two Pointers
---

## 问题
[leetcode link](https://leetcode.com/problems/valid-palindrome/description/)

## 总体思路
- 从上一题而来，想到可以简单的略过可能可以删掉的字符，向下进行
  - 但只有一次机会， 也不需要回溯， 所以还是O(n)


## 方案
时间O(n)

{% highlight java %}
private final static int DIFF = 'a' - 'A';

public boolean validPalindrome(String s) {
   return isPalindrome(s, 0, s.length()-1, true);
}

public boolean isPalindrome(String s, int left, int right, boolean canDeleteOneChar) {
    char[] cArray = s.toCharArray();

    while(left < right){
        if (cArray[left] != cArray[right]){
            if (canDeleteOneChar){
                return isPalindrome(s, left+1, right, false) || isPalindrome(s, left, right-1, false);
            } else {
                return false;
            }
        }

        left++;
        right--;
    }

    return true;
}
{% endhighlight %}
