/*
DP idea: O(n^2)

compute LTS beginning at each index, start from last index
(equivalent to ending at each index, start from first index)
*/
class Solution1 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] beginWithLIS = new int[nums.length];
        Arrays.fill(beginWithLIS, 1);

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    beginWithLIS[i] = Math.max(beginWithLIS[i], beginWithLIS[j] + 1);
                }
            }
        }

        return Arrays.stream(beginWithLIS).max().getAsInt();
    }
}

/*
Patience Sort: 
- simulate patience game (first phase of patience sort).
- the number of piles is the result of LIS
*/
class Solution2 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int[] piles = new int[nums.length];
        int size = 0;
        
        for (int num : nums) {
            int insertIndex = binarySearch(piles, size - 1, num);
            
            piles[insertIndex] = num;
            
            if (insertIndex == size) {
                size++;
            }
        }

        return size;
    }
    
    private int binarySearch(int[] piles, int high, int target) {
        int low = 0;
        
        while(low <= high) {
            int mid = (low + high) >>> 1;
                
            if (target < piles[mid]) {
                high = mid - 1;
            } else if (target > piles[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        
        return low;
    }
}