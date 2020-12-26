package ibit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.lang.model.util.ElementScanner6;
import javax.xml.transform.stream.StreamSource;

public class Solution {

    /**
     * Max Sum of triplet
     */
    public static int solve(List<Integer> list) {

        List<Integer> a = list;
        int n = list.size();
        int suffix[] = new int[n + 1];
        suffix[n] = 0;

        // Computing suffix array containing max of 2 values.
        for (int i = n - 1; i >= 0; i--) {

            suffix[i] = Math.max(suffix[i + 1], list.get(i));
        }

        int ans = 0;

        TreeSet<Integer> set = new TreeSet<>();

        set.add(Integer.MIN_VALUE);

        for (int i = 0; i < n - 1; i++) {

            if (suffix[i + 1] > list.get(i)) {

                ans = Math.max(ans, getLowValue(set, list.get(i)) + list.get(i) + suffix[i + 1]);
                set.add(list.get(i));

            }
        }
        return ans;
    }

    // Gives a lower value wrt to a number preceding it but among all the lower it
    // is the maximum
    private static int getLowValue(TreeSet<Integer> set, int curr) {

        return set.lower(curr);
    }

    /**
     * https://www.interviewbit.com/problems/minimum-lights-to-activate/ Greedy Algo
     * based.
     * 
     * @param args
     */

    public static int minLights(int[] A, int B) {
        int n = A.length;
        int i = Math.min(B - 1, n - 1);
        int count = 0;
        // i determines position of light.
        while (i >= 0) {

            if (A[i] == 1) {
                count++;
                A[i] = 2;
                // jump here.
                i += B;

                if (i >= n)
                    break;

                i = Math.min(n - 1, i + B - 1);

            }

            else if (A[i] == 2)
                break;

            else
                i--;
        }

        if (i < n - 1)
            return -1;

        return count;
    }

    /**
     * https://www.interviewbit.com/problems/largest-number/
     */
    public static String largestNumber(final List<Integer> a) {

        String[] arr = new String[a.size()];

        for (int i = 0; i < a.size(); i++) {
            arr[i] = String.valueOf(a.get(i));
        }

        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b);
            }
        });

        StringBuilder sb = new StringBuilder();

        for (String s : arr) {
            sb.append(s);
        }

        if (sb.charAt(0) == '0') { // check if all zeroes are there
            return String.valueOf(0);
        }

        return sb.toString();
    }

    /**
     * Rotate Matrix: https://www.interviewbit.com/problems/rotate-matrix/ Accepted
     * Soln: Best Approach
     * 
     * @param args
     */

    public static void rotate(List<List<Integer>> a) {

        int n = a.size();

        // operation 1
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                // swap
                if (i != j) {
                    int temp = a.get(i).get(j);
                    a.get(i).set(j, a.get(j).get(i));
                    a.get(j).set(i, temp);
                }

            }
        }

        // operation2

        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n - 1;

            while (start < end) {

                int temp = a.get(i).get(start);
                a.get(i).set(start, a.get(i).get(end));
                a.get(i).set(end, temp);
                start++;
                end--;
            }
        }

        for (List<Integer> l : a) {

            for (Integer x : l) {

                System.out.print(x + " ");
            }
            System.out.println();
        }

    }

    /**
     * 
     */
    public static int hammingDistance(final List<Integer> a) {

        int n = a.size();
        int sum = 0;
        int mod = 1000000007;

        for (int i = 0; i < 32; i++) {

            int count = 0;
            for (int j = 0; j < n; j++) {

                if ((a.get(j) & (1 << i)) == 0)
                    count++;

                // counting number of 0 at i-th position

            }
            sum += (2 * count * (n - count)) % mod; // for i-th position value of hamming distance
            sum = sum % mod;

        }

        return sum;
    }

    private static int countSetBits(int n) {

        int count = 0;

        while (n > 0) {

            n = n & n - 1;
            count++;
        }

        return count;
    }

    /**
     * https://www.interviewbit.com/problems/smaller-or-equal-elements/ Accepted.
     */

    public static int smallerEqual(List<Integer> A, int B) {

        int high = A.size() - 1;
        int low = 0;
        int ans = 0;
        int mid = (low + high) / 2;
        boolean flag = false;

        while (high >= low) {

            mid = (low + high) / 2;

            if (B > A.get(mid)) {
                low = mid + 1;
            }

            else if (B < A.get(mid)) {
                high = mid - 1;
            }

            else {
                ans = mid;
                flag = true;
                break;
            }

        }

        if (!flag)
            ans = low;

        if (flag) {
            for (int i = mid; i < A.size(); i++) {

                if (A.get(i) == B)
                    ans++;
                else
                    break;
            }
        }

        return ans;
    }

    /**
     * https://www.interviewbit.com/problems/woodcutting-made-easy/ Help Ojas find
     * the maximum integer height of the sawblade that still allows him to cut off
     * at least B metres of wood.
     * 
     * @param args Accepted
     */

    public static int woodcutting(List<Integer> A, int B) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int ht = 0;

        for (int i = 0; i < A.size(); i++) {

            max = Math.max(A.get(i), max);
            min = Math.min(A.get(i), min);
        }

        boolean flag = false;

        while (max >= min) {

            int mid = (min + max) / 2;

            if (getMinSumWood(A, mid) < B)
                max = mid - 1;

            else if (getMinSumWood(A, mid) > B)
                min = mid + 1;

            else {
                flag = true;
                ht = mid;
                break;
            }

        }

        if (!flag)
            ht = max;

        return ht;
    }

    private static long getMinSumWood(List<Integer> A, int mid) {

        long minSum = 0;

        for (int i = 0; i < A.size(); i++) {

            if (A.get(i) > mid) {

                minSum += A.get(i) - mid;
            }
        }

        return minSum;
    }

    /**
     * https://www.interviewbit.com/problems/matrix-search/ Accepted
     * 
     * @param args
     */

    public static int searchMatrix(ArrayList<ArrayList<Integer>> A, int B) {

        int n = A.size();
        int m = A.get(0).size();

        int i = 0;
        int j = m - 1;

        while (i < n && j >= 0) {

            if (A.get(i).get(j) < B)
                i++;

            else if (A.get(i).get(j) > B)
                j--;

            else
                return 1;

        }

        return 0;
    }

    /**
     * https://www.interviewbit.com/problems/palindrome-string/ Accepted
     * 
     * @param args
     */

    public static int isPalindrome(String A) {

        A = A.replaceAll("\\s", "");

        StringBuilder b = new StringBuilder();

        for (int i = 0; i < A.length(); i++) {

            int x = A.charAt(i);

            if ((x >= 48 && x <= 57) || (x >= 65 && x <= 90) || (x >= 97 && x <= 122)) {
                b.append(A.charAt(i));
            }

        }

        A = b.toString().toLowerCase();

        int i = 0;
        int j = A.length() - 1;

        while (j >= i) {

            if (A.charAt(i) == A.charAt(j)) {
                i++;
                j--;
            }

            else
                return 0;

        }

        return 1;
    }

    /**
     * https://www.interviewbit.com/problems/pair-with-given-difference/ Pair With
     * Given Difference Accepted
     * 
     * @param args
     */

    public static int pairWithDifference(List<Integer> A, int B) {

        Collections.sort(A);

        int i = 0;
        int j = 1;
        B = Math.abs(B);

        while (j < A.size() && i < A.size()) {

            int diff = A.get(j) - A.get(i);

            if (i == j) {
                j++;
                continue;
            }

            if (diff > B) {
                i++;
            }

            else if (diff < B) {

                j++;
            }

            else if (i != j && diff == B) {
                return 1;
            }

        }

        return 0;
    }

    /**
     * https://www.interviewbit.com/problems/3-sum/ 3 Sum Accepted
     * 
     * @param args
     */

    public static int threeSumClosest(List<Integer> A, int B) {

        Collections.sort(A);
        int n = A.size();

        int closestSum = 10000000;

        for (int i = 0; i < n; i++) {

            int j = i + 1;
            int k = n - 1;

            while (k > j) {

                int sum = A.get(i) + A.get(j) + A.get(k);

                int diff1 = sum - B;
                int diff2 = closestSum - B;

                if (Math.abs(diff1) < Math.abs(diff2))
                    closestSum = sum;

                if (sum > B)
                    k--;

                else
                    j++;

            }

        }

        if (closestSum == Integer.MIN_VALUE || closestSum == Integer.MAX_VALUE)
            closestSum = 0;

        return closestSum;
    }

    /**
     * https://www.interviewbit.com/problems/3-sum-zero/ Accepted
     */

    public static ArrayList<ArrayList<Integer>> threeSum(List<Integer> A) {

        Collections.sort(A);

        LinkedHashSet<ArrayList<Long>> ans = new LinkedHashSet<>();

        int n = A.size();

        for (int i = 0; i < n; i++) {

            int j = i + 1;
            int k = n - 1;

            while (k > j) {

                long ai = A.get(i);
                long aj = A.get(j);
                long ak = A.get(k);

                long sum = ai + aj + ak;

                if (sum == 0) {

                    ArrayList<Long> l = new ArrayList<>();
                    l.add(Long.valueOf(A.get(i)));
                    l.add(Long.valueOf(A.get(j)));
                    l.add(Long.valueOf(A.get(k)));

                    ans.add(l);
                    j++;
                    k--;
                }

                else if (sum > 0)
                    k--;

                else if (sum < 0)
                    j++;

            }

        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        for (ArrayList<Long> s : ans) {

            ArrayList<Integer> l = new ArrayList<>();

            for (Long x : s) {

                l.add(Integer.valueOf(x.toString()));

            }

            res.add(l);
        }

        return res;

    }

    /**
     * https://www.interviewbit.com/problems/search-for-a-range/ Accepted
     */
    public static ArrayList<Integer> searchRange(final List<Integer> A, int B) {

        int high = A.size() - 1;
        int low = 0;
        int posn = -1;

        ArrayList<Integer> ans = new ArrayList<>();

        while (high >= low) {

            int mid = (low + high) / 2;

            if (A.get(mid) > B)
                high = mid - 1;

            else if (A.get(mid) < B)
                low = mid + 1;

            else {
                posn = mid;
                ans.add(posn);
                ans.add(posn);
                break;
            }
        }

        if (posn == -1) {
            ans.add(-1);
            ans.add(-1);
            return ans;
        }

        for (int i = posn - 1; i >= 0; i--) {

            if (A.get(i) == B) {
                ans.set(0, i);
            }

            else
                break;
        }

        for (int i = posn + 1; i < A.size(); i++) {

            if (A.get(i) == B) {
                ans.set(1, i);
            }

            else
                break;
        }

        return ans;
    }

    /**
     * https://www.interviewbit.com/problems/sorted-insert-position/ Accepted
     * 
     * @param args
     */

    public static int searchInsert(ArrayList<Integer> a, int B) {

        int posn = -1;
        int low = 0;
        int high = a.size() - 1;

        while (high >= low) {

            int mid = (low + high) / 2;

            if (a.get(mid) > B)
                high = mid - 1;

            else if (a.get(mid) < B)
                low = mid + 1;

            else {
                posn = mid;
                return posn;
            }
        }

        return low;
    }

    /**
     * https://www.interviewbit.com/problems/matrix-median/ Accepted
     * 
     * @param args
     */

    public static int findMedian(List<List<Integer>> A) {

        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < A.size(); i++) {

            for (int j = 0; j < A.get(0).size(); j++) {

                l.add(A.get(i).get(j));
            }
        }

        Collections.sort(l);

        return l.get(l.size() / 2);
    }

    /**
     * https://www.interviewbit.com/problems/square-root-of-integer/ Accepted
     * 
     * @param args
     */

    public static int sqrt(int A) {

        if (A == 0 || A == 1)
            return A;

        long low = 1;
        long high = A;
        long ans = 0;
        while (high >= low) {

            long mid = (low + high) / 2;

            if (mid * mid == A)
                return Integer.parseInt(String.valueOf(mid));

            else if (mid * mid > A) {

                high = mid - 1;
            } else {
                low = mid + 1;
                ans = mid;
            }
        }

        return Integer.parseInt(String.valueOf(ans));
    }

    /**
     * https://www.interviewbit.com/problems/allocate-books/ Accepted - A
     * 
     * @param args
     */

    public static int books(List<Integer> A, int m) {

        long sum = 0;
        int n = A.size();

        if (n < m)
            return -1;

        for (int i = 0; i < n; i++)
            sum += A.get(i);

        int start = 0, end = (int) sum;
        int result = Integer.MAX_VALUE;

        while (start <= end) {

            int mid = (start + end) / 2;

            if (isPossible(A, n, m, mid)) {
                result = Math.min(result, mid);

                end = mid - 1;
            }

            else
                start = mid + 1;

        }

        return result;
    }

    private static boolean isPossible(List<Integer> A, int n, int m, int curr_min) {
        int studentsRequired = 1;
        int curr_sum = 0;

        for (int i = 0; i < n; i++) {

            // no. of pages allocated can't be less than a book size.
            if (A.get(i) > curr_min)
                return false;

            if (curr_sum + A.get(i) > curr_min) {
                studentsRequired++;

                curr_sum = A.get(i);

                if (studentsRequired > m)
                    return false;
            }

            else
                curr_sum += A.get(i);
        }

        return true;
    }

    /**
     * https://www.interviewbit.com/problems/longest-common-subsequence/
     */

    public int lcs(String A, String B) {

        int n = A.length() + 1;
        int m = B.length() + 1;
        int dp[][] = new int[n][m];

        // Base Cases:
        for (int i = 0; i < n; i++) {

            dp[i][0] = 0;
        }

        for (int i = 0; i < m; i++) {

            dp[0][i] = 0;
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j < m; j++) {

                if (A.charAt(i - 1) == B.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else {

                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    /**
     * https://www.interviewbit.com/problems/longest-palindromic-subsequence/
     * 
     * @param args
     */

    public int lps(String A) {

        StringBuilder s = new StringBuilder(A);
        String B = s.reverse().toString();

        return lcs(A, B);
    }

    /**
     * https://www.interviewbit.com/problems/edit-distance/
     * 
     * @param args
     */

    public static int minDistance(String A, String B) {

        int n = A.length() + 1;
        int m = B.length() + 1;

        int dp[][] = new int[n][m];

        // Base Cases:

        for (int i = 0; i < n; i++) {

            dp[i][0] = i;
        }

        for (int i = 0; i < m; i++) {

            dp[0][i] = i;
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j < m; j++) {

                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                else {

                    // insert
                    int c1 = dp[i - 1][j];
                    // delete
                    int c2 = dp[i][j - 1];
                    // update
                    int c3 = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(c1, Math.min(c2, c3));
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    /**
     * https://www.interviewbit.com/problems/colorful-number/ A
     * 
     * @param args
     */

    public static int colorful(int A) {

        StringBuilder str = new StringBuilder();
        HashSet<Integer> set = new HashSet<>();

        while (A > 0) {

            int n = A % 10;
            str.append(n);
            A /= 10;

        }

        str.reverse();

        for (int i = 0; i < str.length(); i++) {

            int prod = 1;
            for (int j = i; j < str.length(); j++) {

                prod *= (int) str.charAt(j) - '0';

                if (set.contains(prod))
                    return 0;

                set.add(prod);

            }
        }

        return 1;
    }

    /**
     * https://www.interviewbit.com/problems/2-sum/
     * 
     * @param args
     */

    public static ArrayList<Integer> twoSum(final List<Integer> A, int B) {

        ArrayList<Integer> ans = new ArrayList<>();

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        int currIndex = Integer.MAX_VALUE;

        for (int i = 0; i < A.size(); i++) {

            if (!map.containsKey(A.get(i)))
                map.put(A.get(i), i);
        }

        // for (int key : map.keySet()) {

        // int k = B - key;

        // if (!map.containsKey(k))
        // continue;

        // int index1 = map.get(key);
        // int index2 = map.get(k);

        // if (index1 <= index2 && currIndex >= index2) {

        // currIndex = Math.min(index2, currIndex);

        // if (ans.size() == 0) {

        // ans.add(index1+1);
        // ans.add(currIndex+1);
        // }

        // else {
        // ans.set(0, index1+1);
        // ans.set(1, currIndex+1);
        // }

        // }

        // }

        for (int i = 0; i < A.size(); i++) {

            for (int j = i + 1; j < A.size(); j++) {

                if (A.get(i) + A.get(j) == B && currIndex > j) {

                    if (ans.size() == 0) {
                        ans.add(i);
                        ans.add(j);
                    }

                    else {
                        ans.set(0, i + 1);
                        ans.set(1, j + 1);
                    }

                    currIndex = j;
                }

            }
        }

        return ans;
    }

    /**
     * https://www.interviewbit.com/problems/4-sum/
     */

    public static ArrayList<ArrayList<Integer>> fourSum(ArrayList<Integer> a, int b) {

        Collections.sort(a);

        ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < a.size() - 3; i++) {

            for (int j = i + 1; j < a.size() - 2; j++) {

                int tot = b - a.get(i) - a.get(j);

                int k = j + 1;
                int l = a.size() - 1;

                while (k < l) {

                    if (a.get(k) + a.get(l) > tot)
                        l--;

                    else if (a.get(k) + a.get(l) < tot)
                        k++;

                    else {

                        ArrayList<Integer> temp = new ArrayList<Integer>();

                        temp.add(a.get(i));

                        temp.add(a.get(j));

                        temp.add(a.get(k));

                        temp.add(a.get(l));

                        if (!out.contains(temp))
                            out.add(temp);
                        k++;
                        l--;
                    }
                }
            }
        }

        return out;
    }

    /**
     * https://www.interviewbit.com/problems/perfect-peak-of-array/ A
     * 
     * @param args
     */

    public static int perfectPeak(List<Integer> A) {

        int n = A.size();

        for (int i = 1; i < n; i++) {

            boolean flag = true;

            int num = A.get(i);

            for (int j = 0; j < i; j++) {

                if (num > A.get(j)) {
                    continue;
                }

                else {
                    flag = false;
                    break;
                }

            }

            for (int k = i + 1; k < n; k++) {

                if (flag && num < A.get(k)) {
                    continue;
                }

                else {
                    flag = false;
                    break;
                }

            }

            if (flag)
                return 1;

        }

        return 0;
    }

    /**
     * https://www.interviewbit.com/problems/vowel-and-consonant-substrings/
     * 
     * @param args
     */

    public static int vowel(String A) {

        int mod = (int) Math.pow(10, 9) + 7;

        long count = 0;

        for (int i = 0; i < A.length(); i++) {

            for (int j = i + 2; j < A.length(); j++) {

                String sub = A.subSequence(i, j).toString();

                if (

                (sub.charAt(0) == 'a' || sub.charAt(0) == 'e' || sub.charAt(0) == 'i' || sub.charAt(0) == 'o'
                        || sub.charAt(0) == 'u')

                        &&

                        (sub.charAt(j - i - 1) != 'a' && sub.charAt(j - i - 1) != 'e' && sub.charAt(j - i - 1) != 'i'
                                && sub.charAt(j - i - 1) != 'o' && sub.charAt(j - i - 1) != 'u')

                ) {
                    count++;
                }

                else if (

                (sub.charAt(0) != 'a' && sub.charAt(0) != 'e' && sub.charAt(0) != 'i' && sub.charAt(0) != 'o'
                        && sub.charAt(0) != 'u')

                        && (sub.charAt(j - i - 1) == 'a' || sub.charAt(j - i - 1) == 'e' || sub.charAt(j - i - 1) == 'i'
                                || sub.charAt(j - i - 1) == 'o' || sub.charAt(j - i - 1) == 'u')

                ) {

                    count++;
                }

            }

        }

        return (int) count % mod;
    }


    /**
     * Accepted TLE:
     * Use Prefix technique.
     * https://www.geeksforgeeks.org/substrings-starting-vowel-ending-consonants-vice-versa/
     */

    public static int vowel2(String A) {

        int n = A.length();

        int mod = (int)Math.pow(10, 9) + 7;

        String vowel = "[aeiuo]";
        String consonant = "[^aeiuo]";
        int count = 0;

        for(int i  =0;i<n;i++){

            for(int j = i+2;j<n+1;j++){

                String sub = A.substring(i, j);

                if(String.valueOf(sub.charAt(0)).matches(vowel) &&  String.valueOf(sub.charAt(j-i-1)).matches(consonant))
                    count++;

                else 
                    if(String.valueOf(sub.charAt(0)).matches(consonant) &&  String.valueOf(sub.charAt(j-i-1)).matches(vowel))
                        count++;

            }
        }

        return count % mod;
    }


    public static void main(String[] args) {

        List<Integer> A = Arrays.asList(5, 1, 4, 4);

        String a = "inttnikjmjbemrberk";

        System.out.println(vowel2(a));

        // System.out.println(perfectPeak(A));

        // System.out.println(fourSum(A, 0));

        // System.out.println(twoSum(A, 2));

        // System.out.println(colorful(23));

        // System.out.println(books(A, 2));

        // List<List<Integer>> list = new ArrayList<>();
        // List<Integer> a = Arrays.asList(1, 3, 5);
        // List<Integer> b = Arrays.asList(2, 6, 9);
        // List<Integer> c = Arrays.asList(3, 6, 9);
        // List<Integer> d = Arrays.asList(13,14,15,16);

        // list.add(a);
        // list.add(b);
        // list.add(c);
        // list.add(d);

        // System.out.println(findMedian(list));

        // System.out.println(threeSum(l));

        // System.out.println(threeSumClosest(l, -1));

        // System.out.println(pairWithDifference(l, -42));

        // String a = "A man, a plan, a canal: Panama";
        // System.out.println(isPalindrome(a));

        // System.out.println(woodcutting(l, 95));

        // System.out.println(smallerEqual(l,28));

        // System.out.println(hammingDistance(l));

        // System.out.println(1<<0);

        // rotate(list);
        // List<Integer> list = Arrays.asList(3, 30, 34, 5, 9);
        // largestNumber(list);
        // int arr[] = { 0, 0, 1, 1, 1, 0, 0, 0 };
        // System.out.println(minLights(arr, 3));
        // System.out.println(solve(list));
    }
}

class Pair {

    int one;
    int second;

    Pair(int one, int second) {
        this.one = one;
        this.second = second;
    }
}