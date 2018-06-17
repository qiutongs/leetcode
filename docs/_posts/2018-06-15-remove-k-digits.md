---
title: Remove K Digits
tags: stack
---

## 问题
[leetcode link](https://leetcode.com/problems/remove-k-digits/description/)

## 总体思路
- Brute force: 解集是n个digit中选k个：C(n,k)
- 把最大的digit删除的想法不总是正确的， 比如1329中删1个，应该删3，而不是9
- 思路1：删了k个后，剩n-k个，每一位的位置都是有范围的。
  - 第i个数后面的位置要够选剩下n-k-i的数 n-P(i)>=n-k-i
  - P(i-1) + 1 <= P(i) <= k+i
  - 剩下的第一位在[1,k+1]，第二位在[第一位+1, k+2]
  - 时间复杂度是k(n-k) -> O(nk) : 如果k远小于n，这个方案近似于O(n)
- 思路2：局部的最优决定了全局最优。从左向右看相邻的两个digit, [D1, D2]。如果D1 > D2, 那删掉D1一定是对的；
如果D1 <= D2，那就要往后看
  - 用stack来保存可能要被删掉的digit，根据上面的算法可以看出来后进的digit会先被删掉，直到删到k个为止
  - stack中的元素应该是非递减排列。如果到最后还有没删掉的，就从后往前删
  - 时间复杂度O(n)
- 有个额外的需求是要trim leading 0。 找到first Index Not Zero

## 方案1 我的代码
时间O(nk), 空间O(n)

{% highlight java %}
public String removeKdigits(String num, int k) {
    // result is n-k length
    char[] resultChar = new char[num.length()-k];
    int previousIndex = -1;
    int fisrtIndexNotZero = -1;
    for (int i=0;i<resultChar.length;i++){
        //[previousIndex + 1, k+i]
        int minIndex = getMinValueIndex(num, previousIndex+1, k+i);
        resultChar[i] = num.charAt(minIndex);
        previousIndex = minIndex;
        if (resultChar[i]!='0' && fisrtIndexNotZero==-1){
            fisrtIndexNotZero = i;
        }
    }
    //nothing left or all digits left is 0
    if (fisrtIndexNotZero == -1){
        return "0";
    }
    return new String(resultChar, fisrtIndexNotZero, resultChar.length-fisrtIndexNotZero);
}
private int getMinValueIndex(String s, int start, int end){
    int resultIndex = start;
    while(start<end){
        start++;
        if (s.charAt(start) < s.charAt(resultIndex)){
            resultIndex = start;
        }
    }
    return resultIndex;
}
{% endhighlight %}

## 方案2 我的代码
时间O(n), 空间O(n)

{% highlight java %}
public String removeKdigits(String num, int k) {
    Stack<Integer> digitStack = new Stack<>();
    char[] numCharArray = num.toCharArray();

    for (int i = 0;i < num.length(); i++){
        Integer currentDigit = numCharArray[i] - '0';
        while (k > 0 && !digitStack.isEmpty() && digitStack.peek() > currentDigit){
            digitStack.pop();
            k--;
        }
        digitStack.push(currentDigit);
    }

    if (digitStack.isEmpty()){
        return "0";
    } else {
        return getResultStr(digitStack);
    }
}

private String getResultStr(Stack<Integer> digitStack){
    char[] resultCharArray = new char[digitStack.size()];
    //this also covers an edge case: all digits are zero
    int firstIndexNotZero = resultCharArray.length-1;

    for (int i = resultCharArray.length-1;i>=0;i--){
        resultCharArray[i] = (char)('0' + digitStack.pop());

        if (resultCharArray[i] != '0'){
            firstIndexNotZero = i;
        }
    }

    return new String(resultCharArray, firstIndexNotZero, resultCharArray.length - firstIndexNotZero);
}
{% endhighlight %}
