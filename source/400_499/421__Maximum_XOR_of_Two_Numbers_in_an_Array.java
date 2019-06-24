class Solution {
    public int findMaximumXOR(int[] nums) {
        BitTrieNode bitTrieRoot = new BitTrieNode();
        int result = 0;
        
        for (int num : nums) {
            add(bitTrieRoot, num);
        }
        
        for (int num : nums) {
            result = Math.max(result, num ^ getMaxMatch(bitTrieRoot, num));
        }
        
        return result;
    }
    
    private void add(BitTrieNode root, int val) {
        BitTrieNode curTrieNode = root;
        int originalVal = val;
        
        int i = 32;
        while(i > 0) {
            int highestDigit = val >>> 31;
            val <<= 1;
            
            if (highestDigit == 0) {
                if (curTrieNode.left == null) {
                    curTrieNode.left = new BitTrieNode();
                }
                
                curTrieNode = curTrieNode.left;
            } else {
                if (curTrieNode.right == null) {
                    curTrieNode.right = new BitTrieNode();
                }
                
                curTrieNode = curTrieNode.right;
            }
            
            i--;
        }
        
        curTrieNode.val = originalVal;
    }
    
    private int getMaxMatch(BitTrieNode root, int val) {
        BitTrieNode curTrieNode = root;
        
        int i = 32;
        while(i > 0) {
            int highestDigit = val >>> 31;
            val <<= 1;

            if (highestDigit == 0) {
                curTrieNode = curTrieNode.right != null ? curTrieNode.right : curTrieNode.left;
            } else {
                curTrieNode = curTrieNode.left != null ? curTrieNode.left : curTrieNode.right;
            }
            
            i--;
        }
        
        return curTrieNode.val;
    }
    
    private class BitTrieNode {
        BitTrieNode left = null;
        BitTrieNode right = null;
        Integer val = null;
    }
}