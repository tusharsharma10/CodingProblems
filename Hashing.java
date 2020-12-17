import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Hashing {

    public static void printIntArray(int arr[]) {

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * pair with given sum in unsorted array. Used HashSet therefore whatever we put
     * in hashset is a key of a constant value PRESENT of type Object. HashSet
     * internally uses HashMap.
     * 
     * @param arr
     * @return
     */

    public static boolean pairWithSum(int arr[], int sum) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {

            int diff = sum - arr[i];
            set.add(diff);
        }

        for (int i = 0; i < arr.length; i++) {

            if (set.contains(arr[i]))
                return true;
        }

        return false;
    }

    /**
     * Subarray with 0 sum present or not. Using prefix sum and hashing together.
     * Algorithm: Traverse the array Compute prefix sum Put prefix sum in HashTable,
     * when you get the prefix Sum in hashTable already then return true;
     * 
     * @param arr
     */

    public static boolean subarrayWithSum0(int arr[]) {

        int prefix[] = new int[arr.length];
        int sum = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < prefix.length; i++) {
            sum += arr[i];
            prefix[i] = sum;
            if (set.contains(prefix[i]))
                return true;

            else
                set.add(prefix[i]);
        }

        return false;
    }

    /**
     * Subarray with contiguous given sum
     */

    public static boolean subArrayWithSum(int arr[], int sum) {

        HashSet<Integer> set = new HashSet<>();

        int prefix[] = new int[arr.length];

        int prefixSum = 0;

        for (int i = 0; i < prefix.length; i++) {

            prefixSum += arr[i];
            prefix[i] = prefixSum;

            int diff = prefix[i] - sum;

            if (set.contains(diff))
                return true;

            else
                set.add(prefix[i]);
        }

        return false;
    }

    /**
     * To return length of Longest contiguous Subarray with given sum
     */

    public static int longestSubarrayWithSum(int arr[], int sum) {

        // Key index, value - sum
        Map<Integer, Integer> map = new HashMap<>();

        int prefix[] = new int[arr.length];
        int maxLength = 0;
        int prefixSum = 0;

        for (int i = 0; i < prefix.length; i++) {

            prefixSum += arr[i];
            prefix[i] = prefixSum;

            int diff = prefix[i] - sum;

            if (map.containsKey(diff) && maxLength < i - map.get(diff))
                maxLength = i - map.get(diff);

            else
                map.putIfAbsent(prefix[i], i);

        }

        return maxLength;

    }

    /**
     * Longest contiguous subarray with equal number of 0's and 1's O(N) based on
     * Hashing. Variation of finding length of longest subarray with 0 sum. Algo:
     * Change all 0's to -1 and call longestSubarrayWithSum passing 0 as sum
     * 
     * @param args
     */

    public static int longestSubWithEqual0And1(int arr[]) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 0)
                arr[i] = -1;
        }

        return longestSubarrayWithSum(arr, 0);

    }

    /**
     * Given 2 binary arrays need to find the longest length where sum is same for
     * both arrays. Algo: It can be converted to longest subarray with 0 sum
     * 
     * @param args
     */

    public static int longestCommonSum(int a[], int b[]) {

        // create 3 array
        int temp[] = new int[a.length];

        // values of array will be a[i] - b[i]
        for (int i = 0; i < temp.length; i++) {

            temp[i] = a[i] - b[i];
        }

        // now call this to get the result
        return longestSubarrayWithSum(temp, 0);
    }

    /**
     * Longest common subsequence. Given an array a[1,9,3,4,2,20] - O/P = 4 1,2,3,4
     * are contiguous in array O(N)
     * 
     * @param args
     */

    public static int longestContiguousNumbers(int arr[]) {

        int maxLength = 0;
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < arr.length; i++) {

            set.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {

            int curr = 1;

            if (!set.contains(arr[i] - 1)) {

                while (set.contains(arr[i] + curr)) {
                    curr++;
                }

                maxLength = Math.max(curr, maxLength);
            }

        }

        return maxLength;
    }

    /**
     * Print Count of distinct elements in every window of a given array
     * 
     * @param args
     */

    public static void distinctNumbersInWindow(int arr[], int wSize) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Prefix map technique
        for (int j = 0; j < wSize; j++) {

            if (map.containsKey(arr[j])) {
                int curr = map.get(arr[j]);
                curr++;
                map.replace(arr[j], curr);
            }

            else
                map.put(arr[j], 1);
        }

        System.out.println(map.size());

        for (int i = 1; i <= arr.length - wSize; i++) {

            // decrease or remove i-wSize and add i+wSize

            if (map.get(arr[i - 1]) == 1) {
                map.remove(arr[i - 1]);
            }

            else if (map.get(arr[i - 1]) > 1) {
                int val = map.get(arr[i - 1]);
                val--;
                map.replace(arr[i - 1], val);
            }

            int indexOfEndofWindow = i + wSize - 1;

            if (map.containsKey(arr[indexOfEndofWindow])) {
                int val = map.get(arr[indexOfEndofWindow]);
                val++;
                map.replace(arr[indexOfEndofWindow], val);
            }

            else
                map.put(arr[indexOfEndofWindow], 1);

            System.out.println(map.size());
        }
    }


/**
 * given an array need to print count of number that occurs More than n/k.
 * Where k is second arg
 * @param args
 */

 public static int countOfNK(int arr[], int k){
     
    HashMap<Integer,Integer> map = new HashMap<>();
    int ans = 0;
    int nk = arr.length/k;

    for(int i = 0;i<arr.length;i++){

        if(map.containsKey(arr[i])){

            int val = map.get(arr[i]);
            val++;
            map.replace(arr[i], val);
        }

        else
        map.put(arr[i],1);
    }

    for(Integer key : map.keySet()){

        if( map.get(key) > nk )
            ans++;

    }

    return ans;
 }


    public static void main(String[] args) {

        int arr[] = { 10, 20, 10, 10, 30, 40 };

        distinctNumbersInWindow(arr, 4);

        // int arr[] = { 3, 8, 4, 1, 1, 5, 7 };

        // int a2[] = { 10, 20, 30, 40 };
        // System.out.println(longestContiguousNumbers(a2));

        // System.out.println(longestSubWithEqual0And1(arr));
        // System.out.println(longestSubarrayWithSum(a, 4));

        // System.out.println(subarrayWithSum0(a));

    }
}
