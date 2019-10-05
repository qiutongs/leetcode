/* Interval DP
 */
class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = i == j ? 1 : 0;
            }
        }
        
        for (int interval = 2; interval <= n; interval++) {
            for (int i = 0; i + interval <= n; i++) {
                if (s.charAt(i) == s.charAt(i + interval - 1)) {
                    dp[i][i + interval - 1] = dp[i + 1][i + interval - 2] + 2;
                } else {
                    dp[i][i + interval - 1] = Math.max(dp[i + 1][i + interval - 1], dp[i][i + interval - 2]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}