import java.util.Scanner;

/**
 * nextLine takes input till Enter is pressed. next method takes input till
 * space is pressed both returns string.
 */
public class GFG {

    public static void addCommas(String s) {

        StringBuilder builder = new StringBuilder();

        String arr[] = s.split(" ");

        for (String str : arr) {
            System.out.print(str + ",");
        }

    }

    public static int maxRainWater(int arr[]) {

        int maxWidth = arr.length - 2;
        int height = Math.min(arr[0], arr[arr.length - 1]);
        int maxWater = height * maxWidth;

        for (int i = 1; i < arr.length - 2; i++) {

            maxWater -= Math.min(height, arr[i]);

        }
        return maxWater;
    }

    public static void inputTaker(Scanner sc) {
        // 1. Take all necessary inputs
        int arr[] = arrayInputTaker(sc);
       String s  = sc.next();
        // 2.Call the core function
        System.out.println(maxRainWater(arr));
    }

    public static int[] arrayInputTaker(Scanner sc) {

        int size = sc.nextInt();
        int arr[] = new int[size];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(sc.next());

        return arr;
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);

        // int t = sc.nextInt();

        // while (t > 0) {
        //     inputTaker(sc);
        //     t--;
        // }

        String s = "9 8 N 5 10 N 5 6 N 6";
            addCommas(s);

      
    }

}
