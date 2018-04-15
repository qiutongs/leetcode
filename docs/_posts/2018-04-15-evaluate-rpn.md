---
layout: post
title: Evaluate Reverse Polish Notation
---

## 问题
[leetcode link](https://leetcode.com/problems/evaluate-reverse-polish-notation/description/)

## 总体思路
- 很简单的问题，可以参考[wikipedia](https://en.wikipedia.org/wiki/Reverse_Polish_notation)
- 我写过一个相关的[博客](https://qiutongs.github.io/computer-science/stack/math-expression)

## 实现思路

## 我的代码

{% highlight java %}
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0){
            throw new RuntimeException();
        }
        
        Stack<Integer> operandsStack = new Stack<Integer>();
        
        for (String token : tokens){
            if (isOperator(token)){
                Integer i2 = operandsStack.pop();
                Integer i1 = operandsStack.pop();
                operandsStack.push(calculate(i1, i2, token));
            } else {
                operandsStack.push(Integer.valueOf(token));
            }
        }
        return operandsStack.peek();
    }
    
    private boolean isOperator(String token){
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
    
    private Integer calculate(Integer i1, Integer i2, String operator){
        switch(operator){
            case "+": return i1+i2;
            case "-": return i1-i2;
            case "*": return i1*i2;
            case "/": return i1/i2;
            default: throw new RuntimeException();
        }
    }
{% endhighlight %}