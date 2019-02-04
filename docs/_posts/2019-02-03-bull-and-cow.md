---
title: Bulls and Cows
tags: Array
---

## 问题
[leetcode link](https://leetcode.com/problems/bulls-and-cows/)

## 总体思路
无

## 细节
- 数字重复
- 先排除掉exact match的情况

## 方案

{% highlight java %}
public String getHint(String secret, String guess) {
    int bull = 0, cow = 0;

    char[] secretArray = secret.toCharArray();
    char[] guessArray = guess.toCharArray();

    // create frequency array and initialize to 0
    int[] secretFreq = new int[10];
    Arrays.fill(secretFreq, 0);

    // count the frequency
    for (int i = 0; i < secretArray.length; i++) {
        secretFreq[secretArray[i] - '0']++;
    }

    // compute bull
    for (int i = 0; i < guessArray.length; i++) {
        // NOT out of boundary of secret array
        if (i < secretArray.length) {
            // exact match
            if (guessArray[i] == secretArray[i]) {
                bull++;
                // take one out
                secretFreq[guessArray[i] - '0']--;
            }
        } else {
            break;
        }

    }

    // compute cow
    for (int i = 0; i < guessArray.length; i++) {
        // NOT out of boundary of secret array
        if (i < secretArray.length) {
            // NOT exact match but same digit in wrong position
            if (guessArray[i] != secretArray[i] && secretFreq[guessArray[i] - '0'] > 0){
                cow++;
                // take one out
                secretFreq[guessArray[i] - '0']--;
            }
        } else {
            break;
        }
    }

    return bull+"A"+cow+"B";
}
{% endhighlight %}
