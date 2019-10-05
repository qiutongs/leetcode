class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        
        while(r - l >= 2) {
            int mid = (l + r) / 2;
            
            // valley
            if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
                r = mid - 1;
            } else if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) { // uphill
                l = mid + 1;
            } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) { // downhill
                r = mid - 1;
            } else { // peak
                return mid;
            }
        }
        
        return nums[r] > nums[l] ? r : l;
    }
}