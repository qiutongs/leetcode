---
title: Find All Duplicates in an Array
tags: Array
---

## 问题
[leetcode link](https://leetcode.com/problems/find-all-duplicates-in-an-array/description/)

## 总体思路
- 明显是不鼓励用hash table
- 因为有1 ≤ a[i] ≤ n的特殊条件，所以可以想特殊的办法: 利用a[i]对应的数组中的index(a[i] - 1)
  - 方案1: 将index对应的数字变为负数，表示已经存在了
  - 方案2: 将数字放在index对应的位置上, 如果位置上已经存在了这个数字，表示已经存在了
    -


## 方案1

{% highlight java %}
public List<Integer> findDuplicates(int[] nums) {
    List<Integer> result = new ArrayList<>();

    for (int num : nums){
        int pos = Math.abs(num)-1;
        if (nums[pos] < 0){
            result.add(Math.abs(num));
        } else {
            nums[pos] *= -1;
        }
    }

    return result;
}
{% endhighlight %}

## 方案2

{% highlight java %}
public List<Integer> findDuplicates(int[] nums) {
    List<Integer> result = new ArrayList<>();

    int i = 0;

    while(i < nums.length){
        int pos = nums[i] - 1;

        //happen to be on right position, move on
        if (i == pos){
            i++;
        } else {
            // the right position already has the number, it is the duplicate, move on
            if (nums[pos] == nums[i]){
                i++;
            } else {
                swap(nums, i, pos);
            }
        }
    }

    // cannot add number in above loop
    // it may add duplicate 
    for (i = 0; i < nums.length; i++){
        int pos = nums[i] - 1;
        if (i != pos){
            result.add(nums[i]);
        }
    }

    return result;
}

private void swap(int[] nums, int idx1, int idx2){
    int tmp = nums[idx1];
    nums[idx1] = nums[idx2];
    nums[idx2] = tmp;
}

{% endhighlight %}
