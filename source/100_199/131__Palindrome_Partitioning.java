class Solution {
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }
        
        List<List<String>> result = new ArrayList<>();
        
        dfs(result, new ArrayList<>(), s, 0);
        
        return result;
    }
    
    private void dfs(List<List<String>> result, List<String> partition, String s, int i) {
        if (i == s.length()) {
            result.add(new ArrayList<>(partition));
            return;
        }
        
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                partition.add(s.substring(i, j + 1));
                dfs(result, partition, s, j + 1);
                partition.remove(partition.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}