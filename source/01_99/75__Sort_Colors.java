/*
Special counting sort: the integer key is the item itself
*/
class Solution1 {
    public void sortColors(int[] nums) {
        countingSort(nums);
    }

    private void countingSort(int[] nums) {
        int[] counts = new int[3];

        // Counting.
        for (int num : nums) {
            counts[num]++;
        }

        // Simpler output due to the key is the item itself.
        int i = 0;
        for (int k = 0; k < 3; k++) {
            while(counts[k] > 0) {
                nums[i] = k;
                i++;
                counts[k]--;
            }
        }
    }
}

/*
3-way partition:
- 0 to lastZero: all zero
- lastZero + 1 to i - 1: all one
- i - 1 to firstTwo - 1: unexplored
- firstTwo - n: all two
https://en.wikipedia.org/wiki/Dutch_national_flag_problem
*/
class Solution2 {
    public void sortColors(int[] nums) {
        int lastZero = -1, firstTwo = nums.length;

        int i = 0;
        while( i < firstTwo ) {
            if (nums[i] == 0) {
                lastZero++;
                swap(nums, i, lastZero);
                i++;
            } else if (nums[i] == 2) {
                firstTwo--;
                swap(nums, i, firstTwo);
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
