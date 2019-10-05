/*
  Ref: https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
 */
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        
        int factorial = 1;
        ArrayList<Integer> nums = new ArrayList<>(n);

        // Pre-compute n! and put 1~n to an array
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            factorial *= i;
        }
        
        // k was 1-indexed, change it to 0-indexed
        k--;
        
        for (int i = n; i >= 1; i--) {
            int group = k / (factorial / i);
            sb.append(nums.get(group));
            nums.remove(group);
            
            k %= (factorial / i);
            factorial /= i;
        }
        
        return sb.toString();
    }
}