package dp;

public class VariationLCS {

    public static int lcSequenceTab(String s1, String s2, int m, int n) {

        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < dp.length; i++) {

            for (int j = 1; j < dp[0].length; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * Minimum number of deletions and insertions.
     */

    public int minOperations(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();
        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            dp[i][0] = 0;
        }

        for (int i = 0; i <= n; i++) {

            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }

                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcs = dp[m][n];

        int insertions = n - lcs;
        int deletions = m - lcs;

        return insertions + deletions;

    }

    /**
     * Shortest Common Supersequence: Shortest string containing missing characters
     * of s1 and s2 and subsequence
     * 
     * @param args
     */

    public static int shortestCommonSupersequence(String X, String Y, int m, int n) {
        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            dp[i][0] = 0;
        }

        for (int i = 0; i <= n; i++) {

            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }

                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcs = dp[m][n];
        int insertionsX = m - lcs;
        int insertionsY = n - lcs;

        return lcs + insertionsX + insertionsY;
    }

    /**
     * Longest palindromic subsequence: Reverse the string and find the lcs of both
     * strings.
     */

    public static int lps(String s) {

        StringBuilder str = new StringBuilder(s);

        String x = str.reverse().toString();

        return lcSequenceTab(s, x, s.length(), s.length());

    }

    /**
     * Longest repeating subsequence
     */

    public static int longestRepeatingSub(String s) {

        int n = s.length();

        int dp[][] = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {

                if (s.charAt(i - 1) == s.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }

/**
 * Space optimized version of LCS
 */

 public static void spaceOptimizedLCS(){

 }


    public static void main(String[] args) {


          // System.out.println(longestRepeatingSub(s)); 
       
    }
}
