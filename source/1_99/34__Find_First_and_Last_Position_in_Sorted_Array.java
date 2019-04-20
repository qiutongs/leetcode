/*
- binary search
- binary search left most
- binary search right most
*/
class Solution {
    enum Mode {
        SEARCH,
        LEFT_BOUND,
        RIGHT_BOUND
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];

        if (search(nums, target, Mode.SEARCH) == -1) {
            result[0] = -1;
            result[1] = -1;
        } else {
            result[0] = search(nums, target, Mode.LEFT_BOUND);
            result[1] = search(nums, target, Mode.RIGHT_BOUND);
        }
        return result;
    }

    private int search(int[] nums, int target, Mode mode) {
        int low = 0, high = nums.length - 1;

        while(low <= high) {
            int mid = (low + high) >> 1;
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                switch(mode) {
                    case SEARCH: return mid;
                    case LEFT_BOUND: high = mid - 1; break;
                    case RIGHT_BOUND: low = mid + 1; break;
                }
            }
        }

        switch(mode) {
            case SEARCH: return -1;
            case LEFT_BOUND: return low;
            case RIGHT_BOUND: return high;
        }

        throw new RuntimeException();
    }
}
