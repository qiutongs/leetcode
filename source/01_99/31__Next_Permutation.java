// Ref http://bangbingsyb.blogspot.com/2014/11/leetcode-next-permutation.html?m=1
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        // step 1: start from right, find the first number < its right
        // 1, 3, 2 -> 1
        int firstSmallIndex = nums.length - 2;
        for (; firstSmallIndex >= 0; firstSmallIndex--) {
            if (nums[firstSmallIndex] < nums[firstSmallIndex + 1]) {
                break;
            }
        }
        
        // step 2: start from right, find the first number > firstSmall; swap them
        // 1, 3, 2 -> 2
        // 2, 3, 1
        if (firstSmallIndex >= 0) {
            for ( int firstLargeIndex = nums.length - 1; firstLargeIndex > firstSmallIndex; firstLargeIndex--) {
                if (nums[firstLargeIndex] > nums[firstSmallIndex]) {
                    swap(nums, firstSmallIndex, firstLargeIndex);
                    break;
                }
            }
        }
        
        // step 3: reverse all the numbers right to first small
        int l = firstSmallIndex + 1, r = nums.length - 1;
        while(l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
    }
}