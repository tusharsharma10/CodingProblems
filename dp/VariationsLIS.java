package dp;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        int arr[] = {24 ,12 ,35 ,32, 11 ,20 ,49};
        System.out.println(minDeletions(arr));
    }
}
