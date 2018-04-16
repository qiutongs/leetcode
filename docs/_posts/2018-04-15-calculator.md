---
title: Basic Calculator
---

## 问题
[leetcode link](https://leetcode.com/problems/basic-calculator/description/)

## 总体思路（一）
- 这个问题有个通用的算法shunting-yard，我写过一个相关的[博客](https://qiutongs.github.io/computer-science/stack/math-expression)
- 然而发现这个solution的运行速度特别慢。

## 实现思路（一）
这里只提一下parse的实现。我们需要把String给分解成由操作数和操作符组成的数组。
简单的方式是正则表达式（整数或者支持的符号）\d+|[\(\)\+-]。然而发现leetcode并不支持Pattern class。
于是写了一个笨方法去拿到一个代表整数的substring。后来发现更简单的方式是 currentNumber * 10 + currentDigit

## 复杂度（一）
时间O(n), 空间O(n)

## 我的代码（一） 

{% highlight java %}
public int calculate(String s) {
    List<String> tokens = parse(s);
    Stack<Integer> operandStack = new Stack<>();
    Stack<String> operatorStack = new Stack<>();

    for (String token : tokens) {
        if (token.matches("\\d+")) {
            operandStack.push(Integer.valueOf(token));
        } else {
            if (token.matches("[\\+-]")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    processStack(operandStack, operatorStack);
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    processStack(operandStack, operatorStack);
                }
                operatorStack.pop();
            }
        }
    }

    while (!operatorStack.isEmpty()) {
        processStack(operandStack, operatorStack);
    }

    return operandStack.peek();
}

private static void processStack(Stack<Integer> operandStack, Stack<String> operatorStack) {
    String operator = operatorStack.pop();
    int op2 = operandStack.pop();
    int op1 = operandStack.pop();
    operandStack.push(evaluate(op1, op2, operator));
}

private static List<String> parse(String s) {
    List<String> result = new LinkedList<>();
    Integer numberLeftIndex = null;

    for (int i = 0; i < s.length(); i++) {

        char c = s.charAt(i);

        if (Character.isDigit(c)) {
            numberLeftIndex = numberLeftIndex == null ? i : numberLeftIndex;
            continue;
        } else {
            if (numberLeftIndex != null) {
                result.add(s.substring(numberLeftIndex, i));
                numberLeftIndex = null;
            }

            if (c == '+' || c == '-' || c == '(' || c == ')') {
                result.add(String.valueOf(c));
            }

        }
    }

    if (numberLeftIndex != null) {
        result.add(s.substring(numberLeftIndex));
    }

    return result;
}

private static int evaluate(int op1, int op2, String operator) {
    switch (operator) {
        case "+":
        return op1 + op2;
        case "-":
        return op1 - op2;
        default:
        throw new RuntimeException();
    }
}
{% endhighlight %}

## 总体思路（二）
- 在discuss里看到一个高票的答案(https://leetcode.com/problems/basic-calculator/discuss/62361/Iterative-Java-solution-with-stack)
- 这个简单的算法利用了+-的简易：（1）没有操作符优先级的问题）（2）括号展开也只是+-。

## 实现思路（二）
- 用一个stack来跟踪当前level的符号是+1还是-1（初始化+1）
- 当看到一个数的时候，两个变量会决定是+还是-： （1）当前level的符号 （2）这个数左边的符号。 
- 当看到左括号的时候，说明要换一个level，而最近的符号也就是新level的符号，把它加入stack。
- 当看到右括号的时候，pop stack

## 复杂度（二）
时间O(n), 空间O(n)

## 我的代码（二） 

{% highlight java %}
public int calculate(String s) {
    int result = 0;
    int sign = 1;
    Stack<Integer> contextSignStack = new Stack<>();
    contextSignStack.push(sign);
    
    int currentNumber = 0;
    for (char c : s.toCharArray()){
        if (c <= '9' && c >= '0'){
            currentNumber = currentNumber * 10 + (int)(c-'0');
        } else if (c == '+'){
            result += currentNumber * sign;
            currentNumber = 0;
            sign = 1 * contextSignStack.peek();
        } else if (c == '-'){
            result += currentNumber * sign;
            currentNumber = 0;
            sign = -1 * contextSignStack.peek();
        } else if (c == '('){
            contextSignStack.push(sign);
        } else if (c == ')'){
            contextSignStack.pop();
        }
    }
    
    result += currentNumber * sign;
    
    return result;
}
{% endhighlight %}