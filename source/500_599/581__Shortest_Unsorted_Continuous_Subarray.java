/*
 * Greedy?  Just compare the sorted one with unsorted
 */
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] sortedNums = nums.clone();
        
        Arrays.sort(sortedNums);
        
        int l = 0, r = nums.length - 1;
        
        while(l < nums.length) {
            if (nums[l] == sortedNums[l]) {
                l++;
            } else {
                break;
            }
        }
        
        while(r > l) {
            if (nums[r] == sortedNums[r]) {
                r--;
            } else {
                break;
            }
        }
        
        return r - l + 1;
    }
}