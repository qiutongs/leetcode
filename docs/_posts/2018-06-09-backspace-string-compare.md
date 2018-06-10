---
title: Backspace String Compare
---

## 问题
[leetcode link](https://leetcode.com/problems/backspace-string-compare/description/)

## 总体思路
- Solution 1： 从左至右遍历，用stack存储valid character，如果遇到backspace就pop stack。
- Solution 2：Follow up 提示可以O(1) space， 想到了two pointers。
  - 从右向左遍历
  - 记录backspace的个数，然后跳过“删掉的字符串”
  - 注意好边界：两个字符串都要处理到”尽头“

## 复杂度
  - Solution 1：时间O(n), 空间O(n)
  - Solution 2：时间O(n), 空间O(1)

## 我的代码

  {% highlight java %}
  public boolean backspaceCompare(String S, String T) {
      char[] sArray = S.toCharArray();
      char[] tArray = T.toCharArray();

      int sp = sArray.length-1, tp = tArray.length-1;
      int sBackCount = 0, tBackCount = 0;

      while (sp >=0 || tp >=0){
          if (sp >= 0){
            if (sArray[sp] == '#'){
                sBackCount++;
                sp--;
                continue;
            }

            if (sBackCount>0){
                sBackCount--;
                sp--;
                continue;
            }
          }

          if (tp >= 0){
            if (tArray[tp] == '#'){
                tBackCount++;
                tp--;
                continue;
            }

            if (tBackCount>0){
                tBackCount--;
                tp--;
                continue;
            }
          }

          //now, sp/tp points to valid character or equal to -1
          if (sp >=0 && tp >= 0 && sArray[sp] == tArray[tp]){
              sp--;
              tp--;
          } else {
              return false;
          }
      }

      return sp == -1 && tp == -1 ? true : false;
  }
  {% endhighlight %}
