---
title: Next Greater Element I
---

## 问题
[leetcode link](https://leetcode.com/problems/next-greater-element-i/description/)

## 总体思路
- brute force: 需要O(n^2)时间，应该不是这道题的目的
- 感觉排序应该不试用O(nLogN)，因为nums2的顺序不能改变
- 这个问题的大问题是找出nums2中每个元素的next greater element。这个问题和daily-temperature类似
  - 用stack来计算每个元素的next greater element
  - 用hash map来储存，用来给nums1查询用

## 复杂度
  - 时间O(n), 空间O(n)

## 我的代码

  {% highlight java %}
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      HashMap<Integer, Integer> ngeHashMap = new HashMap<>();
      Stack<Integer> nums2Stack = new Stack<>();

      for (int i2 : nums2){
          while (!nums2Stack.isEmpty() && i2 > nums2Stack.peek()){
              ngeHashMap.put(nums2Stack.pop(), i2);
          }

          nums2Stack.push(i2);
      }

      int[] result = new int[nums1.length];
      for (int i = 0;i < nums1.length; i++){
          Integer ngeValue = ngeHashMap.get(nums1[i]);
          result[i] = ngeValue == null ? -1 : ngeValue;
      }
      return result;
  }
  {% endhighlight %}
