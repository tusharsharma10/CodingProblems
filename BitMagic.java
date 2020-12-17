import java.util.ArrayList;
import java.util.List;

/**
 * Tough thing is to make those expressions like finding right most set bit.
 */
public class BitMagic {

    // kth bit is set or not
    static void kThBit(int n, int k) {

        int x = n & (1 << k - 1);

        if (x != 0)
            System.out.println("Set");

        else
            System.out.println("Unset");
    }

    /**
     * Count set bits 1 Brian Kerningam Algorithm
     */

    static int countSetBits1(int n) {

        int res = 0;
        while (n > 0) {

            n = n & n - 1;
            res++;
        }

        return res;
    }

    /**
     * Count set bits Lookup table based approach
     * 
     * @param n
     * @return
     */
    static int countSetBits2(int n) {

        return 1;
    }

    /**
     * Check whether a no. is power of 2 using bit manipulation
     */

    static boolean checkPowerOf2(int n) {

        if (n == 0)
            return false;

        return ((n & n - 1) == 0);

    }

    /**
     * Find the only odd occuring number [4,3,4,4,4,5,5] here 3 occurs 1 time 1 is
     * odd. XOR operator - returns 1 if 2 operands are different and 0 if they are
     * same.
     */

    public static int oddOccuring(int arr[]) {
        int res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            res = res ^ arr[i];
        }
        return res;
    }

    /**
     * Find the missing number
     * 
     * @param args
     */

    public static int missingNum(int arr[]) {

        int res1 = 1;
        int res2 = 0;
        for (int i = 0; i < arr.length; i++) {

            res1 = res1 ^ i+2;
            res2 = res2 ^ arr[i];
        }

        int res = res1 ^ res2;
        return res;
    }

    /**
     * Find 2 odd occuring elements
     */

    public static List<Integer> find2OddOccuring(int arr[]){
        
        List<Integer> ans = new ArrayList<Integer>();
        int one = 0;
        int two = 0;
        int res = 0;
        //Find the XOR value
        for(int i=0;i<arr.length;i++){

            res = res ^ arr[i];
        }
        
        //Divide into 2 groups using right most set bit, each group will have one value.
        int rightMostSetBit = res & ~(res-1);
        
        for(int i=0;i<arr.length;i++){

            if( (arr[i] & rightMostSetBit) != 0)
                one = one ^ arr[i];

            else
            two = two ^ arr[i];
        }
        
        ans.add(one);
        ans.add(two);

        return ans;
    }

    public static void main(String[] args) {
        // int arr[] = {3,4,4,4,4,5,5};
        // System.out.println(oddOccuring(arr));

        // int arr2[] = { 1,2,5,6,4,7 };
        // System.out.println(missingNum(arr2));

        int arr3[] = {3,4,4,4,4,5,5,6};
        System.out.println(find2OddOccuring(arr3));

    }
}
