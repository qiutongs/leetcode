/*
- 2-way partitoon -> three regions:
  * region1 <= lastNotDup: no duplicates region
  * > lastNotDup < region2 < i: duplicates region
  * >= i: unexplored region
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int lastNotDup = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[lastNotDup]) {
                lastNotDup++;
                nums[lastNotDup] = nums[i];
            }
        }
        return lastNotDup + 1;
    }
}
