import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/**
 * Recursion Problems
 */
public class Recursion {

/*
    Variables
*/

static Map<Integer, String> map;

static void rec(int n) {

    if (n == 0)
        return;

    rec(n - 1);
    System.out.println(n);
    rec(n - 1);
}

static int fact(int n, int k) {

    if (n == 1)
        return k;

    k = n * k;

    return fact(n - 1, k);
}

static int naturalSum(int n, int sum) {

    if (n == 0)
        return sum;

    return naturalSum(n - 1, n + sum);
}

/**
 * Count Total Digits in a Number
 */
public static int countDigits(int n) {
    if (n == 0)
        return 0;

    else
        return 1 + countDigits(n / 10);

}

/**
 * Tower of hanoi
 */

public long toh(int N, int from, int to, int aux) {

    return 1l;
}

// Fibonacci using recursion
public static int fib(int n) {

    if (n == 0)
        return 0;

    if (n == 1)
        return 1;

    return fib(n - 2) + fib(n - 1);
}

/**
 * JOSEPHUS PROBLEM, Very difficult Very difficult to understand Should never
 * try this problem
 */
public static int josephus(int n, int k, int ans) {
    if (n == 1)
        return 1;
    else {
        ans = (josephus(n - 1, k, ans) + k - 1) % n + 1;
    }

    return ans;
}

/**
 * Lucky Numbers: Take the set of integers 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
 * 12, 13, 14, 15, 16, 17, 18, 19,…… First, delete every second number, we get
 * following reduced set. 1, 3, 5, 7, 9, 11, 13, 15, 17, 19,………… Now, delete
 * every third number, we get 1, 3, 7, 9, 13, 15, 19,….…. Continue this process
 * indefinitely…… Any number that does NOT get deleted due to above process is
 * called “lucky”.
 * 
 */
// Accepted Solution
public static boolean isLucky(int n) {

    return isLuckyRec(n, n, 2);

}

// iteration > index of Num then Lucky Number
private static boolean isLuckyRec(int n, int index, int iteration) {

    if (index < iteration)
        return true;

    if (index % iteration == 0)
        return false;

    index = index - (index / iteration);
    return isLuckyRec(n, index, ++iteration);
}

/**
 * You are given two numbers n and p. You need to find n^p.
 * 
 * @param args
 */

public static int recursivePower(int n, int p) {
    return power(n, p, 1);
}

private static int power(int n, int p, int i) {
    if (p == i)
        return n;

    n = n * power(n, p, ++i);
    return n;
}

/**
 * Possible Words From Phone Digits
 */

static List<String> possibleWords(int a[]) {
   
        map = new HashMap<>();

        map.put(2, "ABC");
        map.put(3, "DEF");
        map.put(4, "GHI");
        map.put(5, "JKL");
        map.put(6, "MNO");
        map.put(7, "PQRS");
        map.put(8, "TUV");
        map.put(9, "WXYZ");

        List<String> stringList = printWords(a, a.length, 0, "");
        stringList.stream().forEach(System.out::println);

        return stringList;
    }

    private static List<String> printWords(int[] arr, int len, int numIndex, String s) {
       
        //base case returning immutable list containing a single string.
        if (len == numIndex) {
            return new ArrayList<>(Collections.singleton(s));
        }

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i <  map.get(arr[numIndex]).length() ; i++) {
            
            String sCopy = s;
           
            sCopy = sCopy.concat( String.valueOf(map.get(arr[numIndex]).charAt(i))) ;
            
            stringList.addAll( printWords(arr, len, numIndex + 1, sCopy) );
        }
       
        return stringList;
    }

    

    public static void main(String[] args) {
        // System.out.println(fact(6,1));
        // System.out.println(fib(10));
        // System.out.println(josephus(8, 2, -1));

        // System.out.println(isLucky(19));
       // System.out.println(recursivePower(9, 9));

      // int arr[] = {2,3,4};
       //possibleWords(arr);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        for(int  i=0;i<10;i++)
            l1.add(i);

        l2.addAll(l1);
        l2.addAll(l1);
        System.out.println(l2);
    }
}