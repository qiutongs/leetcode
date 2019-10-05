/* 
  DFS
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        dfs(result, new ArrayList<>(), nums, 0);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> curPermu, int[] nums, int i) {
        if (i == nums.length) {
            result.add(new ArrayList<>(curPermu));
        }
        
        for (int j = i; j < nums.length; j++) {
            swap(nums, j, i);
            curPermu.add(nums[i]);
            dfs(result, curPermu, nums, i + 1);
            curPermu.remove(curPermu.size() - 1);
            swap(nums, j, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}