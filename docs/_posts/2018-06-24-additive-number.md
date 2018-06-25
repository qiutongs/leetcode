---
title: Additive Number
tags: backtrack
---

## 问题
[leetcode link](https://leetcode.com/problems/additive-number/description/)

## 总体思路
- 用backtrack暴力求解
  - 选择: 前面已经选了一些满足条件的数字）需要在当前的位置选下一个可能的数字
  - 限制: 这个数字 = 已选数字末尾 + 已选数字末尾前一个
  - 结束: 有所字符都被选完

## 方案

{% highlight java %}
public boolean isAdditiveNumber(String num) {
    if (num.length() < 3) return false;

    char[] numsArray = num.toCharArray();

    return backtrack(numsArray, new ArrayList<>(), 0);
}

private boolean backtrack(char[] numsArray, List<String> currentSeq, int pos){
    if (numsArray.length == pos){
        return currentSeq.size() >= 3;
    }

    for (int i = pos; i< numsArray.length;i++){
        String target = new String(numsArray, pos, i-pos+1);

        if (currentSeq.size() >= 2
            && !isAdditive(currentSeq.get(currentSeq.size()-2),
                           currentSeq.get(currentSeq.size()-1),
                           target)){
           continue;
        }

        currentSeq.add(target);
        if (backtrack(numsArray, currentSeq, i+1)){
            return true;
        }
        currentSeq.remove(target);
    }

    return false;
}

private boolean isAdditive(String s1, String s2, String s3){
    if (isInvalidNum(s1) || isInvalidNum(s2) || isInvalidNum(s3)) return false;

    return Long.valueOf(s3) == Long.valueOf(s1) + Long.valueOf(s2);
}

private boolean isInvalidNum(String s){
    return s.length() > 1 && s.charAt(0) == '0';
}
{% endhighlight %}
