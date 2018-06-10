---
title: Next Greater Element II
---

## 问题
[leetcode link](https://leetcode.com/problems/next-greater-element-ii/description/)

## 总体思路
- 很类似 Next Greater Element I
- brute force: 需要O(n^2)时间
- 这个问题的变化就是circular, 对于在右边找不到NGE的元素，可以在左边从头找。所以就想到从左再找一遍。
  - 用stack来计算每个元素的next greater element
  - 第一遍查询：同Next Greater Element I
  - 第二遍查询：同上

## 复杂度
  - 时间O(n), 空间O(n)

## 我的代码

  {% highlight java %}
  public int[] nextGreaterElements(int[] nums) {
      int[] result = new int[nums.length];
      Arrays.fill(result, -1);

      Stack<Integer> numsIndexStack = new Stack<>();
      //first round scan
      for (int i=0; i<nums.length; i++){
          while(!numsIndexStack.isEmpty() && nums[numsIndexStack.peek()]<nums[i]){
              result[numsIndexStack.pop()] = nums[i];
          }
          numsIndexStack.push(i);
      }

      //second round scan
      for (int i=0; i<nums.length; i++){
          while(!numsIndexStack.isEmpty() && nums[numsIndexStack.peek()]<nums[i]){
              result[numsIndexStack.pop()] = nums[i];
          }
      }

      return result;
  }
  {% endhighlight %}
