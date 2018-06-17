---
title: Simplify Path
tags: stack
---

## 问题
[leetcode link](https://leetcode.com/problems/simplify-path/description/)

## 总体思路
- 主要就是处理 "." "..", 其他的都是简单的对"/"的简化
- 显然用stack来pop后进的路径，当遇到".."
- 最后将stack的路径组合起来

## 方案1 我的代码
时间O(n), 空间O(n)

{% highlight java %}
public String simplifyPath(String path) {
    String[] directories = path.split("/");
    Stack<String> stack = new Stack<>();
    for (String directory : directories){
        if (directory.equals("..")){
            if (!stack.empty()){
                stack.pop();
            }
        }  else if (directory.equals(".") || directory.equals("")){

        } else {
            stack.push(directory);
        }
    }

    if (stack.empty()){
        return "/";
    } else {
        StringBuilder sb = new StringBuilder();
        while(!stack.empty()){
            sb.append(new StringBuilder(stack.pop()).reverse().toString());
            sb.append("/");
        }
        return sb.reverse().toString();
    }
}
{% endhighlight %}
