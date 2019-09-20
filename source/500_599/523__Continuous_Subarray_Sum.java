/*
 * Prefix sum + mod.

 * Crazy detail:
 * - k = 0, k < 0
 * - cannot create array with size k for mod. K might be too big
 */
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        
        // Edge case, only true if there is two adjacent 0
        if (k == 0) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == 0 && nums[i + 1] == 0) {
                    return true;
                }
            }
            return false;
        }
        
        // flip k if it is negative
        if (k < 0) {
            k = -k;
        }
        
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        HashMap<Integer, Integer> modIndexMap = new HashMap<>();
        
        for (int i = 0; i < prefixSum.length; i++) {
            int remainder = prefixSum[i] % k;
            
            Integer curIndex = modIndexMap.get(remainder);
            if (curIndex == null) {
                modIndexMap.put(remainder, i);
            } else {
                // The problem says at least 2...
                if (i - curIndex >= 2) {
                    return true;
                }
            }
        }
        
        return false;
    }
}