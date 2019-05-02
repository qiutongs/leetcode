/*
In regular binary search, we only care the relation between target and middle value.
In this problem, we care the relation among target, middle value and last value.

It will break down to two logic branch:

// Pivot is between middle and last
if (middle > last) {
    check where target falls into
} else { // Pivot is between first and middle
    check where target falls into
}
*/
class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while(low <= high) {
            int mid = (low + high) >> 1;

            if (target == nums[mid]) {
                return mid;
            }

            if (nums[mid] > nums[high]) {
                if (target < nums[mid] && target > nums[high]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}
