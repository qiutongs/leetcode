/*
DP idea: ending with n OptUp(n) and OptDown(n)
- OptUp(n) = max(OptDown(k), k = 1 to n -1)
- OptDown(n) = max(OptUp(k), k = 1 to n -1)
*/
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] endWithUpMax = new int[nums.length];
        int[] endWithDownMax = new int[nums.length];

        Arrays.fill(endWithUpMax, 1);
        Arrays.fill(endWithDownMax, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    endWithUpMax[i] = Math.max(endWithUpMax[i], endWithDownMax[j] + 1);
                } else if (nums[i] < nums[j]) {
                    endWithDownMax[i] = Math.max(endWithDownMax[i], endWithUpMax[j] + 1);
                }
            }
        }

        return Math.max(Arrays.stream(endWithUpMax).max().getAsInt(),
                        Arrays.stream(endWithDownMax).max().getAsInt());
    }
}
