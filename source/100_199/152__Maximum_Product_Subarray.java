/*
Track max/min so far.
use the current value only or include previous optimized value
*/
class Solution1 {
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int maxEndHere = nums[0], minEndHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                maxEndHere = Math.max(nums[i], maxEndHere * nums[i]);
                minEndHere = Math.min(nums[i], minEndHere * nums[i]);
            } else {
                int tmpMax = Math.max(nums[i], minEndHere * nums[i]);
                minEndHere = Math.min(nums[i], maxEndHere * nums[i]);
                maxEndHere = tmpMax;
            }
            result = Math.max(result, maxEndHere);
        }

        return result;
    }
}

/*
Similar to above, but here it tracks max positive and min negative
it must have a "null" state to indicate there is none!
More complicated than above. Not good.
*/
class Solution2 {
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        Integer maxPosiveEndHere = null, minNegtiveEndHere = null;

        for (int num : nums) {
            if (num > 0) {
                Integer tmpMax = maxPosiveEndHere == null ? num : maxPosiveEndHere * num;
                minNegtiveEndHere = minNegtiveEndHere == null ? null : minNegtiveEndHere * num;
                maxPosiveEndHere = tmpMax;
            } else if (num < 0) {
                Integer tmpMax = minNegtiveEndHere == null ? null : minNegtiveEndHere * num;
                minNegtiveEndHere = maxPosiveEndHere == null ? num : maxPosiveEndHere * num;
                maxPosiveEndHere = tmpMax;
            } else {
                maxPosiveEndHere = null;
                minNegtiveEndHere = null;
                result = Math.max(result, 0);
            }

            if (maxPosiveEndHere != null) {
                result = Math.max(result, maxPosiveEndHere);
            } else if (minNegtiveEndHere != null) {
                result = Math.max(result, minNegtiveEndHere);
            }
        }

        return result;
    }
}
