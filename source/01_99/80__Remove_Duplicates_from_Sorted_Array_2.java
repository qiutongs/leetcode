/*
Similar to question 1. The additional logic is to handle twice values.
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int lastNotDup = 0;
        boolean twice = false;

        for (int i = 1; i < nums.length; i++) {
            if ( nums[i] != nums[lastNotDup] ) {
                /* If found a new value. */

                lastNotDup++;
                nums[lastNotDup] = nums[i];
                twice = false;
            } else if ( !twice && (nums[i] == nums[lastNotDup]) ) {
                /* If found an old value but only apreared once. */

                lastNotDup++;
                nums[lastNotDup] = nums[i];
                twice = true;
            }
        }

        return lastNotDup + 1;
    }
}
