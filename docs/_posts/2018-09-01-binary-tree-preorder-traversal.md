---
title: Binary Tree Preorder Traversal
tags: Tree
---

## 问题
[leetcode link](https://leetcode.com/problems/binary-tree-preorder-traversal/description/)

## 总体思路


## 方案

{% highlight c %}

static const int initial_capacity = 128;

typedef struct {
    int* array;
    int size;
    int capacity;
} vector;

void vector_init(vector* v);
//void vector_free(vector* v);
void vector_add(vector* v, int val);

int* preorderTraversal(struct TreeNode* root, int* returnSize) {
    vector v;
    vector_init(&v);
    preorder(root, &v);
    *returnSize = v.size;
    return v.array;
}

void preorder(struct TreeNode* current, vector* v){
    if (current == NULL) return;

    vector_add(v, current->val);
    preorder(current->left, v);
    preorder(current->right, v);
}

// vector implementation

void vector_init(vector* v){
    v->array = malloc(initial_capacity * sizeof(int));
    v->capacity = initial_capacity;
    v->size = 0;
}


void vector_add(vector* v, int val){
    if (v->size == v->capacity){
        int* narray = realloc(v->array, v->capacity * 2 * sizeof(int));
        if (narray) v->array = narray;
    }
    v->array[v->size] = val;
    (v->size)++;
}

{% endhighlight %}
