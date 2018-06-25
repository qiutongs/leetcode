---
title: Combinations
tags: backtrack recursion
---

## 问题
[leetcode link](https://leetcode.com/problems/combinations/description/)

## 总体思路
- 数学基础 C(n,m)=C(n-1,m-1)+C(n-1,m)
 -  C(n, 0) = 1 -> [ [] ] （not []）
- 方案1：直接迭代
- 方案2：backtrack
  - 选择: 是否选当前n为解集 (选 or 不选)
  - 限制: n 不能用完 < 1
  - 结束: k = 0  -> C(n, 0) = 1

## 方案1

{% highlight java %}
public List<List<Integer>> combine(int n, int k) {
    if (n < k){
        return new LinkedList<>(); // no resolution
    }

    if (k == 0){
        // one empty resolution
        return Collections.singletonList(new LinkedList<>());
    }

    List<List<Integer>> result = new LinkedList<>();

    List<List<Integer>> subResult1 = combine(n-1, k-1);
    List<List<Integer>> subResult2 = combine(n-1, k);

    for(List<Integer> oneSubResult1 : subResult1){
        oneSubResult1.add(n);
        result.add(oneSubResult1);
    }

    result.addAll(subResult2);

    return result;
}
{% endhighlight %}

## 方案2

{% highlight java %}
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new LinkedList<>();

    backtrack(result, new LinkedList<>(), n, k);

    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> curList, int n, int k){
    if (k == 0){
       // one empty resolution
        result.add(new LinkedList<>(curList));
        return;
    }

    if (n == 0){
       // no resolution
        return;
    }

    //select n
    curList.add(n);
    backtrack(result, curList, n-1, k-1);
    curList.remove(curList.size()-1);
    //not select n
    backtrack(result, curList, n-1, k);
}
{% endhighlight %}
