class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        
        dfs(result, new ArrayList<>(), k, n, 1);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> comb, int k, int n, int i) {
        if (n == 0 && k == 0) {
            result.add(new ArrayList<>(comb));
            return;
        }
        
        if (k == 0 || n < 0) {
            return;
        }
        
        for (int j = i; j <= 9; j++) {
            comb.add(j);
            dfs(result, comb, k - 1, n - j, j + 1);
            comb.remove(comb.size() - 1);
        }
    }
}