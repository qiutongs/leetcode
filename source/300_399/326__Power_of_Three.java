class Solution {
    private int MAX_POWER_THREE = 1162261467;
    
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        
        return MAX_POWER_THREE % n == 0;
    }
}