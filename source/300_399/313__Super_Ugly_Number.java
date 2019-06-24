class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        
        int[] pos = new int[primes.length];
        
        for (int i = 1; i < n; i++) {
            int nextMin = Integer.MAX_VALUE;
            for (int k = 0; k < pos.length; k++) {
                nextMin = Math.min(nextMin, uglyNums[pos[k]] * primes[k]);
            }
            
            uglyNums[i] = nextMin;
            
            for (int k = 0; k < pos.length; k++) {
                if (nextMin == uglyNums[pos[k]] * primes[k]) {
                    pos[k]++;
                }
            }
        }
        
        return uglyNums[n-1];
    }
}