package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class VariationsLIS {

    /**
     * Longest increasing subsequence: O(nlgn)
     */

    public static int lis(int arr[]) {

        List<Integer> tail = new ArrayList<>();
        tail.add(arr[0]);
        int tailLen = 1;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > tail.get(tailLen - 1)) {
                tail.add(arr[i]);
                tailLen++;
            }

            else {
                int ceilIdx = ceilIdx(tail, arr, 0, tailLen - 1, arr[i]);
                tail.set(ceilIdx, arr[i]);
            }

        }

        return tailLen;
    }

    private static int ceilIdx(List<Integer> tail, int arr[], int low, int high, int k) {

        while (high > low) {

            int mid = (low + high) / 2;

            if (tail.get(mid) >= k)
                high = mid;

            else
                low = mid + 1;

        }

        return high;
    }

    /**
     * Minimum deletions to make an array sorted
     */
    public static int minDeletions(int arr[]) {
        int lis = lis(arr);

        return arr.length - lis;
    }

    /**
     * Returns the sequence of numbers that forms the part of this list.
     */

    public static List<Integer> maxSumIncSubsequence(int arr[]) {

        List<Integer> ans = new ArrayList<>();
        int maxSum[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            maxSum[i] = arr[i];

            for (int j = 0; j < i; j++) {

                if (arr[j] < arr[i] && maxSum[i] < arr[i] + maxSum[j]) {
                    maxSum[i] = arr[i] + maxSum[j];
                }
            }
        }

        int res = maxSum[0];
        int maxIndex = 0;

        // printArray(maxSum);

        for (int i = 1; i < arr.length; i++) {

            if (maxSum[i] > res) {
                res = maxSum[i];
                maxIndex = i;
            }
        }

        Set<Integer> set = new TreeSet<>();
        set.add(arr[maxIndex]);
        for (int i = maxIndex, j = maxIndex - 1; i >= 1 && j >= 0;) {

            if (arr[i] > arr[j] && maxSum[i] - maxSum[j] == arr[i]) {
                set.add(arr[i]);
                set.add(arr[j]);
                i = j;
                j--;
            }

            else
                j--;
        }

        for (int x : set) {
            ans.add(x);
        }

        return ans;
    }

    private static void printArray(int arr[]) {

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * Max Sum increasing subsequence: Elements should increase from i= 0 to n and
     * sum should be maximum
     */

    public static int maxSumIncreasingSubsqnc(int arr[]) {

        int maxSum[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            maxSum[i] = arr[i];

            for (int j = 0; j < i; j++) {

                if (arr[j] < arr[i]) {
                    maxSum[i] = Math.max(maxSum[i], arr[i] + maxSum[j]);
                }
            }
        }

        int res = maxSum[0];
        for (int i = 1; i < arr.length; i++) {

            res = Math.max(res, maxSum[i]);
        }

        return res;
    }

    /**
     * LIS (N*N) returns the dp array of max increasing subsequence
     */

    public static int[] lis2(int arr[]) {

        int maxLen[] = new int[arr.length];
        Arrays.fill(maxLen, 1);
        maxLen[0] = 1;

        for (int i = 0; i < arr.length; i++) {

            int len = 0;

            for (int j = 0; j < i; j++) {

                if (arr[i] > arr[j])
                    maxLen[i] = Math.max(maxLen[i], 1 + maxLen[j]);
            }
        }

        return maxLen;

    }

    /**
     * Longest decreasing subsequence: Same as LIS but starts from end to 0.
     * 
     * @return
     */

    public static int[] lds(int arr[]) {

        int n = arr.length;
        int minLen[] = new int[n];
        Arrays.fill(minLen, 1);
        minLen[n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j > i; j--) {

                if (arr[i] > arr[j])
                    minLen[i] = Math.max(minLen[i], 1 + minLen[j]);
            }
        }

        return minLen;
    }

    /**
     * Max Length Bitonic Subsequence
     */

    public static int maxBitonicSub(int arr[]) {

        int lis[] = lis2(arr);
        int lds[] = lds(arr);

        int maxBitonic = 0;

        for (int i = 0; i < arr.length; i++) {

            // -1 bcz same element is present in both lds and lis.
            int sum = lis[i] + lds[i] - 1;
            maxBitonic = Math.max(maxBitonic, sum);

        }

        return maxBitonic;
    }

    /**
     * Building bridges: No crosing b/w the bridges, maximise the bridges. Algo:
     * Sort the array in increasing order of first value pair. Find lis of second
     * value of the sorted array
     */

    public static int maxBridges(Integer arr[][]) {

        Arrays.sort(arr, (a, b) -> {

            if (Integer.compare(a[0], b[0]) == 0) {
                return Integer.compare(a[1], b[1]);
            }

            else
                return Integer.compare(a[0], b[0]);
        });

        int a[] = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {

            a[i] = arr[i][1];

        }

        int lis = lis(a);

        // printMatrix(arr);

        return lis;
    }

    private static void printMatrix(Integer mat[][]) {

        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[0].length; j++) {

                System.out.print(mat[i][j] + " ");
            }

            System.out.println();
        }

    }

    /**
     * Longest chain of pairs: such that (a,b) (c,d) b<c
     */

    public static int longestChain(Integer arr[][]) {

        Arrays.sort(arr, (a, b) -> {
            return Integer.compare(a[0], b[0]);
        });

        // LIS
        int dp[] = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < i; j++) {

                if (arr[i][0] > arr[j][0])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
        }

        int lis = 0;

        for (int i = 0; i < dp.length; i++) {

            lis = Math.max(lis, dp[i]);
        }

        return lis;
    }

/**
 * Maximum cuts in rod:
 * Fully divide the rod using given cuts dimension,
 * @param args
 */
public static int rodCutting(int len, int a, int b, int c){

        int minCutSize = Math.min(Math.min(a,b),c);

        return rodCutRec(len,a,b,c,minCutSize);
}

public static int rodCutRec(int len, int a, int b, int c, int minSize){

        if(len < 0)
            return -1;
        
        if(len == 0)
            return 0;
    
        
            int cut1 = rodCutRec(len-a, a, b, c, minSize);
            int cut2 = rodCutRec(len-b, a, b, c, minSize);
            int cut3 = rodCutRec(len-c, a, b, c, minSize);

            int res = Math.max(cut1, Math.max(cut2,cut3));

            if(res == -1) return -1;
            
            return res+1;
       
}

    public static void main(String[] args) {

        System.out.println(rodCutting(5,4,2,6));
       
       
        // Integer arr[][] = { { 6, 2 }, { 4, 3 }, { 2, 6 }, { 2, 5 }, { 2, 3 }, { 1, 5 } };

       // maxBridges(arr);

        // System.out.println(maxBitonicSub(arr));

        // System.out.println(lis(arr));

        // System.out.println(maxSumIncSubsequence(arr));
        // System.out.println(maxSumIncreasingSubsqnc(arr));
        // System.out.println(minDeletions(arr));
    }

}
