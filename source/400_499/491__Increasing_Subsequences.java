class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        return findSubHelper(nums, nums.length)
                    .stream()
                    .distinct()
                    .filter(sublist -> sublist.size() >= 2)
                    .collect(Collectors.toList());
    }
    
    private List<List<Integer>> findSubHelper(int[] nums, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if (n == 0) {
            result.add(Collections.emptyList());
            return result; 
        }
        
        List<List<Integer>> subResult = findSubHelper(nums, n - 1);
        
        for (List<Integer> sublist : subResult) {
            if (sublist.isEmpty() || nums[n - 1] >= sublist.get(sublist.size() - 1)) {
                List<Integer> newSublist = new ArrayList<>(sublist);
                newSublist.add(nums[n - 1]);
                result.add(newSublist);
            }
            
            result.add(sublist);
        }
        
        return result;
    }
}