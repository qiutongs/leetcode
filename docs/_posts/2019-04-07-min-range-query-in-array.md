---
title: Min Range Query in Array
tags: SegmentTree
---

## Problem
[leetcode link](https://www.geeksforgeeks.org/min-max-range-queries-array/)

## High Level Idea

- BF will take O(n) in each query.
- A straightforward optimization is to store every possibility to a "lookup" table, thus O(1) time and O(n^2) space.
  - But if taking "update" into account, above method is arguable.
- Like other cases, if we need a balance between query and update, it is tree.
  - Segment Tree is designed to retrieve information of "range" - the segment


## Ref

- https://www.geeksforgeeks.org/min-max-range-queries-array/
- https://www.geeksforgeeks.org/range-minimum-query-for-static-array/
