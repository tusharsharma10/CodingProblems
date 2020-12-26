import java.util.Arrays;

/**
 * It is really important to convert the question in a mathematical function.
 */
public class ArrayMagic {

    /**
     * O(N) algo for checking whether array is sorted or not
     * 
     */
    public static boolean isSorted(int arr[]) {

        for (int i = 1; i < arr.length; i++) {

            if (arr[i - 1] > arr[i + 1])
                return false;
        }

        return true;
    }

    /**
     * Second Largest Element in array O(N) algorithm
     */
    public static int secondLargest(int arr[]) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            }

            else if (arr[i] > max2)
                max2 = arr[i];

        }

        return max2;
    }

    /**
     * Reverse an array O(N/2) time
     */

    public static void reverseArray(int arr[], int low, int high) {

        while (low < high) {

            swap(arr, low, high);
            low++;
            high--;

        }
    }

    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T> void printArray(T arr[]) {

        for (T i : arr) {
            System.out.print(i + " ");
        }
    }

    /**
     * Remove duplicates from sorted array
     * 
     * @param args
     */

    public static void removeDuplicates(int arr[]) {

        Arrays.sort(arr);

        for (int i = 1; i < arr.length; i++) {

            if (arr[i - 1] == arr[i]) {
                System.out.println("Duplicate was: " + arr[i]);
                arr[i] = -1;
            }

        }
    }

    /**
     * Move zeroes in array to end of the array. i - Zero Pointer - Will jump by one
     * if no. is other than zero j - Non Zero Pointer - Will jump by one if no. is
     * zero
     */

    public static void moveZeroesToEnd(int arr[]) {

        for (int i = 0, j = 1; i < arr.length && j < arr.length;) {

            if (arr[i] != 0)
                i++;

            if (arr[j] == 0)
                j++;

            if (arr[i] == 0 && arr[j] != 0) {
                swap(arr, i, j);
                i++;
                j++;
            }

        }

    }

    /**
     * Rotating an Array by d Positions to left Very easy using an extra array
     * Challenge is to do it without using any extra space. Reversal Algorithm:
     * 
     * @param args
     */

    public static void leftRotateArray(int arr[], int d) {

        d = d % arr.length;
        // Reverse array from 0 to d-1 elements
        reverseArray(arr, 0, d - 1);
        // Reverse array from d to end elements
        reverseArray(arr, d, arr.length - 1);
        // Reverse the whole array
        reverseArray(arr, 0, arr.length - 1);

    }

    /**
     * Right rotating an array by d positions Call leftRotate with d = arr.length -
     * d
     */
    public static void rightRotateArray(int arr[], int d) {

        leftRotateArray(arr, arr.length - d);
    }

    /***
     * Leader in an array: Numbers for which all of it's right elements are smaller
     * than it. RightMost element is always a leader
     * 
     * @param args
     */
    public static void findAllLeaders(int arr[]) {

        int currentLeader = arr[arr.length - 1];
        // Rightmost element is a leader
        System.out.print(arr[arr.length - 1] + " ");
        for (int i = arr.length - 1; i >= 0; i--) {

            if (arr[i] > currentLeader) {
                System.out.print(arr[i] + " ");
                currentLeader = arr[i];
            }

        }
    }

    /***
     * Maximum difference problem: (Unsorted Array) arr[j] - arr[i] where j > i
     * 
     * @param args
     */
    public static int maxDifference(int arr[]) {

        int maxDiff = Integer.MIN_VALUE;

        // j > i always
        for (int i = 0, j = 1; j < arr.length; j++) {

            if (arr[i] < arr[j] && maxDiff < arr[j] - arr[i])
                maxDiff = arr[j] - arr[i];

            else if (arr[i] > arr[j])
                i = j;

        }

        return maxDiff;
    }

    /**
     * Frequencies in a sorted array. Prints the frequency of occurence of all
     * elements of array.
     */

    public static void frequency(int arr[]) {

        int count = 1;
        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i] == arr[i + 1]) {
                count++;

                if (i == arr.length - 2)
                    System.out.println(arr[i] + ": " + count);

            } else {

                System.out.println(arr[i] + ": " + count);
                count = 1;

                if (i == arr.length - 2)
                    System.out.println(arr[i + 1] + ": " + count);

            }
        }
    }

    /**
     ** Stock buying and selling problem Every bottom point buy the stock and every
     * peak sell the stock
     **/
    public static int maxProfit(int arr[]) {
        int profit = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i + 1] > arr[i]) {
                profit += arr[i + 1] - arr[i];
            }
        }

        return profit;
    }

    /***
     * Trapping Rain water b/w two bars of different heights. f(width,Min(height)).
     * Water is trapped when array is not strichtly increasing or decreasing. Medium
     * Level Qs
     */
    public static int maxRainWater(int arr[], int n) {

        int res = 0;

        int lMax[] = new int[n];
        int rMax[] = new int[n];

        lMax[0] = arr[0];

        for (int i = 1; i < n; i++)
            lMax[i] = Math.max(arr[i], lMax[i - 1]);

        rMax[n - 1] = arr[n - 1];

        for (int i = n - 2; i >= 0; i--)
            rMax[i] = Math.max(arr[i], rMax[i + 1]);

        for (int i = 1; i < n - 1; i++)
            res = res + (Math.min(lMax[i], rMax[i]) - arr[i]);

        return res;
    }

    /***
     * Find count of maximum consecutive 1s in a binary array. Two approaches are
     * discussed, one is O(n^2) and other is O(n). Both of these approaches require
     * O(1) auxiliary space.
     * 
     * @param args
     */

    public static int maxConsecutiveOnes(int arr[]) {

        int maxCount = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            }

            else if (arr[i] == 0)
                count = 0;
        }

        return maxCount;
    }

    /***
     * Explanation of the Kadane's algorithm. Max Subarray Sum - Max Sum of
     * continuous elements.
     * 
     * @param args
     */

    public static int maxSubarraySum(int arr[]) {

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > 0) {
                sum += arr[i];
                maxSum = Math.max(sum, maxSum);
            }

            else {
                sum += arr[i];
                maxSum = Math.max(arr[i], maxSum);
            }

            if (sum < 0)
                sum = 0;

        }

        return maxSum;

    }

   

    /**
     * Prefix Sum: finding sum of indexes using preprocessing. O(N) time O(N) space
     * 
     * @param args
     */

    public static int prefixSum(int arr[], int l, int r) {

        int prefix[] = new int[arr.length];
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];
            prefix[i] = sum;

        }

        if (l != 0)
            return prefix[r] - prefix[l - 1];

        else
            return prefix[r];
    }

    /**
     * Range represented by 2 arrays L & R. To find the maximum appearing element in
     * these ranges. O(N) time iff - 0 <= L[i],R[i] <= 1000 else HashMap technique
     * O(NM)
     * We have applied a really clever trick here.
     * Most frequent element is the one whose value is the highest in the arr array.
     */

    public static int rangePrefixSum(int L[], int R[]) {

        int arr[] = new int[1000];

       // R[i]
        for (int i = 0; i < L.length; i++) {

            // TO increase the leftmost element by 1.
            arr[L[i]]++;
            
            // To nullify elements that are out of range
            arr[R[i] + 1]--;
        }

        //printIntArray(arr);
        int max = arr[0];
        int index = 0;

        // Prefix sum of array arr
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];

            // checking and updating the max element in arr array.
            if (max < arr[i]) {
                max = arr[i];
                index = i;
            }
        }

       // determines the number which is most frequent in all ranges.
        return index;
    }

/**
 * print int array
 * @param args
 */
public static  void printIntArray(int arr[]) {

    System.out.println();
   
    for (int i = 0 ; i<arr.length; i++) {
        System.out.print(arr[i] + " ");
    }
}

    public static void main(String[] args) {

        int l[] = { 1, 2, 3 };
        int r[] = { 3, 5, 7 };
        System.out.println(rangePrefixSum(l, r));
        
        

        // int arr[] = {2,8,3,9,6,5,4};
        // System.out.println(prefixSum(arr,0,2));

        // String s = "23 41 84 -8 42 -54 1 2 28 49 -32 -16 -33 -44 -100 -30 68 -47 59
        // -94 35 -18";
        // addCommas(s);
        // System.out.println(maxSubarraySum(arr));

        // System.out.println(maxConsecutiveOnes(arr));

        // System.out.println(maxRainWater(a3,a3.length));

        // int arr[] = { 190, 12, 1, 90, 32, 78, 56, 78, 12, 123, 43 };
        // System.out.println(maxProfit(a3));

        // System.out.println(secondLargest(arr));
        // reverseArray(arr);
        // printArray(arr);

        // removeDuplicates(arr);

        // int a1[] = {1,0,0,0,0,0,2,5,0,6,7,8};
        // moveZeroesToEnd(a1);
        // leftRotateArray(arr, 2);
        // rightRotateArray(arr, 2);
        // printArray(arr);
        // findAllLeaders(a2);

        // int a2[] = { 2, 3, 10, 16, -1, 18, 1 };

        // System.out.println(maxDifference(a2));
        // frequency(a3);

    }
}
