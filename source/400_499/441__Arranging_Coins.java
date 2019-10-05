class Solution {
    public int arrangeCoins(int n) {
        int r = 1;
        long coins = 0;
        
        while(fullStairs(r) < n) {
            r *= 2;
        }
        
        int l = r / 2;
        
        while(l <= r) {
            int mid = l + (r - l) / 2;
            coins = fullStairs(mid);
            
            // first coins > n
            if (coins > n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l - 1;
    }
    
    private long fullStairs(long row) {
        return (1 + row) * row / 2;
    }
}