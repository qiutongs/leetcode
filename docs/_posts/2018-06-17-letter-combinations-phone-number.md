---
title: Letter Combinations of a Phone Number
tags: recursion
---

## 问题
[leetcode link](https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/)

## 总体思路
- 非常直白的问题，每个digit对应3-4个字母，只要把所有的情况给算出来就可以。时间O(2^n): 解集一共有3^n
- 直接recursion, 找到n-1时候的解，然后当前数字对应的字母给放到最前面，得到正确的解
- 可以backtrack：TOOD 还没实现

## 方案
时间O(2^n), 空间O(n)

{% highlight java %}
private static final HashMap<Character, String> PHONE_DIC = new HashMap<>();
static {
    PHONE_DIC.put('2', "abc");
    PHONE_DIC.put('3', "def");
    PHONE_DIC.put('4', "ghi");
    PHONE_DIC.put('5', "jkl");
    PHONE_DIC.put('6', "mno");
    PHONE_DIC.put('7', "pqrs");
    PHONE_DIC.put('8', "tuv");
    PHONE_DIC.put('9', "wxyz");
}

public List<String> letterCombinations(String digits) {
    if (digits.isEmpty()){
        return Collections.emptyList();
    }

    List<String> result = new LinkedList<>();
    char firstDigit = digits.charAt(0);
    char[] firstDigitCArray = PHONE_DIC.get(firstDigit).toCharArray();

    if (digits.length() == 1){
        for (char c : firstDigitCArray){
            result.add(String.valueOf(c));
        }
        return result;
    }

    List<String> subResults = letterCombinations(digits.substring(1));
    for (char c : firstDigitCArray){
        for (String subResult : subResults){
            result.add(c + subResult);
        }
    }


    return result;
}
{% endhighlight %}

## Reference
https://blog.csdn.net/ajianyingxiaoqinghan/article/details/79682147
