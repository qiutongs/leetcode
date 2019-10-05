class Solution1 {
    public int[] searchRange(int[] nums, int target) {
        int[] ret = {-1, -1};
        
        if (nums == null || nums.length == 0) {
            return ret;
        }
        
        int firstIndex = binarySearch(nums, target, true);
        int lastIndex = binarySearch(nums, target, false);
        
        if (firstIndex < nums.length && nums[firstIndex] == target) {
            ret[0] = firstIndex;
        }
        
        if (lastIndex >= 0 && nums[lastIndex] == target) {
            ret[1] = lastIndex;
        }
        
        return ret;
    }
    
    private int binarySearch(int[] nums, int target, boolean leftMost) {
        int l = 0, r = nums.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (target > nums[mid]) {
                l = mid + 1;
            } else if (target < nums[mid]) {
                r = mid - 1;
            } else {
                if (leftMost) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        
        if (leftMost) {
           return l; // first >= target
        } else {
           return r; // last <= target
        }
    }
}

/*
- binary search
- binary search left most
- binary search right most
*/
class Solution2 {
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
