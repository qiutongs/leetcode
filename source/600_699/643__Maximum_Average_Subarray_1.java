class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0;
        }

        int sum = 0;
        
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        int ret = sum;
        
        for (int i = 0; i < nums.length - k; i++) {
            sum -= nums[i];
            sum += nums[i + k];
            
            ret = Math.max(ret, sum);
        }
        
        return (double)ret / (double)k;
    }
}