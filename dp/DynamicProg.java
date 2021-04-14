package dp;

import java.math.BigInteger;
import java.util.Arrays;

public class DynamicProg {

    /**
     * Fibonacci recursion
     * 
     * @param args
     */

    public static int fibrec(int n) {

        if (n == 0)
            return 0;

        else if (n == 1)
            return 1;

        else if (n == 2)
            return 1;

        else
            return fibrec(n - 2) + fibrec(n - 1);

    }

    /**
     * Fibonacci DP Top down approach: Memoization
     */
    public static int fibTopDown(int n, int dp[]) {

        if (dp[n] == -1) {

            if (n == 0 || n == 1)
                dp[n] = n;

            else
                dp[n] = fibTopDown(n - 2, dp) + fibTopDown(n - 1, dp);

        }

        return dp[n];
    }

    /**
     * Fibonacci bottom up soln: Tabulation
     */

    public static int botUpFib(int n) {

        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {

            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[dp.length - 1];

    }

    /**
     * Longest Common Subsequence: Recursion approach
     */

    public static int lcSequence(String s1, String s2, int m, int n) {

        if (m == 0 || n == 0)
            return 0;

        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return 1 + lcSequence(s1, s2, m - 1, n - 1);

        else
            return Math.max(lcSequence(s1, s2, m - 1, n), lcSequence(s1, s2, m, n - 1));
    }

    /**
     * LCS Memoization approach theta(MN) time
     * 
     * @param args
     */

    public static int lcSequenceMem(String s1, String s2, int m, int n, int dp[][]) {

        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        // Rest of the code works only if dp(m,n) is == -1 or it is unfilled.
        if (n == 0 || m == 0) {
            dp[m][n] = 0;
        }

        if (s1.charAt(m) == s2.charAt(n))

            dp[m][n] = 1 + lcSequenceMem(s1, s2, m - 1, n - 1, dp);

        else
            dp[m][n] = Math.max(lcSequenceMem(s1, s2, m - 1, n, dp), lcSequenceMem(s1, s2, m, n - 1, dp));

        return dp[m][n];

    }

    /**
     * LCS Tabulation:
     */

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
     * Variations of LCS
     * 
     * @param args
     */

    /**
     * nth catalan numbers: Cn​= 1/n+1 (2n) (n)
     */

    public static BigInteger nthCatalan(int n) {

        BigInteger reqFactorial[] = getFactorial(2 * n);

        BigInteger nPlusOne = new BigInteger(String.valueOf(n + 1));

        BigInteger div1 = reqFactorial[2 * n - n].multiply(reqFactorial[n]);

        BigInteger prod2 = reqFactorial[2 * n].divide(div1);

        return prod2.divide(nPlusOne);

    }

    public static BigInteger[] getFactorial(int n) {

        BigInteger fact[] = new BigInteger[n + 1];
        fact[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {

            BigInteger temp1 = new BigInteger(String.valueOf(i));
            fact[i] = fact[i - 1].multiply(temp1);

        }

        return fact;
    }

    /**
     * Number of ways to make a sum with given set of coins
     * 
     * @param args
     */

    public static int numOfWaysUsingCoins(int coins[], int sum) {

        int n = coins.length;
        int dp[][] = new int[sum + 1][n + 1];

        for (int i = 0; i <= sum; i++) {

            dp[i][0] = 0;
        }

        for (int i = 0; i <= n; i++) {

            dp[0][i] = 1;
        }

        for (int i = 1; i <= sum; i++) {

            for (int j = 1; j <= n; j++) {

                dp[i][j] = dp[i][j - 1];

                if (coins[j - 1] <= i) {
                    dp[i][j] += dp[i - coins[j - 1]][i];
                }
            }
        }

        return dp[sum][n];
    }

    /**
     * Edit distance problem: S1 and S2 allowed to do insert,update,delete to
     * convert S1 to S2. Recursive approach
     */

    public static int editDistance(String s1, String s2, int n, int m) {

        // return left over characters
        if (n == 0)
            return m;

        // return left over characters
        if (m == 0)
            return n;

        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return editDistance(s1, s2, n - 1, m - 1);
        }

        else {

            int update = editDistance(s1, s2, n - 1, m - 1);
            int delete = editDistance(s1, s2, n - 1, m);
            int insert = editDistance(s1, s2, n, m - 1);

            return 1 + Math.min(update, Math.min(insert, delete));
        }

    }

    /**
     * Tabulation Edit Distance: Converting to Recursion to tabulation approach:
     * 1.Initial filling of 2D array is same as base case
     * 
     */

    public static int editDistance(String s1, String s2) {

        int n = s1.length() + 1;
        int m = s2.length() + 1;
        int dp[][] = new int[n][m];
        // Arrays.fill(dp, -1);

        // code for remaining char left in s2
        for (int i = 0; i < n; i++) {

            dp[i][0] = i;
        }

        // code for remaining char left in s2
        for (int i = 0; i < m; i++) {

            dp[0][i] = i;
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j < m; j++) {

                if (s1.charAt(i - 1) != s2.charAt(j - 1))
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));

                else
                    dp[i][j] = dp[i - 1][j - 1];
            }
        }

        return dp[n - 1][m - 1];
    }

    /**
     * Longest increasing subsequence Tabulation Method: O(Nlgn)
     * 
     * @param args
     */

    public static int lis(int arr[]) {

        int tail[] = new int[arr.length];

        tail[0] = arr[0];
        int len = 1;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > tail[len - 1]) {
                tail[len] = arr[i];
                len++;
            } else {
                int c = ceilIdx(tail, 0, len - 1, arr[i]);
                tail[c] = arr[i];
            }
        }

        return len;
    }

    /**
     * Returns the posn where incoming number can is greater than previous number
     * based on binary search.
     */

    private static int ceilIdx(int tail[], int l, int r, int x) {

        while (r > l) {

            int m = (l + r) / 2;

            if (tail[m] >= x)
                r = m;

            else
                l = m + 1;
        }

        return r;
    }

    /**
     * Rod Cutting Problem: Tabulation Method:
     * https://practice.geeksforgeeks.org/problems/cutted-segments1642/1
     */

    public static int rodCutting(int len, int a, int b, int c) {

        int dp[] = new int[len + 1];

        Arrays.fill(dp, 0);

        int minCut = Math.min(Math.min(a, b), c);

        for (int i = 1; i < dp.length; i++) {

            if (i - minCut < 0)
                dp[i] = Integer.MIN_VALUE;

            else if (i - minCut > 0) {
                // dp[i] = 1 + Math.max(Math.max(dp[i - a], dp[i - b]), dp[i - c]);

                int val1 = i - a >= 0 ? dp[i - a] : Integer.MIN_VALUE;
                int val2 = i - b >= 0 ? dp[i - b] : Integer.MIN_VALUE;
                int val3 = i - c >= 0 ? dp[i - c] : Integer.MIN_VALUE;

                dp[i] = 1 + Math.max(Math.max(val1, val2), val3);
            }

            else {
                dp[i] = 1;
            }
        }

        return dp[dp.length - 1] < 1 ? 0 : dp[dp.length - 1];
    }

    /**
     * Minimum Coin change problem DP: Here Greedy approach fails bcz of non
     * standard coins. Tabulation Method: Approach: We have 2 options either we can
     * include the coin or exclude it.
     */

    public static int minCoinChange(int value, int coins[]) {

        Arrays.sort(coins);

        // dp[i] corresponds to a value.
        int dp[] = new int[value + 1];
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            int minCoins = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {

                if (i - coins[j] >= 0)
                    minCoins = Math.min(minCoins, dp[i - coins[j]]);
            }
            dp[i] = minCoins == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + minCoins;
        }

        return dp[value] == Integer.MAX_VALUE ? -1 : dp[value];
    }

    /**
     * Minimum jumps to reach at the end. Tabulation Method:
     */

    public static int minJumps(int arr[]) {

        int dp[] = new int[arr.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < arr.length; i++) {

            for (int j = 0; j < i; j++) {

                if (arr[j] + j >= i && dp[j] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], 1 + dp[j]);

            }
        }

        return dp[arr.length - 1] == Integer.MAX_VALUE ? -1 : dp[arr.length - 1];
    }

    /**
     * 0/1 Knapsack problem: Recursion Approach
     */

    public static int knapsackRec(int value[], int weight[], int w, int n) {

        if (w == 0 || n == 0)
            return 0;

        if (weight[n - 1] > w)
            return knapsackRec(value, weight, w, n - 1);

        else {
            int include = value[n - 1] + knapsackRec(value, weight, w - weight[n - 1], n - 1);
            int exclude = knapsackRec(value, weight, w, n - 1);
            return Math.max(include, exclude);
        }
    }

    /**
     * 0/1 Knapsack problem: Tabulation Method Algo: Uses Max of inclusion and
     * Exclusion of an element.
     */

    public static int knapsack(int value[], int weight[], int w, int n) {

        int dp[][] = new int[n + 1][w + 1];

        // Base Case - Column:1 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // Base Case Row:1 0
        for (int i = 0; i <= w; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= w; j++) {

                // Recursion case 1
                if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];

                // Recursion case 2
                else
                    dp[i][j] = Math.max(dp[i - 1][j], value[i - 1] + dp[i - 1][j - weight[i - 1]]);

            }
        }

        return dp[n][w];
    }

    /**
     * Optimal Strategy for a game: We make the first move need to win the game by
     * collecting max coins: Coins can be collected from arr[0] OR arr[n-1]: Algo
     * calculate the sum of even placed coins and odd placed coins, and we are
     * through we can never lose the game now
     */

    public static int gameStrategyRec(int arr[], int i, int j) {

        // Base case 2 coins left
        if (i + 1 == j)
            return Math.max(arr[i], arr[j]);

        else {
            // opponent leaves min of i+2 by picking i+1 or j-1 by picking j
            int caseI = Math.min(gameStrategyRec(arr, i + 2, j), gameStrategyRec(arr, i + 1, j - 1));
            int caseJ = Math.min(gameStrategyRec(arr, i + 1, j - 1), gameStrategyRec(arr, i, j - 2));

            return Math.max(caseI, caseJ);
        }

    }

    // Tabulation Method:
    public static int gameStrategyDp(int arr[]) {

        int n = arr.length;
        int dp[][] = new int[n][n];

        for (int i = 0; i < n - 1; i++) {

            dp[i][i + 1] = Math.max(arr[i], arr[i + 1]);
        }

        for (int gap = 3; gap < n; gap += 2) {

            for (int i = 0; i + gap < n; i++) {

                int j = i + gap;

                int case1 = Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
                int case2 = Math.min(dp[i + 1][j - 1], dp[i][j - 2]);

                dp[i][j] = Math.max(case1, case2);
            }
        }

        return dp[0][n - 1];
    }

    /**
     * Egg Dropping Puzzle:
     * 
     * @return Minimum trials required to find threshold floor
     */
    public static int eggDropping(int e, int f) {

        int dp[][] = new int[f + 1][e + 1];

        // no. of floors left = 0
        for (int i = 1; i <= e; i++) {
            dp[0][i] = 0;
        }

        // No. of floor left = 1
        for (int i = 1; i <= e; i++) {
            dp[1][i] = 1;
        }

        // No. of egg = 1
        for (int i = 2; i <= f; i++) {
            dp[i][1] = i;
        }

        for (int i = 2; i <= f; i++) {

            for (int j = 2; j <= e; j++) {

                dp[i][j] = Integer.MAX_VALUE;

                for (int x = 1; x <= i; x++) {

                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[x - 1][j - 1], dp[i - x][j]));
                }
            }
        }

        return dp[f][e];
    }



    /**
     * https://www.interviewbit.com/problems/repeating-subsequence/
     */

    public static int anytwo(String A) {


        return 1;

    }

/**
 * longest repeating subsequence
 * @param a
 * @return
 */
    public static int lrs(String a) {

        int n = a.length();

        int dp[][] = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {

            dp[i][0] = 0;
            dp[0][i] = 0;

        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {

                if (a.charAt(i - 1) == a.charAt(j - 1) && i != j)
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

            }

        }

        return dp[n][n] >= 2 ? 1 : 0;
    }
    

    public static void main(String[] args) {

        System.out.println(eggDropping(2,5));

        // int arr[] = { 6335, 2103, 3929, 7359, 587, 1551, 3341, 1060 };

        // System.out.println(gameStrategyDp(arr));

        // System.out.println(minJumps(arr));

        // int coins[] = { 9, 2, 11, 5, 14, 17, 8, 18 };
        // System.out.println(minCoinChange(39, coins));

        // System.out.println(rodCutting(100, 23, 15, 50));
        // String s1 = "SATURDAY";
        // String s2 = "SUNDAY";

        // System.out.println(editDistance(s1, s2, s1.length(), s2.length()));
        // System.out.println(editDistance(s1, s2));

        // int arr[] = { 10, 20, 30, 50 };
        // System.out.println(ceilIdx(arr, 0, arr.length - 1, 35));

        // System.out.println(nthCatalan(5));

        // System.out.println(fibrec(10));
        // int dp[] = new int[11];
        // Arrays.fill(dp, -1);
        // System.out.println(fibTopDown(10, dp));
    }

}
