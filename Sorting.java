import java.util.ArrayList;
import java.util.List;

public class Sorting {

    /**
     * Insertion Sort Use heap sort or merge sort lor large size arrays when array
     * size is small use insertion sort.
     */

    public static void insertionSort(int arr[]) {

        for (int i = 1; i < arr.length; i++) {

            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

    }

    /**
     * Intersection of 2 sorted arrays O(N) time
     */

    public static List<Integer> intersectionOfSorted(int a[], int b[]) {

        List<Integer> list = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {

            if (a[i] > b[j]) {
                list.add(b[j]);
                j++;
            }

            else if (a[i] == b[j]) {
                list.add(a[i]);
                i++;
                j++;
            }

            else if (a[i] < b[j]) {

                list.add(a[i]);
                i++;
            }
        }

        // left over elements

        while (i < a.length) {
            list.add(a[i]);
            i++;
        }

        while (j < b.length) {
            list.add(b[j]);
            j++;
        }

        return list;
    }

    /**
     * Union of 2 sorted arrays
     * 
     */

    public static List<Integer> unionSortedArrays(int a[], int b[]) {

        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {

            if (a[i] > b[j]) {
                list.add(b[j]);
                j++;
            }

            else if (a[i] == b[j]) {
                list.add(a[i]);
                list.add(b[j]);
                i++;
                j++;
            }

            else if (a[i] < b[j]) {

                list.add(a[i]);
                i++;
            }
        }

        // left over elements

        while (i < a.length) {
            list.add(a[i]);
            i++;
        }

        while (j < b.length) {
            list.add(b[j]);
            j++;
        }

        return list;
    }

    /**
     * Count inversions in array: Inversion is - arr[i] > arr[j] where j > i
     * https://ide.geeksforgeeks.org/KekPYxIWsM O(nlogn) time
     */

    static int countInversion(int arr[], int l, int r) {

        int res = 0;

        if (l < r) {

            int m = (r + l) / 2;

            // count inversions in left half
            res += countInversion(arr, l, m);
            // count inversions in right half
            res += countInversion(arr, m + 1, r);

            res += countAndMerge(arr, l, m, r);
        }

        return res;
    }

    static int countAndMerge(int arr[], int l, int m, int r) {

        int n1 = m - l + 1, n2 = r - m;

        int[] left = new int[n1];

        int[] right = new int[n2];

        // fill left array with lower limit to mid
        for (int i = 0; i < n1; i++)
            left[i] = arr[i + l];

        for (int j = 0; j < n2; j++)
            right[j] = arr[m + 1 + j];

        int res = 0, i = 0, j = 0, k = l;

       // inversion occurs when element of right array is < element of left array.
        
       while (i < n1 && j < n2) {

            if (left[i] <= right[j]) {

                arr[k++] = left[i++];
            }

            // inversion case
            else {

                arr[k++] = right[j++];
                
                // n1 -i is number of elments in left array after i.
                // they add up to res since they will form a inversion.
                res = res + (n1 - i);
            }

        }

        // fill remaining array
        while (i < n1)
            arr[k++] = left[i++];

        while (j < n2)
            arr[k++] = right[j++];

        return res;
    }

    /**
     * 
     * Swap method
     */
    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int a[] = { 3, 5, 10, 10, 10, 15, 15, 20 };
        int b[] = { 0, 0, 1, 2, 3, 4, 5, 10, 10, 15, 30 };

        System.out.println(unionSortedArrays(a, b));
    }
}
