/*
Time: O(n) average, O(n^2) worst

https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array-set-2-expected-linear-time/
*/
class Solution {
    
    private static Random rand = new Random();
    
    public int findKthLargest(int[] nums, int k) {
        return findKthLargestHelper(nums, k, 0, nums.length - 1);
    }
    
    private int findKthLargestHelper(int[] nums, int k, int s, int e) {
        if (s == e) {
            return nums[s];
        }
        
        // Randomly generate a pivot between s and e.
        int pivotIndex = rand.nextInt(e - s + 1) + s;
        
        // Track the index that is firstly greater than or equal to pivot
        int firstGEIndex = s;
        
        // Swap the pivotIndex with the last element.
        swap(nums, pivotIndex, e);
        
        // Standard partition algorithm.
        for (int i = s; i < e; i++) {
            if (nums[i] < nums[e]) {
                swap(nums, i, firstGEIndex);
                firstGEIndex++;
            }
        }
        
        int lengthOfGE = e - firstGEIndex + 1;
        
        if (lengthOfGE == k) {
            return nums[e];
        } else if (lengthOfGE > k) {
            // Note: the boundary must be careful to make sure it reduces in each recursion.
            // For example, it will be dead loop if from firstGEIndex to e
            return findKthLargestHelper(nums, k, firstGEIndex, e - 1);
        } else {
            return findKthLargestHelper(nums, k - lengthOfGE, s, firstGEIndex - 1);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}