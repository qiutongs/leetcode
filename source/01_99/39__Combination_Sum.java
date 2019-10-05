/* DFS
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        dfs(result, new ArrayList<>(), candidates, 0, target);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> comb, int[] candidates, int i, int target) {
        if (target < 0) {
            return;
        }
        
        if (target == 0) {
            result.add(new ArrayList<>(comb));
        }
        
        for (int j = i; j < candidates.length; j++) {
            comb.add(candidates[j]);
            dfs(result, comb, candidates, j, target - candidates[j]);
            comb.remove(comb.size() - 1);
        }
    }
}