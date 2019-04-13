---
title: Replace Words
tags: Trie
---

## 问题
[leetcode link](https://leetcode.com/problems/replace-words/)

## 总体思路
- Prefix search, 我首先想到了Trie，一个可以用O(L)查询字符串的数据结构：L 为字符串长度

## 方案
时间O(sentence)

{% highlight java %}
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        //Create a trie tree
        Trie trie = new Trie();

        //Insert word to the trie tree
        for (String word : dict) {
            trie.insert(word);
        }

        // Trim and split the sentence into words
        String[] words = sentence.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();

        // Attempt to find the root by prefix matching
        for (String word : words) {
            String prefixStr = trie.prefixSearch(word);
            sb.append(prefixStr == null ? word : prefixStr);
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}

class Trie {
    static final int ALPHABET_SIZE = 26;

    private Node root;

    Trie() {
        // Initialize the root
        root = new Node();
    }

    public void insert(String key) {
        char[] keyCharArray = key.toCharArray();
        Node curNode = root;

        for (char cKey: keyCharArray) {
            // If not found the child with the specified character
            if (curNode.getChild(cKey) == null) {
                // Create a new hild and set child to current node
                Node newChildNode = new Node();
                curNode.setChild(cKey, newChildNode);
                // Point to the new node
                curNode = newChildNode;
            } else {
                // Point to the found child node
                curNode = curNode.getChild(cKey);
            }
        }

        curNode.hasValue = true;
    }

    public String prefixSearch(String key) {
        char[] keyCharArray = key.toCharArray();
        Node curNode = root;

        for (int i = 0 ;i < keyCharArray.length; i++) {
            char cKey = keyCharArray[i];
            Node child = curNode.getChild(cKey);

            if (child == null) {
                return null;
            } else {
                if (child.hasValue) {
                    return key.substring(0, i+1);
                }

                curNode = child;
            }
        }

        return null;
    }

    private class Node {
        Node[] children;
        boolean hasValue;

        Node() {
            children = new Node[ALPHABET_SIZE];
            Arrays.fill(children, null);
            hasValue = false;
        }

        Node getChild(char c) {
            return children[c - 'a'];
        }

        void setChild(char c, Node child) {
            children[c - 'a'] = child;
        }
    }
}
{% endhighlight %}
