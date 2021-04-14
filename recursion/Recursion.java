package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Recursion {

    // max of an array
    public static int maxRec(int arr[], int n, int max) {

        if (n < 0)
            return max;

        max = Math.max(arr[n], max);

        return maxRec(arr, n - 1, max);

    }

    // all subsequences
    public static void printSubSeqRec(String str, int n, int index, String curr) {
        // base case
        if (index == n) {
            return;
        }

        if (curr != null && !curr.trim().isEmpty()) {
            System.out.println(curr);
        }

        for (int i = index + 1; i < n; i++) {
            curr += str.charAt(i);
            printSubSeqRec(str, n, i, curr);

            // backtracking
            curr = curr.substring(0, curr.length() - 1);
        }
    }

    /**
     * Correct Solution
     */

    public static ArrayList<String> getKPC(String str) {

        HashMap<String, String> map = new HashMap<>();

        map.put("0", ".;");
        map.put("1", "abc");
        map.put("2", "def");
        map.put("3", "ghi");
        map.put("4", "jkl");
        map.put("5", "mno");
        map.put("6", "pqrs");
        map.put("7", "tu");
        map.put("8", "vwx");
        map.put("9", "yz");

        ArrayList<String> ans = new ArrayList<>();

        return getKPC(str, -1, ans, map, "");

    }

    private static ArrayList<String> getKPC(String str, int index, ArrayList<String> ans, HashMap<String, String> map,
            String curr) {

        if (index == str.length())
            return ans;

        if (curr != null && curr.length() == str.length()) {
            ans.add(curr);
        }

        for (int i = index + 1; i < str.length(); i++) {

            String s = String.valueOf(str.charAt(i));

            for (int j = 0; j < map.get(s).length(); j++) {

                curr += map.get(s).charAt(j);

                getKPC(str, i, ans, map, curr);
                // backtrack
                curr = curr.substring(0, curr.length() - 1);
            }
        }

        return ans;
    }

    /**
     * steps taken can be 1,2,3.
     * 
     * @param args
     */

    private static ArrayList<String> getStairPaths(int n) {

        if (n == 0) {
            ArrayList<String> a = new ArrayList<>();
            a.add("");
            return a;
        }

        else if (n < 0) {

            return new ArrayList<>();
        }

        ArrayList<String> a1 = getStairPaths(n - 1);
        ArrayList<String> a2 = getStairPaths(n - 2);
        ArrayList<String> a3 = getStairPaths(n - 3);

        ArrayList<String> paths = new ArrayList<>();

        for (String s : a1) {

            paths.add("1" + s);
        }

        for (String s : a2) {

            paths.add("2" + s);
        }

        for (String s : a3) {

            paths.add("3" + s);
        }

        return paths;

    }

    /**
     * Maze paths
     * 
     * @param args
     */

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {

        if (sr == dr && sc == dc) {

            ArrayList<String> a = new ArrayList<>();
            a.add("");
            return a;
        }

        ArrayList<String> hPath = new ArrayList<>();
        ArrayList<String> vPath = new ArrayList<>();

        if (sc < dc) {

            hPath = getMazePaths(sr, sc + 1, dr, dc);
        }

        if (sr < dr) {

            vPath = getMazePaths(sr + 1, sc, dr, dc);
        }

        ArrayList<String> paths = new ArrayList<>();

        for (String s : hPath) {

            paths.add("h" + s);
        }

        for (String s : vPath) {

            paths.add("v" + s);
        }

        return paths;
    }

    /**
     * Get maze path with jumps
     * 
     * @param args
     */

    public static ArrayList<String> getMazePathsJump(int sr, int sc, int dr, int dc) {

        if (sr == dr && sc == dc) {
            ArrayList<String> a = new ArrayList<>();
            a.add("");
            return a;

        }

        ArrayList<String> paths = new ArrayList<>();

        for (int i = 1; i <= dr - sr; i++) {
            ArrayList<String> hPaths = getMazePathsJump(sr + i, sc, dr, dc);

            for (String h : hPaths) {
                paths.add("h" + i + h);
            }
        }

        for (int i = 1; i <= dc - sc; i++) {
            ArrayList<String> vPaths = getMazePathsJump(sr, sc + i, dr, dc);
            for (String v : vPaths) {
                paths.add("v" + i + v);
            }
        }

        return paths;

    }

    /**
     * Print permutations
     */

    public static void printPermutations(String a, String ans) {

        if (a.length() == 0) {

            System.out.println(ans);
            return;
        }

        for (int i = 0; i < a.length(); i++) {

            char ch = a.charAt(i);
            String left = a.substring(0, i);
            String right = a.substring(i + 1);

            // rest of question
            String roq = left + right;

            printPermutations(roq, ans + ch);
        }

    }

    /**
     * Print encodings
     */

    public static void printEncodings(String str) {

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            int c = Integer.parseInt(String.valueOf(str.charAt(i)));

            char ch = (char) (96 + c);

            s.append(ch);
        }

        System.out.println(s.toString());
    }

    /**
     * Flood Fill
     * 
     * @param args
     */

    public static void floodfill(int[][] maze) {

            
    }

    public static void main(String[] args) {

        printEncodings("655196");

        // printPermutations("abc", "");

        // System.out.println(getMazePathsJump(1, 1, 2, 2));

        // System.out.println(getMazePaths(1, 1, 3, 3));

        // int n = 7;
        // System.out.println(getStairPaths(n));

        // int arr[] = {23121,12,0,-1,1100,1222,121,3113};

        // String a = "abc";
        // Set<String> set = new HashSet<>();

        // System.out.println(getKPC("78"));

        // printSubSeqRec(a, a.length(), -1, "");

        // System.out.println(set);

    }
}
