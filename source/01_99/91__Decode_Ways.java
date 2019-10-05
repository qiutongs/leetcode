/*
 * DP problem: similar structure as "Go Upstairs Problem"
 */
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Decode as single digit.
            if (c != '0') {
                dp[i + 1] = dp[i];
            }
            
            // Decode as double digits.
            if (i > 0) {
                char preC = s.charAt(i - 1);
                int twoDigits = toInt(c) + 10 * toInt(preC);
                
                if (twoDigits >= 10 && twoDigits <= 26) {
                    dp[i + 1] += dp[i - 1];
                }
            }
        }
        
        return dp[s.length()];
    }
    
    private int toInt(char c) {
        return c - '0';
    }
}