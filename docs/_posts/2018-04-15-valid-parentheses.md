---
title: Valid Parentheses
---

## 问题
[leetcode link](https://leetcode.com/problems/valid-parentheses/description/)

## 总体思路
- 这一类的问题很容易想到用stack解决。每当看到一个开括号时，加入stack，等待闭括号和它对应。

## 复杂度
时间O(n), 空间O(n)

## 我的代码

{% highlight java %}
public boolean isValid(String s) {
    if (s.isEmpty()){
        return true;
    }
    
    Stack<Character> stack = new Stack<>();
    int i = 0;
    while(i < s.length()){
        Character c = s.charAt(i);
        if (isOpenParentheses(c)){
            stack.push(c);
        } else {
            if (stack.isEmpty()){
                return false;
            }
            
            Character topC = stack.peek();
            if (isMatching(topC, c)){
                stack.pop();
            } else {
                return false;
            }
        }
        i++;
    }
    
    return stack.isEmpty();
}

private boolean isOpenParentheses(Character c){
    return c.equals('(') || c.equals('{') || c.equals('[');
}

private boolean isMatching(Character openC, Character closeC){
    return (openC.equals('(') && closeC.equals(')')) || 
           (openC.equals('[') && closeC.equals(']')) ||
           (openC.equals('{') && closeC.equals('}')) ;
}
{% endhighlight %}