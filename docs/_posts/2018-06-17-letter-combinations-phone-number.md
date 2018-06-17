---
title: Letter Combinations of a Phone Number
tags: backstrack
---

## 问题
[leetcode link](https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/)

## 总体思路
- 非常直白的问题，每个digit对应3-4个字母，只要把所有的情况给算出来就可以。时间O(2^n): 解集一共有3^n
- 我试着从backtrack入手，每个digit代表路径上的一个点/选择, 从当前的digit到下一个digit为路径，有3-4个选择
  - 如果到了最后一个点(digit)，就返回这个digit对应字母的list
  - 对于当前digit的每个对应的字母，进行backtrack
  - backtrack通常都用递归来实现（找路径的问题感觉都挺适合用递归的）
- Follow-up：由于这个问题的特殊性，我们知道可以不用对当前digit的每个字母都进行backtrack，因为他们返回的
结果都是一样的，只要backtrack一次就好（省了2-3次的冗余backtrack）

## 方案1
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

    for (char c : firstDigitCArray){
        List<String> subResults = letterCombinations(digits.substring(1));
        for (String subResult : subResults){
            result.add(c + subResult);
        }
    }
    return result;
}
{% endhighlight %}

## 方案1 Follow up
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
