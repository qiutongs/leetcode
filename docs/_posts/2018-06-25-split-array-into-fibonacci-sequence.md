---
title: Split Array into Fibonacci Sequence
tags: backtrack
---

## 问题
[leetcode link](https://leetcode.com/problems/split-array-into-fibonacci-sequence/description/)

## 总体思路
- 几乎可以重用“Additive Number”问题的代码, 但有以下调整
  - 需要处理大整数, 暂时只会catch NumberFormatException
  - 为了解决大整数的时间, 加了一个限定: 数字的长度要 <= 10 （max integer)


## 方案

{% highlight java %}
public List<Integer> splitIntoFibonacci(String S) {
    if (S.length() < 3) return Collections.emptyList();

    char[] numsArray = S.toCharArray();

    return backtrack(numsArray, new ArrayList<>(), 0);
}

private List<Integer> backtrack(char[] numsArray, List<String> currentSeq, int pos){
    if (numsArray.length == pos){
        if (currentSeq.size() >= 3){
            List<Integer> result = new LinkedList<Integer>();
            for (String seq : currentSeq){
                result.add(Integer.valueOf(seq));
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }

    for (int i = pos; i< numsArray.length;i++){
        if (i-pos+1 > 10){
            break;
        }

        String target = new String(numsArray, pos, i-pos+1);

        if (currentSeq.size() >= 2
            && !isAdditive(currentSeq.get(currentSeq.size()-2),
                           currentSeq.get(currentSeq.size()-1),
                           target)){
           continue;
        }

        currentSeq.add(target);
        List<Integer> subResult = backtrack(numsArray, currentSeq, i+1);
        if (!subResult.isEmpty()){
            return subResult;
        }
        currentSeq.remove(target);
    }

    return Collections.emptyList();
}

private boolean isAdditive(String s1, String s2, String s3){
    if (isInvalidNum(s1) || isInvalidNum(s2) || isInvalidNum(s3)) return false;

    try{
        return Integer.valueOf(s3) == Integer.valueOf(s1) + Integer.valueOf(s2);
    } catch (NumberFormatException e){
        return false;
    }
}

private boolean isInvalidNum(String s){
    return s.length() > 1 && s.charAt(0) == '0';
}
{% endhighlight %}
