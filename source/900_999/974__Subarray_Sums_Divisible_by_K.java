/*
 * Prefix-sum and modulo.
 */
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int result = 0;
        int remainA[] = new int[A.length];
        int remainCount[] = new int[K];
        
        Arrays.fill(remainCount, 0);
        
        // 1. calculate the sum: sum[i] = A[0] + ... + A[i]
        remainA[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            remainA[i] = remainA[i-1] + A[i];
        }
        
        // 2. calculate the remain: remainA[i] = sum[i] % k
        // 3. count for each remain
        for (int i = 0; i < A.length; i++) {
            remainA[i] %= K;
            
            // remainA[i] can < 0
            if ( remainA[i] < 0 ) remainA[i] += K;
            
            remainCount[remainA[i]]++;
        }
        
        // 4. sum up possible subarray in each remain "bucket", including remain = 0
        for (int i = 0; i < K; i++) {
            // pick two element in the same bucket
            result += remainCount[i] * (remainCount[i] - 1) / 2;
        }
        
        // 5. add the divisible by K (remain is 0)
        result += remainCount[0];
        
        return result;
    }
}