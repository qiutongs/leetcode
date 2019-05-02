/*
Sliding window algorithm
*/
class Solution {
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
