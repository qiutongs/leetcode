---
title: Decode String
---

## 问题
[leetcode link](https://leetcode.com/problems/decode-string/description/)

## 总体思路
- 从左到右遍历，明显可以O(n)时间解决。只需要选对数据结构
- 当遇到[]，就需要先处理这个”子字符串[......]“，类似于递归
- 想到用stack，每个元素代表当前level的字符串的暂时状态
  - 遇到‘[’， 表示下一个level
  - 遇到‘]’， 表示当前level结束，把当前level的字符串repeat X 次，加到上一个level中
  - 遇到字符， 就加到当前level中
- 写的过程中发现，需要另一个stack记录当前level需要重复的次数。

## 复杂度
  - 时间O(n), 空间O(n)

## 我的代码

  {% highlight java %}
  public String decodeString(String s) {
      if (s.isEmpty()){
          return "";
      }

      Stack<StringBuilder> currentStrStack = new Stack<>();
      Stack<Integer> repNStack = new Stack<>();
      char[] sArray = s.toCharArray();
      int repN = 0;

      //root string builder with initialized one repeat
      currentStrStack.push(new StringBuilder());
      repNStack.push(1);

      for (char c : sArray){
          //new sub-string needs to be decoded first,
          //1. put a new String builder to stack
          //2. put repeat number to stack
          if (c == '['){
              currentStrStack.push(new StringBuilder());
              repNStack.push(repN);
              repN = 0;
          } // finish sub-string decoding. Append to the parent string with repeat
          else if (c == ']'){
              String currentStr = currentStrStack.pop().toString();
              currentStrStack.peek().append(repeat(currentStr, repNStack.pop()));
          } else if (Character.isDigit(c)){
              repN = repN*10 + (c - '0');
          } else {
              currentStrStack.peek().append(c);
          }
      }

      return currentStrStack.peek().toString();
  }

  private String repeat(String str, int repN){
      StringBuilder sb = new StringBuilder();
      for (int i=0;i<repN;i++){
          sb.append(str);
      }
      return sb.toString();
  }
  {% endhighlight %}
