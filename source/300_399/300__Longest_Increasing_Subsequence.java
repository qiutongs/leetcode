/*
DP idea: O(n^2)

compute LTS beginning at each index, start from last index
(equivalent to ending at each index, start from first index)
*/
class Solution {
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
