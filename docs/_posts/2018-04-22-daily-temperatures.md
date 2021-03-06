---
title: Daily Temperatures
---

## 问题
[leetcode link](https://leetcode.com/problems/daily-temperatures/description/)

## 总体思路
- Brute force: 对于每个元素，向后寻找最符合条件的温度，时间O(n^2)
- 从左至右遍历输入，用stack来保存有可能找到但还未找到warmer temperature的元素，当看到新元素时，看看它是否可以成为stack中元素的warmer temperature。可以的话就stack pop。
  - stack中的元素应该是非递增的，否则小的元素应该早被pop了。
  - 非递增的排列使得用stack比较高效，因为只要找到不符合的就不用再往前看了
    - 假设不是从尾部向头部去找，而是从头部至尾部， 那就要需要遍历所有元素


## 复杂度
时间O(n), 空间O(n)

## 我的代码

{% highlight java %}
public int[] dailyTemperatures(int[] temperatures) {
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[temperatures.length];

    for (int i = 0; i < temperatures.length;i++){
        while(!stack.isEmpty()){
            int currentIndex = stack.peek();
            if (temperatures[currentIndex] < temperatures[i]){
                result[currentIndex] = i - currentIndex;
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(i);
    }

    while(!stack.isEmpty()){
        result[stack.pop()] = 0;
    }

    return result;
}
{% endhighlight %}
