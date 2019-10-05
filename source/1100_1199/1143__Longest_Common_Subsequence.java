/* DP
 */
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        
        int m = text1.length();
        int n = text2.length();
        char[] charArr1 = text1.toCharArray();
        char[] charArr2 = text2.toCharArray();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = 0;
        }
        
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 0;
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (charArr1[i - 1] == charArr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        
        return dp[m][n];
    }
}