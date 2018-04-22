---
title: Exclusive Time of Functions
---

## 问题
[leetcode link](https://leetcode.com/problems/exclusive-time-of-functions/description/)

## 总体思路
- 相当于模拟了function的invoke和return。需要用caller func和callee func来计算出具体的时间。所以很容易想到stack

## 具体思路
- 分了四种情况来考虑
  - 当前看到start， 在caller func和当前func 中间不存在其他func: start1, start2
  - 当前看到start， 在caller func和当前func 中间存在其他func: start1, start2, end2, start3
  - 当前看到end， 在当前func的中间没有调用其他func: start1, end1
  - 当前看到end， 在当前func的中间有调用其他func: start1, start2, end2, end1


## 复杂度
时间O(n), 空间O(n)

## 我的代码
**要处理好same size的特殊情况**

{% highlight java %}
public int[] exclusiveTime(int n, List<String> logs) {
    int[] result = new int[n];
    Arrays.fill(result, 0);
    Stack<String> logStack = new Stack<>();

    Integer calleeEndTime = null;
    for (String log : logs){
        String[] logSplit = log.split(":");
        int funcId = Integer.valueOf(logSplit[0]);
        int funcTime = Integer.valueOf(logSplit[2]);

        if (logSplit[1].equals("start")){
            // caller exist
            if (!logStack.isEmpty()){
                String[] callerLogSplit = logStack.peek().split(":");
                int callerFuncId = Integer.valueOf(callerLogSplit[0]);
                int callerFuncTime = Integer.valueOf(callerLogSplit[2]);
                if (calleeEndTime == null){ // func1 += start2 - start1
                    result[callerFuncId] += funcTime - callerFuncTime;
                } else { // func1 += start3 - end2 - 1
                    result[callerFuncId] += funcTime - calleeEndTime - 1;
                    calleeEndTime = null;
                }
            }

            logStack.push(log);
        } else {
            String[] callerLogSplit = logStack.peek().split(":");
            int callerFuncId = Integer.valueOf(callerLogSplit[0]);
            int callerFuncTime = Integer.valueOf(callerLogSplit[2]);

            if (calleeEndTime == null){ // func2 += end2 - start2 + 1
                result[funcId] += funcTime - callerFuncTime + 1;
            } else { // func2 += end2 - end1
                result[funcId] += funcTime - calleeEndTime;
            }

            calleeEndTime = funcTime;
            logStack.pop();
        }
    }

    return result;
}
{% endhighlight %}
