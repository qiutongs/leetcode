---
title: Implement Stack using Queues
tags: Stack
---

## 问题
[leetcode link](https://leetcode.com/problems/implement-stack-using-queues/)

## 总体思路


## 方案1

{% highlight java %}
class MyStack {

    private Queue<Integer> mainQueue;
    private Queue<Integer> auxiliaryQueue;

    /** Initialize your data structure here. */
    public MyStack() {
        mainQueue = new LinkedList<>();
        auxiliaryQueue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        mainQueue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while(mainQueue.size() > 1) {
            auxiliaryQueue.add(mainQueue.remove());
        }

        int result = mainQueue.remove();

        swapQueue();

        return result;
    }

    /** Get the top element. */
    public int top() {
        while(mainQueue.size() > 1) {
            auxiliaryQueue.add(mainQueue.remove());
        }

        int result = mainQueue.remove();
        auxiliaryQueue.add(result);

        swapQueue();

        return result;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return mainQueue.isEmpty();
    }

    private void swapQueue() {
        Queue<Integer> tmp = mainQueue;
        mainQueue = auxiliaryQueue;
        auxiliaryQueue = tmp;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
{% endhighlight %}

## 方案2

{% highlight java %}
class MyStack {

    private Queue<Integer> mainQueue;
    private Queue<Integer> auxiliaryQueue;

    /** Initialize your data structure here. */
    public MyStack() {
        mainQueue = new LinkedList<>();
        auxiliaryQueue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        auxiliaryQueue.add(x);
        while(!mainQueue.isEmpty()) {
            auxiliaryQueue.add(mainQueue.remove());
        }
        swapQueue();
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return mainQueue.remove();
    }

    /** Get the top element. */
    public int top() {
        return mainQueue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return mainQueue.isEmpty();
    }

    private void swapQueue() {
        Queue<Integer> tmp = mainQueue;
        mainQueue = auxiliaryQueue;
        auxiliaryQueue = tmp;
    }
}

{% endhighlight %}

## Ref

https://www.geeksforgeeks.org/implement-stack-using-queue/
