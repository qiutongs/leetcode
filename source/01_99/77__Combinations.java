class Solution1 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();

        backtrack(result, new LinkedList<>(), n, k);
        
        return result; 
    }
    
    private void backtrack(List<List<Integer>> result, List<Integer> curList, int n, int k){
        if (k == 0){
            result.add(new LinkedList<>(curList));
            return;
        }
        
        if (n == 0){
            return;
        }
        
        //select n
        curList.add(n);
        backtrack(result, curList, n-1, k-1);
        curList.remove(curList.size()-1);
        //not select n
        backtrack(result, curList, n-1, k);
    }
}

class Solution2 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        
        dfs(result, new ArrayList<>(), n, k);
        
        return result; 
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> comb, int n, int k) {
        if (k == 0) {
            result.add(new ArrayList<>(comb));
        }
        
        if (n == 0) {
            return;
        }
        
        for (int i = n; i >= 1; i--) {
            comb.add(i);
            dfs(result, comb, i - 1, k - 1);
            comb.remove(comb.size() - 1);
        }
    }
}