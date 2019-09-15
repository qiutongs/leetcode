
/*
 * Ref https://www.youtube.com/watch?v=aYfwus5T3Bs
 */

class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int result = 0;
        
        prefixSum[0] = 0;
        hashMap.put(0, 1);
        
        for (int i = 1; i < nums.length + 1;i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i - 1];
            
            Integer count = hashMap.get(prefixSum[i] - k);
            
            if (count != null) {
                result += count;
            }
            
            hashMap.compute(prefixSum[i], (key,v) -> v == null ? 1 : v + 1 );
        }
        
        return result;
    }
}