/*
The difference from question 1 is the case that middle == high

In this case, we have to do a linear scan. The worst time is O(n)
*/
class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while(low <= high) {
            int mid = (low + high) >> 1;

            if (target == nums[mid]) {
                return true;
            }

            if (nums[mid] > nums[high]) {
                if (target < nums[mid] && target > nums[high]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] < nums[high]) {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                  for (int i = low; i <= high; i++ ) {
                      if (nums[i] == target) {
                          return true;
                      }
                  }
                  break;
            }
        }

        return false;
    }
}
