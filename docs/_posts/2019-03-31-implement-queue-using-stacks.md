---
title: Implement Queue using Stacks
tags: Stack
---

## 问题
[leetcode link](https://leetcode.com/problems/implement-queue-using-stacks/)

## 总体思路
- 两个stack
- 一个stack

## 方案1

{% highlight java %}
class MyQueue {

    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        pushStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (popStack.isEmpty()) {
            moveFromPushToPop();
        }

        return popStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (popStack.isEmpty()) {
            moveFromPushToPop();
        }

        return popStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return popStack.isEmpty() && pushStack.isEmpty();
    }

    private void moveFromPushToPop() {
        while(!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
{% endhighlight %}

## 方案2

{% highlight java %}
class MyQueue {

    private Stack<Integer> pushStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        pushStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        pushStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (pushStack.size() == 1) {
            return pushStack.pop();
        } else {
            int current = pushStack.pop();
            int result = pop();
            pushStack.push(current);
            return result;
        }
    }

    /** Get the front element. */
    public int peek() {
        if (pushStack.size() == 1) {
            return pushStack.peek();
        } else {
            int current = pushStack.pop();
            int result = peek();
            pushStack.push(current);
            return result;
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return pushStack.isEmpty();
    }
}

{% endhighlight %}

## Ref

https://www.geeksforgeeks.org/queue-using-stacks/
