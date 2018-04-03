---
layout: post
title: Rotate Image
---

# 问题
[leetcode link](https://leetcode.com/problems/rotate-image/description/)

# 总体思路
- 非常直白的一个问题，时间上没有什么可以优化的, 一定是O(n)。
- 题目中特意提出要O(1)的空间来解决。也就是说不能创建一个新的matrix
- 需要把每个元素移动到正确的位置，我觉得考察是对数组的manipulation

# 实现思路
- 从4个角开始考虑。既然要O(1)，很容易想到 tmp = 角1，角1 = 角4，角4 = 角3，角3 = 角2，角2 = tmp 
- 用一层一层的视角看待matrix, 迭代的把每一层给旋转好，3 x 3 有2层， 4 x 4 有2层， 5 x 5 有3层 ......  
    - 所以 rotate method很简单，迭代的调用rotateIteration，传入迭代的次数，同时代表深度depth（假设从外向里）
- rotateIteration 每次迭代要处理4条边，每条边上有多个点要处理，所以要对每条边上的点做循环。比较易读的写法是算出起始位置和终点位置，然后用 'offset’ - 偏离起始点的距离，为循环变量
    - 第一次是[0, n-1], offset = 0, 1, .... n-1
    - 第二次是[1, n-2], offset = 0, 1, .... n-3
    - 第三次是[2, n-3], offset = 0, 1, .... n-5

# 我的代码

{% highlight java %}
{% github_sample qiutongs/leetcode/blob/master/RotateImage.java %}
{% endhighlight %}