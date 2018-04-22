---
title: Asteroid Collision
---

## 问题
[leetcode link](https://leetcode.com/problems/asteroid-collision/description/)

## 总体思路
- 比较直白的问题，从左向右，后进的因为碰撞会先出去。自然地想到stack

## 复杂度
时间O(n), 空间O(n)

## 我的代码
**要处理好same size的特殊情况**

{% highlight java %}
public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> astStack = new Stack<>();
    for (int asteroid : asteroids){   
        boolean sameSizeCollide = false;
        while(!astStack.isEmpty()){
            // if they will collide
            if (astStack.peek() >= 0 && asteroid < 0){
                Integer leftAst = astStack.pop();
                if (leftAst.intValue() != -asteroid){
                    asteroid = (leftAst > -asteroid)? leftAst : asteroid;
                } else {
                    sameSizeCollide = true;
                    break;
                }
            } else {
                break;
            }
        }

        if (!sameSizeCollide){
             astStack.push(asteroid);
        }
    }

    int[] result = new int[astStack.size()];
    for (int i = result.length-1 ; i >= 0 ; i--){
        result[i] = astStack.pop();
    }
    return result;
}
{% endhighlight %}
