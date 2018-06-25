---
title: Word Search
tags: backtrack
---

## 问题
[leetcode link](https://leetcode.com/problems/word-search/description/)

## 总体思路
- backtrack
  - 选择: board上的位置
  - 限制: 当前的位置需要与word的下一个字母匹配
  - 结束: word完全匹配结束
  - 这个问题有个小特殊: 初始有m*n个下一步可以选择（而不是1个），而在选择一个后，以后就只有4个选择了

## 方案

{% highlight java %}
public boolean exist(char[][] board, String word) {
    char[] wordChar = word.toCharArray();
    int h = board.length, w = board[0].length;

    int[][] track = new int[h][];
    for(int i=0; i<h;i++){
        track[i] = new int[w];
        Arrays.fill(track[i], 0);
    }

    for (int i=0; i<h;i++){
        for(int j=0;j<w;j++){
            if (backtrack(board, track, i, j, wordChar, 0)){
                return true;
            }
        }
    }
    return false;
}

private boolean backtrack(char[][] board, int[][] track, int boardY, int boardX, char[] wordChar, int wordPos){
    if (board[boardY][boardX] != wordChar[wordPos]) return false;

    if (wordPos == wordChar.length-1) return true;

    int h = board.length, w = board[0].length;
    track[boardY][boardX] = 1;

    //left
    if (boardX > 0 && track[boardY][boardX-1] == 0){
        if (backtrack(board, track, boardY, boardX-1, wordChar, wordPos+1)) return true;
    }
    //right
    if (boardX < w-1 && track[boardY][boardX+1] == 0){
        if (backtrack(board, track, boardY, boardX+1, wordChar, wordPos+1)) return true;
    }

    //up
    if (boardY > 0 && track[boardY-1][boardX] == 0){
        if (backtrack(board, track, boardY-1, boardX, wordChar, wordPos+1)) return true;
    }

    //down
    if (boardY < h-1 && track[boardY+1][boardX] == 0){
        if (backtrack(board, track, boardY+1, boardX, wordChar, wordPos+1)) return true;
    }

    track[boardY][boardX] = 0;

    return false;
}
{% endhighlight %}
