/*
Sliding window algorithm
*/
class Solution1 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int windowLeft = 0, windowRight = 0;
        int curSum = nums[0];
        int result = Integer.MAX_VALUE;

        while(true) {
            if (curSum < s) {
                windowRight++;

                if (windowRight == nums.length) {
                    break;
                }

                curSum += nums[windowRight];
            } else {
                while(curSum >= s && windowLeft <= windowRight) {
                    curSum -= nums[windowLeft];
                    windowLeft++;
                }

                result = Math.min(result, windowRight - windowLeft + 2);
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

/* Simplified sliding window algorithm
 */
class Solution2 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int ret = Integer.MAX_VALUE;
        
        int l = 0, sum = 0;
        
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            
            while(l <= r && sum >= s) {
                ret = Math.min(ret, r - l + 1);
                
                sum -= nums[l];
                l++;
            }
        }
        
        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}