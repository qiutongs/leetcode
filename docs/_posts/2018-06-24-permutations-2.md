---
title: Permutations II
tags: backtrack
---

## 问题
[leetcode link](https://leetcode.com/problems/permutations-ii/description/)

## 总体思路
- 和前一题相比, 不同的是unique, 其实只要用hash set可以无差别解决, 但这里我换了backtrack，也没用set
- backtrack
  - 选择：选择下一个数字放到解集里
  - 限制: 如果重复就不放了
    - 先排序，然后在选择时，对同样的数字，只选择一次，其他的跳过
  - 结束：当前解已经有所有数字了


## 方案
{% highlight java %}
public List<List<Integer>> permuteUnique(int[] nums) {
    Arrays.sort(nums);

    int[] lookupTable = new int[nums.length];
    Arrays.fill(lookupTable, 0);

    List<List<Integer>> result = new LinkedList<>();
    backtrack(result, new LinkedList<>(), nums, lookupTable);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> currentList, int[] nums, int[] lookupTable){
    if (currentList.size() == nums.length){
        result.add(new LinkedList(currentList));
        return;
    }

    Integer previousValue = null;
    for (int i = 0;i < lookupTable.length;i++){
        if (lookupTable[i] == 0){
            //start - handle duplicate
            if (previousValue != null && nums[i] == previousValue){
                continue;
            }
            previousValue = nums[i];
            //end - handle duplicate

            lookupTable[i] = 1;
            currentList.add(nums[i]);
            backtrack(result, currentList, nums, lookupTable);
            lookupTable[i] = 0;
            currentList.remove(currentList.size()-1); //remove last element
        }
    }
}
{% endhighlight %}
