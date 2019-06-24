/*
Ref: https://www.zhihu.com/question/33776070/answer/685253646?utm_source=wechat_session&utm_medium=social&utm_oi=806547547257724928
*/
class Solution {
    public int[] singleNumber(int[] nums) {
        int twoResultsXor = 0;
        
        for (int num : nums) {
            twoResultsXor ^= num;
        }
        
        // Any "1" bit is OK. Here it picks the last one bit.
        int lastOneBitMask = twoResultsXor & -twoResultsXor;
        
        int result1 = 0, result2 = 0;
        
        for (int num : nums) {
            // Divide the array to 2 groups: 0 or 1 on that mask bit.
            if ((num & lastOneBitMask) == 0) {
                result1 ^= num;
            } else {
                result2 ^= num;
            }
        }
        
        int[] result = {result1, result2};
        return result;
    }
}