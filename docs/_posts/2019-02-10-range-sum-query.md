---
title: Range Sum Query - Mutable
tags: SegmentTree
---

## 问题
[leetcode link](https://leetcode.com/problems/range-sum-query-mutable/)

## 总体思路
- 这道题就是告诉你Prefix sum array不适用
- 然后引导你寻找对于query/update都是Log(n)的方法
  - segment tree

## 方案

{% highlight java %}
class NumArray {

    private final SegmentTree segmentTree;

    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            segmentTree = null;
            return;
        }

        segmentTree = new SegmentTree(nums);
    }

    public void update(int i, int val) {
        segmentTree.update(i, val);
    }

    public int sumRange(int i, int j) {
        return segmentTree.query(i, j);
    }
}

class SegmentTree {
    // Each element represents a node in segment tree:
    // - index represents the position
    // - value represents the sum of segment i-j of original array
    private final Integer[] nodes;

    // Track original array to calculate the diff in update method
    private final int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;

        // Elements in nums will be leaves in the segement tree
        // Therefore, rounding up to nearest power of 2 will guarantee enough space
        nodes = new Integer[nextPowerOfTwo(nums.length) * 2];
        Arrays.fill(nodes, null);

        build(0, nums, 0, nums.length-1);
    }

    public int query(int start, int end) {
        return query(0, 0, nums.length - 1, start, end);
    }

    public void update(int i, int val) {
        update(0, 0, nums.length - 1, i, val - nums[i]);
        // Also update the actual array to calculate the diff properly next time
        nums[i] = val;
    }

    // Build current node - *index*, representing the sum of *nums* from *s* to *e*
    // This takes O(n) time. T(n) = 2 * T(n/2)
    private int build(int index, int[] nums, int s, int e) {
        // Termination case: this is a leaf
        if (s == e) {
            nodes[index] = nums[s];
            return nodes[index];
        }

        int mid = getMid(s, e);
        nodes[index] = build(leftChild(index), nums, s, mid)
                     + build(rightChild(index), nums, mid+1, e);
        return nodes[index];
    }

    // query the node - *index* which represents the sum of array from *numStart* to *numEnd*
    // , with range from *qStart* to *qEnd*,
    // This takes O(log(n)) time. In each level of segment tree, it visits at most 4 nodes.
    private int query(int index, int numStart, int numEnd, int qStart, int qEnd) {
        // node is within the query range, simply return
        if (qStart <= numStart && qEnd >= numEnd) {
            return nodes[index];
        }

        // node is completely outside the query range, simply return 0
        if (qStart > numEnd || qEnd < numStart) {
            return 0;
        }

        // node is overlap with query range: query the left half and right half
        int mid = getMid(numStart, numEnd);
        return query(leftChild(index), numStart, mid, qStart, qEnd)
             + query(rightChild(index), mid+1, numEnd, qStart, qEnd);

    }

    // update the node - *index* which represents the sum of array from *numStart* to *numEnd*
    // , with the *diff* if *i* is within range
    private void update(int index, int numStart, int numEnd, int i, int diff) {
        // i is out of range, simply return
        if (i < numStart || i > numEnd) {
            return;
        }

        // i is within range, update the node first
        nodes[index] += diff;

        // this is a leaf, simply return
        if (numStart == numEnd) {
            return;
        }

        // found where i fits: left or right
        int mid = getMid(numStart, numEnd);

        if (i <= mid) {
            update(leftChild(index), numStart, mid, i, diff);
        } else {
            update(rightChild(index), mid+1, numEnd, i, diff);
        }
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // This is critial to divide the array to halves
    private int getMid(int s, int e) {
        return (s + e) / 2;
    }

    // Bit trick to round up a number to nearest power or 2
    private int nextPowerOfTwo(int n) {
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;
        return n;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
{% endhighlight %}

## Ref

https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
