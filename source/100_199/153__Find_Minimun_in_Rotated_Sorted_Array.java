class Solution1 {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        int lastNum = nums[r];
        
        // find first element <= lastNum
        while(l <= r) {
            int mid = (r + l)/2;
            
            if (nums[mid] <= lastNum) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return nums[l];
    }
}

class Solution2 {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        int firstNum = nums[l];
        
        // find first element < firstNum
        while(l <= r) {
            int mid = (r + l)/2;
            
            if (nums[mid] < firstNum) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l == nums.length ? nums[0] : nums[l];
    }
}