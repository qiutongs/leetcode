/*
https://www.geeksforgeeks.org/power-set/
https://www.geeksforgeeks.org/recursive-program-to-generate-power-set/
*/

/*
Most straightforward. Iterate all the 2^n subset.
*/
class Solution1 {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;

        List<List<Integer>> result = new ArrayList<>(1<<n);

        for (int i = 0; i < (1<<n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ( ((1 << j) & i) > 0 ) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }
}

/*
Solve the subsets of first n-1 and add the nth element or not.

Note: this can be converted to iterative approach easily.
*/
class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, nums.length);
    }

    private List<List<Integer>> subsets(int[] nums, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if (n == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        List<List<Integer>> subSubsets = subsets(nums, n - 1);

        // Not include the current num.
        result.addAll(subSubsets);

        for (List<Integer> subSubset : subSubsets) {
            List<Integer> newSubset = new ArrayList<>(subSubset);
            newSubset.add(nums[n-1]);
            result.add(newSubset);
        }

        return result;
    }
}

/*
Backtrack, pick one element or not
*/
class Solution3 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);
        backtrack(new ArrayList<>(), nums.length - 1, nums, result);
        return result;
    }

    private void backtrack(List<Integer> curSet, int curIndex, int[] nums, List<List<Integer>> output) {
        if (curIndex == -1) {
            output.add(new ArrayList<>(curSet));
            return;
        }

        // Not select current num.
        backtrack(curSet, curIndex - 1, nums, output);

        // Select current num.
        curSet.add(nums[curIndex]);
        backtrack(curSet, curIndex - 1, nums, output);
        curSet.remove(curSet.size() - 1);
    }
}
