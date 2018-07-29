---
title: Valid Palindrome
tags: Two Pointers
---

## 问题
[leetcode link](https://leetcode.com/problems/valid-palindrome/description/)

## 总体思路
- 对于这种对称的问题，two pointers很容易想到，从两边到中间验证


## 方案
时间O(n)

{% highlight java %}
private final static int DIFF = 'a' - 'A';

public boolean isPalindrome(String s) {
    char[] cArray = s.toCharArray();

    int left = 0, right = cArray.length - 1;

    while(left < right){
        if (!isValid(cArray[left])){
            left++;
            continue;
        }

        if (!isValid(cArray[right])){
            right--;
            continue;
        }

        if (!isEqual(cArray[left], cArray[right])){
            return false;
        }

        left++;
        right--;
    }

    return true;
}

private boolean isValid(char c){
    return ( c >= '0' && c <= '9') || ( c >= 'a' && c <= 'z') || ( c >= 'A' && c <= 'Z');
}

private boolean isEqual(char c1, char c2){
    c1 = ( c1 >= 'A' && c1 <= 'Z') ? (char)(c1 + DIFF) : c1;
    c2 = ( c2 >= 'A' && c2 <= 'Z') ? (char)(c2 + DIFF) : c2;
    return c1 == c2;
}
{% endhighlight %}
