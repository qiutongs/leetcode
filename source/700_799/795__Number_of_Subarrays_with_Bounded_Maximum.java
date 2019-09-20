/* tweaked sliding window */
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int ret = 0;
        
        int l = 0, inBounded = -1;
        
        for (int r = 0; r < A.length; r++) {
            if (A[r] > R) {
                l = r + 1;
                inBounded = r;
            } else if (A[r] < L) {
                ret += inBounded - l + 1;
            } else {
                inBounded = r;
                ret += inBounded - l + 1;
            }
        }
        
        return ret;
    }
}

/*
 * This is actually number of subarray SUM with bounded max
 */
class Solution2 {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int l = 0, sum = 0;
        
        for (int r = 0; r < A.length; r++) {
            sum += A[r];
            
            while(l <= r && sum > R) {
                sum -= A[l];
                l++;
            } 
            
            // first <= R
            int tmpL = l;
            int tmpSum = sum;
            
            while(tmpL <= r && tmpSum >= L) {
                tmpSum -= A[tmpL];
                tmpL++;
            }
            
            // now, tmpL is first < L
            
            ret += tmpL - l;
        }
        
        return ret;
    }
}

/*
 * This is actually number of subarray within bounds
 */
class Solution3 {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int ret = 0;
        int l = 0, r = 0;
        int size = 0;
        
        for (; r < A.length; r++) {
            if (A[r] > R || A[r] < L) {
                size = r - l;
                ret += size * (size + 1) / 2;
                l = r + 1;
            }
        }
        
        size = r - l;
        ret += size * (size + 1) / 2;
        
        return ret;
    }
}