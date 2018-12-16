---
title: Task Scheduler
tags: Greedy
---

## 问题
[leetcode link](https://leetcode.com/problems/task-scheduler/description/)

## 总体思路
- 把相同的task尽量分散开
- 出现最多的task将是瓶颈
  - 把最多的task分散开后，剩下的task插入中间
- edge case：可能cooling interval不是瓶颈，task number才是   

## 感想

## 方案

{% highlight c %}
int leastInterval(char* tasks, int tasksSize, int n) {
    int count_table[26] = {0};
    int i;
    for (i = 0;i < tasksSize;i++){
        char task = tasks[i];
        count_table[task - 'A']++;
    }
    
    int max_count = 0;
    for (i = 0;i < 26;i++){
        if (count_table[i] > max_count) max_count = count_table[i];
    }

    int max_task_count = 0;
    for (i = 0;i < 26;i++){
        if (count_table[i] == max_count) max_task_count++;
    }

    int result = (max_count - 1) * (n + 1) + max_task_count;
    return result > tasksSize? result : tasksSize;

}
{% endhighlight %}
