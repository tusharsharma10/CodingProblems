import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Class containing standard string problems
 */

class LeftChar {

    int maxVal;
    int firstOccurence;

}

public class StringsMagic {

    /**
     * Checking for Anagrams: all characters in s1 should be in s2 Efficient Soln:
     * Time - O(N) Space - O(1) If sum and product of set of numbers is same then
     * numbers in both sets are same.
     * 
     * @param args
     */

    public static boolean checkAnagrams(String s1, String s2) {

        // compare lengths
        if (s1.length() != s2.length())
            return false;

        int sum1 = 0;
        long prod1 = 1l;
        long prod2 = 1l;

        for (int i = 0; i < s1.length(); i++) {

            sum1 += s1.charAt(i);
            sum1 -= s2.charAt(i);

            prod1 *= (s1.charAt(i));
            prod2 *= (s2.charAt(i));
        }

        if (sum1 == 0 && prod1 == prod2)
            return true;

        else
            return false;

    }

    /**
     * Leftmost Max repeating character: To find character which occurs more than
     * any other character and if 2 characters occur same time then return leftmost.
     * Most efficient
     * 
     * @param args
     */

    public static int leftRepeatingChar(String s) {

        HashMap<Character, LeftChar> map = new HashMap<>();
        char c = '\0';

        LeftChar val = new LeftChar();

        for (int i = 0; i < s.length(); i++) {

            c = s.charAt(i);

            if (map.containsKey(c)) {
                val = map.get(c);
                val.maxVal++;
                map.replace(c, val);
            }

            else {
                val = new LeftChar();
                val.firstOccurence = i;
                val.maxVal = 1;
                map.put(c, val);
            }

        }

        int maxVal = 0;
        int maxPosn = Integer.MAX_VALUE;

        for (char x : map.keySet()) {

            val = map.get(x);

            if (maxVal == val.maxVal && val.firstOccurence < maxPosn)
                maxPosn = val.firstOccurence;

            if (maxVal < val.maxVal) {
                maxPosn = val.firstOccurence;
                maxVal = val.maxVal;
            }

        }

        return maxPosn;
    }

    /**
     * Leftmost non repeating character IF all character repeats o/p = -1 else
     * return leftMost non repeating char
     * 
     * @param args
     */

    public static int leftMostNonRepeatingChar(String s) {

        HashMap<Character, LeftChar> map = new HashMap<>();
        char c = '\0';

        LeftChar val = new LeftChar();

        // Filling MAP
        for (int i = 0; i < s.length(); i++) {

            c = s.charAt(i);

            if (map.containsKey(c)) {
                val = map.get(c);
                val.maxVal++;
                map.replace(c, val);
            }

            else {
                val = new LeftChar();
                val.firstOccurence = i;
                val.maxVal = 1;
                map.put(c, val);
            }

        }

        // Map filled

        // int maxVal = 0;
        int maxPosn = Integer.MAX_VALUE;

        for (char x : map.keySet()) {

            val = map.get(x);

            if (val.maxVal < 2 && maxPosn > val.firstOccurence) {
                maxPosn = val.firstOccurence;
            }
        }

        return maxPosn == Integer.MAX_VALUE ? -1 : maxPosn;
    }

    /**
     * Reverse words in a String Word is characterized by space.
     * 
     * @param args
     */

    public static String reverseString(String s) {

        String arr[] = s.split(" ");
        StringBuilder str = new StringBuilder();

        for (int i = arr.length - 1; i >= 0; i--) {

            str.append(arr[i]);
        }

        return str.toString();

    }

    /**
     * Pattern Searching: Given string and pattern, need to find all indexes where
     * pattern occurs. Rabin Karp Algorithm O(NM) N - length of string M length of
     * pattern
     * 
     * @param args
     */

    public static List<Integer> searchPattern(String s, String pattern) {

        List<Integer> ans = new ArrayList<>();

        int patternHash = 0;
        int tempHash = 0;

        for (int i = 0; i < pattern.length(); i++) {
            // calculating pattern hash
            patternHash += (pattern.charAt(i) - 'a');
            // calculating first hash of string to be used in rolling hash.
            tempHash += (s.charAt(i) - 'a');
        }

        for (int i = 1; i <= s.length() - pattern.length(); i++) {

            // Rolling Hash Optimisation
            tempHash = tempHash + s.charAt(i + pattern.length() - 1) - s.charAt(i - 1);

            // If hashcode matches then search char by char.
            if (tempHash == patternHash && s.substring(i, i + pattern.length()).equals(pattern))
                ans.add(i);

        }

        return ans;

    }

    /**
     * Check if strings are rotations. To find s1 can be obtained from s2 by
     * rotating s2 any number of times. O(N) time Concatenate s1 with itself and
     * then search whether s2 is present as a substring in it using indexOf
     * function.
     * 
     * @param args
     */

    public static boolean isStringRotation(String s1, String s2) {

        if (s1.length() != s2.length())
            return false;

        return (s1.concat(s1).contains(s2));
    }

    /**
     * Pattern anagram is present or not in string. They should be contiguous.
     * 
     * @param args
     */

    public static boolean hasPatternAnagram(String s, String pattern) {

        int stringCounter[] = new int[256];
        int patternCounter[] = new int[256];

        // precomputation for rolling hash
        for (int i = 0; i < pattern.length(); i++) {

            patternCounter[pattern.charAt(i)]++;
            stringCounter[s.charAt(i)]++;
        }

        for (int i = 1; i <= s.length() - pattern.length(); i++) {

            if (compareArrays(stringCounter, patternCounter))
                return true;

            stringCounter[s.charAt(i - 1)]--;
            stringCounter[s.charAt(i + pattern.length() - 1)]++;
        }

        return false;
    }

    /**
     * 
     * All values in both arrays should be identical.
     */
    private static boolean compareArrays(int arr1[], int arr2[]) {

        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {

            if (arr1[i] != arr2[i])
                return false;

        }

        return true;
    }

    /**
     * Lexicographic rank of string. O(N) time
     * 
     * @param args
     */

    public static int rankLexic(String s) {

        int maxFact = fact(s.length());
        int n = s.length();
        int count[] = new int[256];
        int res = 1;

        // Making all the chars in string as 1
        for (int i = 0; i < n; i++)
            count[s.charAt(i)]++;

        // cumulative counts : overall smaller character than present character
        for (int i = 1; i < 256; i++)
            count[i] += count[i - 1];

        for (int i = 0; i < n - 1; i++) {

            maxFact = maxFact / (n - i);
            res = res + count[s.charAt(i) - 1] * maxFact;

            // decreased index of every character after a accompanied char by 1
            for (int j = s.charAt(i); j < 256; j++)
                count[j]--;
        }

        return res;

    }

    public static int fact(int n) {

        int x = 1;
        for (int i = 2; i <= n; i++) {
            x = x * i;
        }

        return x;
    }

    /**
     * Longest substring with all distinct characters Substring - Consecutive
     * characters O(N) time https://ide.geeksforgeeks.org/fe7ZNRJqfm
     * 
     * @param args
     */
    public static int longestDistinctSubstring(String s) {

        int res = 0;

        int prev[] = new int[256];

        Arrays.fill(prev, -1);

        int i = 0;

//i - inital index, j - final index
// prev stores the posn of character at which last occur in string
// if a char never appeared before appears maxEnd++

        for (int j = 0; j < s.length(); j++) {

            i = Math.max(i, prev[s.charAt(j)] + 1);

            int maxEnd = j - i + 1;

            res = Math.max(res, maxEnd);

            prev[s.charAt(j)] = j;
        }

        return res;

    }

/**
 * KMP algorithm
 * @param args
 */


    /**
     * Main Method
     */
    public static void main(String[] args) {

        String s1 = "geeksforgeeks";
        String s2 = "eeksg";

        System.out.println(hasPatternAnagram(s1, s2));
        // System.out.println(searchPattern(s1,s2));
        // System.out.println(leftMostNonRepeatingChar(s1));
        // System.out.println(checkAnagrams(s1, s2));
    }

}
