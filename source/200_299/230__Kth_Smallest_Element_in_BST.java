/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
 * Time: O(n)
 * Space: O(height) 
 */
class Solution
{
    private int pos = 0;

    public int kthSmallest(TreeNode root, int k)
    {
        int[] result = new int[1];

        // Assumes k is valid, this always returns true
        inOrderTraverse(root, k, result);

        return result[0];
    }

    private boolean inOrderTraverse(TreeNode node, int k, int[] result)
    {
        if (node == null)
        {
            return false;
        }

        // if found kth element in left sub-tree, no further tranversal
        if (inOrderTraverse(node.left, k, result))
        {
            return true;
        }

        pos++;

        // if this is the kth element, no further tranversal
        if (pos == k)
        {
            result[0] = node.val;
            return true;
        }

        // return whatever result in right sub-tree
        return inOrderTraverse(node.right, k, result);
    }
}