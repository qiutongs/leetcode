/*
Idea: Sort and select next move carefully.
After sort, say there is a subarray of duplicated value: 2,2,2,2,2,2...
The output should only include ONCE '2', '22', '222', '2222', ....
1. if not select 2 on current index, then never, go to next different value
2. if select 2 on current index, we can go to next value (maybe duplicate)
*/
class Solution1 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);

        Arrays.sort(nums);
        backtrack(new ArrayList<>(), nums.length - 1, nums, result);

        return result;
    }

    private void backtrack(List<Integer> curSet, int curIndex, int[] nums, List<List<Integer>> output) {
        if (curIndex == -1) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        int nextIndex = curIndex;
        while(nextIndex >= 0 && nums[nextIndex] == nums[curIndex]) {
            nextIndex--;
        }

        // Not select current num.
        backtrack(curSet, nextIndex, nums, output);

        // Select current num.
        curSet.add(nums[curIndex]);
        backtrack(curSet, curIndex - 1, nums, output);
        curSet.remove(curSet.size() - 1);
    }
}

/*
  DFS: 
 */
class Solution2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> result = new ArrayList<>();
        
        Arrays.sort(nums);
        
        dfs(result, new ArrayList<>(), nums, 0);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> curSet, int[] nums, int i) {
        result.add(new ArrayList<>(curSet));
        
        int j = i;
        
        while(j < nums.length) {
            curSet.add(nums[j]);
            dfs(result, curSet, nums, j + 1);
            curSet.remove(curSet.size() - 1);
            
            while(j < nums.length && nums[j] == nums[i]) {
                j++;
            }
            i = j;
        }
    }
}
