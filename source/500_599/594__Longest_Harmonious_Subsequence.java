class Solution {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int ret = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        
        for (int num : nums) {
            hashMap.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        
        for (int num : nums) {
            Integer count = hashMap.get(num);
            Integer countPlusOne = hashMap.get(num + 1);
            Integer countMinusOne = hashMap.get(num - 1);
            if (countPlusOne != null) {
                ret = Math.max(ret, countPlusOne + count);
            }
            
            if (countMinusOne != null) {
                ret = Math.max(ret, countMinusOne + count);
            }
        }
        
        return ret;
    }
}