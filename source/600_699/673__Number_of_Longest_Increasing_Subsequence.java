/*
 * DP: variation of Longest Increasing Subsequence.
 * - add a count array
 * - sum up count array if it is equal to max LIS
 */
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] dp = new int[nums.length];
        int[] counts = new int[nums.length];
        int maxLIS = 1;
        
        Arrays.fill(dp, 1);
        Arrays.fill(counts, 1);
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        maxLIS = Math.max(maxLIS, dp[i]);
                        
                        counts[i] = counts[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }
        
        int ret = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == maxLIS) {
                ret += counts[i];
            }
        }
    
        return ret;
    }
}