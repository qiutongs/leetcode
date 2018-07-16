---
title: Array Partition I
tags: Array
---

## 问题
[leetcode link](https://leetcode.com/problems/array-partition-i/description/)

## 总体思路
- brute force: 肯定是低效
- 观察发现 1, 2, 3, 4 时最优解是 (1, 2) （3， 4）
  - 推广结论: 最优解是排序后 (a1, a2), (a3, a4), .... (a2n-1, a2n)
  - 我没有严格的数学证明。。。(shame)
- 题目非要提示数组的大小最多是10000，数字的大小[-10000, 10000]，明显诱惑你用O(n)的sort
  - 我这里用了[counting sort](https://www.geeksforgeeks.org/counting-sort/)
  - 我认为counting sort是bucket sort的特殊情况: bucket为单值
  - 我认为这里数组大小不重要，只要数字的大小在接受范围内就好


## 方案1
时间O(nLog(n)), 空间O(1)

{% highlight java %}
public int arrayPairSum(int[] nums) {
    Arrays.sort(nums);
    int sum = 0;
    for (int i = 0; i<nums.length;i++){
        if (i%2 == 0){
           sum += nums[i];
        }
    }
    return sum;
}
{% endhighlight %}

## 方案2
时间O(nLog(n)), 空间O(1)

{% highlight java %}
public int arrayPairSum(int[] nums) {
    countSort(nums);
    int sum = 0;
    for (int i = 0; i<nums.length;i++){
    if (i%2 == 0){
       sum += nums[i];
    }
    }
    return sum;

}

private void countSort(int[] nums){
    int[] count = new int[20001];
    Arrays.fill(count, 0);

    //step 1: store the count of each element
    for (int num : nums){
        count[getPosInCount(num)]++;
    }

    //step 2: modify the count so that it stores the sum of previous count
    // and it indicates the position in the sorted array
    for (int i = 1;i < count.length; i++){
        count[i] += count[i-1];
    }

    //step 3: build the result: find the position stored in count and decrease by 1
    int[] result = new int[nums.length];
    for (int num : nums){
        int pos = count[getPosInCount(num)] - 1;
        result[pos] = num;
        count[getPosInCount(num)]--;
    }

    System.arraycopy(result, 0, nums, 0, nums.length);
}

private int getPosInCount(int num){
    return num + 10000;
}

{% endhighlight %}
