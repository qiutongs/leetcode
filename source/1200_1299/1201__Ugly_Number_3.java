/*
 * This is NOT same ugly number. 
 * This solution uses similar idea but time exceed
 */
class Solution1 {
    public int nthUglyNumber(int n, int a, int b, int c) {       
        int ret = 0;
        int ai = 1, bi = 1, ci = 1;
        
        while(n > 0) {
            int nextA = ai * a;
            int nextB = bi * b;
            int nextC = ci * c;

            int min = Math.min(nextA, Math.min(nextB, nextC));
            
            if (a * ai == min) {
                ret = a * ai;
                ai++;
            } 
            
            if (b * bi == min) {
                ret = b * bi;
                bi++;
            } 
            
            if (c * ci == min) {
                ret = c * ci;
                ci++;
            }
            
            n--; 
        }
        
        return ret;
    }
}

/*
 * This is NOT same ugly number. 
 * https://leetcode.com/problems/ugly-number-iii/discuss/387780/Java-Binary-Search-Venn-Diagram-Explain-Math-Formula
 */
class Solution2 {
    public int nthUglyNumber(int n, int a, int b, int c) {       
        int ret = 0;
        int l = 1, r = Integer.MAX_VALUE;
        
        while(l <= r) {
            int mid = (l + r) >>> 1;
            
            int divisibles = numOfDivisibles(mid, a, b, c);
            
            if (divisibles > n) {
                r = mid - 1;
            } else if (divisibles < n) {
                l = mid + 1;
            } else {
                ret = mid;
                r = mid - 1;
            }
        }

        return ret;
    }
    
    public int numOfDivisibles(int num, int a, int b, int c) {
        long abLcm = lcm(a, b);
        long bcLcm = lcm(b, c);
        long acLcm = lcm(a, c);
        long abcLcm = lcm(a, bcLcm);
        
        return (int) (num/a + num/b + num/c - num/abLcm - num/bcLcm - num/acLcm + num/abcLcm);
    }
    
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
    
    private long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    }
}