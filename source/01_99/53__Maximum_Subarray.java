/*
Kadane's algorithm
*/
class Solution1 {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int maxEndHere = 0;

        for (int num : nums) {
            maxEndHere = maxEndHere > 0 ? maxEndHere + num : num;
            max = Math.max(max, maxEndHere);
        }
        return max;
    }
}

/* very similar to above, but the idea is more general:
use the current value only or include previous optimized value
*/
class Solution2 {
    public int maxSubArray(int[] nums) {
        int result = nums[0];
        int maxEndHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndHere = Math.max(nums[i], maxEndHere + nums[i]);
            result = Math.max(result, maxEndHere);
        }
        return result;
    }
}

/*
convert this problem to max difference problem
used additional prefix sum array.
*/
class Solution3 {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;

        int preSums[] = new int[nums.length];
        preSums[0] = nums[0];

        for (int i = 1; i < preSums.length; i++) {
            preSums[i] = preSums[i-1] + nums[i];
        }

        int curMin = 0;

        for (int i = 0; i < preSums.length; i++) {
            result = Math.max(result, preSums[i] - curMin);
            curMin = Math.min(curMin, preSums[i]);
        }

        return result;
    }
}

/*
same idea as above, save the space
*/
class Solution4 {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;

        int curSum = 0;
        int curMin = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum = curSum + nums[i];
            result = Math.max(result, curSum - curMin);
            curMin = Math.min(curMin, curSum);
        }

        return result;
    }
}
