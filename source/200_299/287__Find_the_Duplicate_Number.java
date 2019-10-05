class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1;
        
        int l = 1, r = n;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            int equalMid = 0, lessMid = 0;
            
            for (int num : nums) {
                if (num == mid) {
                    equalMid++;
                } else if (num < mid) {
                    lessMid++;
                }
            }
            
            if (equalMid > 1) {
                return mid;
            }
            
            if (lessMid >= mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return -1;
    }
}