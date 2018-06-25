---
title: Permutations
tags: recursion
---

## 问题
[leetcode link](https://leetcode.com/problems/permutations/description/)

## 总体思路
- 直接recursion， 找到n-1时候的解，然后把当前的数字逐位插入的n-1的解集中，得到最终解
- 可以backtrack， TODO：还没实现

## 方案1
public List<List<Integer>> permute(int[] nums) {
    if (nums.length == 0) return Collections.emptyList();

    if (nums.length == 1){
        List<Integer> singleItemList = new LinkedList<>();
        singleItemList.add(nums[0]);
        List<List<Integer>> singleItemListResult = new LinkedList<>();
        singleItemListResult.add(singleItemList);
        return singleItemListResult;
    }

    int[] subNums = Arrays.copyOfRange(nums, 1, nums.length);
    List<List<Integer>> result = new LinkedList<>();
    int e = nums[0];

    List<List<Integer>> subResults = permute(subNums);
    for (List<Integer> subResult : subResults){
        for (int i = 0; i <= subResult.size(); i++){
            List<Integer> subResultCopy = new LinkedList<>(subResult);
            subResultCopy.add(i, e);
            result.add(subResultCopy);
        }

    }

    return result;
}
{% endhighlight %}
