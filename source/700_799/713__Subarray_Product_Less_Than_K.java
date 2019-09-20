/*
 * Positive numbers: sliding window
   https://segmentfault.com/a/1190000019748859
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ret = 0;
        
        int l = 0, product = 1;
        for (int r = 0; r < nums.length; r++) {
            product *= nums[r];
            
            while( l <= r && product >= k ) {
                product /= nums[l];
                l++;
            }
            
            ret += r - l + 1;
        }
        
        return ret;
    }
}