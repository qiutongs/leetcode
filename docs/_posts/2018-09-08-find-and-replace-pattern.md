---
title: Find and Replace Pattern
tags: String
---

## 问题
[leetcode link](https://leetcode.com/problems/palindrome-number/description/)

## 总体思路
- bijection: one-to-one mapping

## 感想

## 方案

{% highlight c %}
char** findAndReplacePattern(char** words, int wordsSize, char* pattern, int* returnSize) {
    char** result = malloc(wordsSize * sizeof(char*));
    int size = 0;

    for (int i = 0; i < wordsSize; i++){
        char* word = words[i];
        if (isMatch(pattern, word)){
            result[size++] = word;
        }
    }

    *returnSize = size;
    return result;
}

// patten: a - z
int isMatch(char* pattern, const char* word){
    char p_to_wmap[26] = {0};
    char w_to_pmap[26] = {0};

    char *p = pattern, *w = word;
    while(*p != '\0' && *w != '\0'){
        if (p_to_wmap[*p-'a'] == 0 && w_to_pmap[*w-'a'] == 0){
            p_to_wmap[*p-'a'] = *w;
            w_to_pmap[*w-'a'] = *p;
        } else if (p_to_wmap[*p-'a'] == *w && w_to_pmap[*w-'a'] == *p){
            //happy case
        } else {
            break;
        }

        p++;
        w++;
    }

    return *p == '\0' && *w == '\0' ? 1 : 0;
}
{% endhighlight %}
