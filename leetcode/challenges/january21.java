
package leetcode.challenges;

import java.util.HashMap;
import java.util.Map;

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}

public class january21 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;

        ListNode head = new ListNode();
        ListNode temp = head;

        if (l1.val >= l2.val) {
            head.val = l2.val;
            l2 = l2.next;
        }

        else {
            head.val = l1.val;
            l1 = l1.next;
        }

        while (l1 != null && l2 != null) {

            temp.next = new ListNode();
            temp = temp.next;

            if (l1.val >= l2.val) {
                temp.val = l2.val;
                l2 = l2.next;
            }

            else {
                temp.val = l1.val;
                l1 = l1.next;
            }

        }

        while (l1 != null) {

            temp.next = new ListNode();
            temp = temp.next;
            temp.val = l1.val;
            l1 = l1.next;

        }

        while (l2 != null) {

            temp.next = new ListNode();
            temp = temp.next;
            temp.val = l2.val;
            l2 = l2.next;

        }

        return head;
    }

    /**
     * Kth Missing Positive Number
     * 
     * @param arr
     * @param k
     * @return
     */
    public static int findKthPositive(int[] arr, int k) {

        int i = 0;
        int val = 0;

        if (arr[0] != 1) {
            i = arr[0] - 1;

            if (i >= k)
                return k;

            if (arr.length == 1 && i < k) {
                return k + 1;
            }

        }

        for (int j = 1; j < arr.length; j++) {

            if (arr[j] != j + 1) {
                val = i;
                i = i + arr[j] - arr[j - 1] - 1;

                if (i >= k)
                    return arr[j - 1] + k - val;

            }
        }

        return arr[arr.length - 1] + k - i;

    }

    /**
     * Longest Substring Without Repeating Characters
     * 
     * @param args
     */

    public static int lengthOfLongestSubstring(String s) {

        if (s.length() == 0)
            return 0;
        if (s.length() == 0)
            return 1;

        int len = 0;
        int maxLen = 0;

        StringBuilder b = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            int check = posnChecker(b.toString(), s.charAt(i));

            if (check == -1) {
                b.append(s.charAt(i));
                len++;
                maxLen = Math.max(maxLen, len);
            }

            else {

                b.delete(0, check + 1);

                b.append(s.charAt(i));
                len = b.length();
            }

        }

        return maxLen;
    }

    private static int posnChecker(String s, char c) {

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == c)
                return i;
        }

        return -1;
    }

    /**
     * Check If Two String Arrays are Equivalent
     * 
     * @param args
     */

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        StringBuilder b1 = new StringBuilder();
        StringBuilder b2 = new StringBuilder();

        for (String s : word1) {
            b1.append(s);
        }

        for (String s : word2) {
            b2.append(s);
        }

        if (b1.toString().equals(b2.toString()))
            return true;

        return false;
    }

    /**
     * 
     * Merge Sorted Array
     */

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int arr[] = new int[m + n];

        int j = 0;
        int k = 0;
        int i = 0;

        while (n > k && m > j) {

            if (nums1[j] > nums2[k]) {

                arr[i] = nums2[k];
                k++;
            }

            else {
                arr[i] = nums1[j];
                j++;
            }

            i++;

        }

        while (m > j) {
            arr[i] = nums1[j];
            j++;
            i++;
        }

        while (n > k) {
            arr[i] = nums2[k];
            k++;
            i++;
        }

        i = 0;

        for (int x : arr) {

            nums1[i] = x;
            i++;
        }

    }

    /**
     * Minimum Operations to Reduce X to Zero Correct Time limit exceeded. Recursive
     * solution
     * 
     * @param args
     */

    public static int minOperations(int[] nums, int x, int firstInd, int lastInd, int res) {

        if (x == 0)
            return 0;

        if (firstInd > lastInd)
            return Integer.MAX_VALUE - 1000;

        if (x < nums[firstInd] && x < nums[lastInd])
            return Integer.MAX_VALUE - 1000;

        int case1 = 1 + minOperations(nums, x - nums[firstInd], firstInd + 1, lastInd, res);
        int case2 = 1 + minOperations(nums, x - nums[lastInd], firstInd, lastInd - 1, res);

        res = Math.min(Math.min(case1, case2), res);

        return res;
    }

    public static int minOperations(int[] nums, int x) {

        int ans = minOperations(nums, x, 0, nums.length - 1, (Integer.MAX_VALUE - 1000));

        if (ans > 1947482647)
            return -1;

        else
            return ans;

    }

    /**
     * Minimum Operations to Reduce X to Zero Dynamic programming solution
     * Correct
     */

    public static int minOperationsDp(int arr[], int x) {

        int sumArr = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // base case signifying that there is always a value in left subarray having a
        // sum.
        map.put(0, 0);
        for (int i = 0; i < arr.length; i++) {

            sumArr += arr[i];
            map.put(arr[i], i);

        }

        if (x > sumArr)
            return -1;

        int sumNeeded = sumArr - x;

        int longestSubarray = 0;

        int prefSum = 0;

        for (int i = 0; i < arr.length; i++) {

            prefSum += arr[i];

            if (map.containsKey(prefSum - sumNeeded)) {

                if (prefSum - sumNeeded == 0)
                    longestSubarray = Math.max(longestSubarray, i - map.get(prefSum - sumNeeded) + 1);

                else
                    longestSubarray = Math.max(longestSubarray, i - map.get(prefSum - sumNeeded));

            }

        }

        int n = arr.length;
        return longestSubarray == 0 ? (sumNeeded == 0 ? n : -1 ) : n - longestSubarray;

    }

    

    public static void main(String[] args) {

        int arr[] = { 2,3,1,1,1 };

        System.out.println(minOperations(arr, 5));

        // int a1[] = { 1, 2, 3, 0, 0, 0 };
        // int a2[] = { 2, 5, 6 };

        // merge(a1, 3, a2, 3);

        // String s1[] = { "ab", "c" };
        // String s2[] = { "a", "bc" };

        // System.out.println(arrayStringsAreEqual(s1, s2));

        // System.out.println(findKthPositive(arr, 17));

        // int arr1[] = {1,2,4};
        // int arr2[] = {1,3,4};

        // ListNode l1 = arrToList(arr1);
        // ListNode l2 = arrToList(arr2);

        // printList(l1);
        // printList(l2);

        // ListNode head = mergeTwoLists(l1,l2);

        // printList(head);

    }

    public static ListNode arrToList(int arr[]) {

        ListNode head = new ListNode(arr[0]);
        ListNode temp = head;

        for (int i = 1; i < arr.length; i++) {

            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }

        return head;
    }

    public static void printList(ListNode h) {

        while (h != null) {

            System.out.print(h.val + " ");
            h = h.next;
        }
        System.out.println();
    }

}
