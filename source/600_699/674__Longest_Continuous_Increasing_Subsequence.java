/*
 * DP: similar to max sum subarray
 */
class Solution1 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int ret = 1;
        int maxUpHere = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                maxUpHere++;
                ret = Math.max(ret, maxUpHere);
            } else {
                maxUpHere = 1;
            } 
        }
        
        return ret;
    }
}

/*
 * sliding window
 */
class Solution2 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int ret = 1;
        int l = 0;
        
        for (int r = 1; r < nums.length; r++) {
            if (nums[r] > nums[r - 1]) {
                ret = Math.max(ret, r - l + 1);
            } else {
                l = r;
            }
        }
        
        return ret;
    }
}