import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Searching related problems
 */
public class Searching {

    /**
     * Binary Search Iterative Given: Sorted Array O(logn) time Verified Correct
     */

    public static int binarySearch(int arr[], int key) {

        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;
        int index = -1;

        while (low <= high) {

            mid = (low + high) / 2;

            if (key > arr[mid]) {
                low = mid + 1;
            }

            else if (key < arr[mid]) {
                high = mid - 1;
            }

            else if (key == arr[mid]) {
                index = mid;
                break;
            }

        }
        return index;
    }

    /**
     * Given a sorted array with repetition and an element x, we need to find index
     * of first occurrence of x. O(logn) + O(d) time
     * 
     * @param args
     */
    public static int firstOccurence(int arr[], int key) {
        int index = binarySearch(arr, key);

        if (index == -1)
            return index;

        for (int i = index - 1; i >= 0; i--) {

            if (arr[i] != arr[index]) {
                index = i + 1;
                break;
            }

        }
        return index;
    }

    /***
     * Given a sorted array with repetition and an element x, we need to find index
     * of last occurrence of x. O(logn) + O(d) time
     * 
     * @param args
     */
    public static int lastOccurence(int arr[], int key) {
        int index = binarySearch(arr, key);

        if (index == -1)
            return index;

        for (int i = index + 1; i <= arr.length; i++) {

            if (arr[i] != arr[index]) {
                index = i - 1;
                break;
            }

        }
        return index;
    }

    /***
     * Given a sorted array and an element x, we need to count occurrences of x in
     * the array.
     * 
     * @param args
     */

    public static int countOccurences(int arr[], int key) {

        int index = binarySearch(arr, key);

        if (index == -1)
            return index;

        int count = 1;

        for (int i = index + 1; i <= arr.length; i++) {

            if (arr[i] != arr[index])
                break;

            else
                count++;
        }

        for (int i = index - 1; i >= 0; i--) {

            if (arr[i] != arr[index])
                break;

            else
                count++;
        }

        return count;

    }

    /**
     * Given an infinite sized sorted array i.e. high = infinity, we need to write
     * an efficient solution to search an element. Algorithm: Double the index and
     * then do binary search.
     */

    /**
     * Search in sorted rotated array, we dont know by which number it is rotated.
     * To do again.
     */

    public static int searchRotatedArray(int arr[], int x) {

        int low = 0, high = arr.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            if (arr[mid] == x)
                return mid;

            if (arr[low] < arr[mid]) {

                if (x >= arr[low] && x < arr[mid])
                    high = mid - 1;

                else
                    low = mid + 1;
            } else {

                if (x > arr[mid] && x <= arr[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * FInding peak element. GIven : Unsorted array For any subarray there always
     * lies a peak element hence binary search can be used.
     * 
     * @param args
     */

    public static int findPeak(int arr[]) {

        if (arr.length == 1)
            return arr[0];
        else if (arr[0] >= arr[1])
            return arr[0];
        else if (arr[arr.length - 1] >= arr[arr.length - 2])
            return arr[arr.length - 1];

        else {

            int low = 0;
            int high = arr.length - 1;
            int mid = (low + high) / 2;

            while (low <= high) {

                mid = (low + high) / 2;

                if (arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1])
                    return arr[mid];

                else if (arr[mid] < arr[mid - 1])
                    high = mid - 1;

                else if (arr[mid] < arr[mid + 1])
                    low = mid + 1;

            }
        }
        return -1;
    }

    /***
     * Hashing Approach O(N) time O(N) space: Unsorted array: need to find sum
     * equals to K for 2 numbers does contains method of HashSet takes O(1) time ?
     * 
     * @param args
     */

    public static boolean hasSum(int arr[], int k) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++)
            set.add(arr[i]);

        int temp = -1;

        for (int i = 0; i < arr.length; i++) {
            temp = k - arr[i];

            if (set.contains(temp))
                return true;
        }

        return false;

    }

    /**
     * Median of two sorted arrays. Median - Even total elements then (mid1+mid2)/2.
     * total elements odd - mid. Most Efficient : O(logn) time and O() space.
     * 
     * @param args
     */

    public static double getMedian(int a1[], int a2[], int n1, int n2) {
        int begin1 = 0, end1 = n1;

        while (begin1 < end1) {
            int i1 = (begin1 + end1) / 2;
            int i2 = ((n1 + n2 + 1) / 2) - i1;

            int min1 = (i1 == n1) ? Integer.MAX_VALUE : a1[i1];
            int max1 = (i1 == 0) ? Integer.MIN_VALUE : a1[i1 - 1];

            int min2 = (i2 == n2) ? Integer.MAX_VALUE : a2[i2];
            int max2 = (i1 == 0) ? Integer.MIN_VALUE : a1[i2 - 1];

            if (max1 <= min2 && max2 <= min1) {
                if ((n1 + n2) % 2 == 0)
                    return ((double) Math.max(max1, max2) + Math.min(min1, min2)) / 2;
                else
                    return (double) Math.max(max1, max2);
            } else if (max1 > min2)
                end1 = i1 - 1;
            else
                begin1 = i1 + 1;
        }

        return -1;
    }

    /**
     * Majority Element: Element exists > n/2 ceil times Array: Unsorted n:size of
     * array Efficient Soln: Time O(N) & Space O(N) Moore Voting Algo
     * 
     * @param args
     */

    public static int findMajorityElement(int arr[]) {

        /*
         * Find candidate really clever algorithm based on fact that candidate will be
         * greater than rest of all elements so count will be non zero if it exists
         */
        int candIndex = 0;
        int count = 1;
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] == arr[candIndex])
                count++;

            else
                count--;

            if (count == 0) {
                candIndex = i;
                count = 1;
            }
        }

        count = 0;
      
        // check if candidate is really the majority element.
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == arr[candIndex])
                count++;
        }

        return count >= arr.length / 2 ? arr[candIndex] : -1;

    }

    /**
     * Repeating Elements: Array in which only 1 element repeats any number of times
     * Array Unsorted https://ide.geeksforgeeks.org/HmbgklLDTB TIme - O(N) space -
     * O(1) Medium Level Qs - Algorithm of chaining and slow/fast
     */

    public static int repeatingElement(int arr[]) {

        int slow = arr[0], fast = arr[0];

        do {
            slow = arr[slow];
            fast = arr[arr[fast]];

        } while (slow != fast);

        slow = arr[0];

        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;

    }

    /**
     * Allocate Minimum pages: Medium level Binary Search
     * O(nlgn) solution
     * @param args
     */

    public static int minPages(int arr[], int n, int k) {
        //sum  - total pages 
        //mx - max pages in array
        int sum = 0, mx = 0;
       
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            mx = Math.max(mx, arr[i]);
        }
       
        int low = mx, high = sum, res = 0;

       //Binary Search
        while (low <= high) {
            
            int mid = (low + high) / 2;
           
            if (isFeasible(arr, n, k, mid)) {
                res = mid;
                high = mid - 1;
            }
            
            else 
                low = mid + 1;
            
        }
        
        return res;
    }

    // checks how many students are required or "k" when we have given ans.
    public static boolean isFeasible(int arr[], int n, int k, int ans) {
        // req -> students required
        int req = 1, sum = 0;
       
        for (int i = 0; i < n; i++) {
          
            if (sum + arr[i] > ans) {
                req++;
                sum = arr[i];
            } 
            
            else 
                sum += arr[i];
            
        }
        
        return (req <= k);
    }

    

    public static void main(String[] args) {
        int arr[] = { 1, 1, 1, 3, 3, 3, 3, 5 };

        System.out.println(findMajorityElement(arr));
        // System.out.println(hasSum(arr, 17));
        // System.out.println(findPeak(arr));
        // System.out.println(countOccurences(arr, 8));

    }
}
