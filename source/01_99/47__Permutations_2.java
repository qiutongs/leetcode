/* DFS: similar to Permutations 1
 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        Arrays.sort(nums);
        
        dfs(result, new ArrayList<>(), nums, 0);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> curPermu, int[] nums, int i) {
        if (i == nums.length) {
            result.add(new ArrayList<>(curPermu));
        }
        
        int j = i, curIndex = i;
        
        while(j < nums.length) {
            // select index j
            int selectedNum = nums[j];
            curPermu.add(selectedNum);
            // shift right by one to keep sorted
            shiftRight(nums, i, j - 1);
            
            dfs(result, curPermu, nums, i + 1);
            
            // backtrack: shift left and reset
            shiftLeft(nums, i + 1, j);
            nums[j] = selectedNum;
            curPermu.remove(curPermu.size() - 1);
            
            // next different num
            while(j < nums.length && nums[curIndex] == nums[j]) {
                j++;
            }
            curIndex = j;
        }
    }
    
    private void shiftRight(int[] nums, int i, int j) {
        for (int k = j + 1; k > i; k--) {
            nums[k] = nums[k - 1];
        }
    }
    
    private void shiftLeft(int[] nums, int i, int j) {
        for (int k = i - 1; k < j; k++) {
            nums[k] = nums[k + 1];
        }
    }
}