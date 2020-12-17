
import java.util.Deque;
import java.util.LinkedList;

import Heap.MaxHeap;

public class Misc {

    /**
     * Design a data structure that supports following operations O(1) Dequeue
     */

    static Deque<Integer> dq = new LinkedList<>();

    // Min at front end
    public static void insertMin(int val) {
        dq.addFirst(val);
    }

    public static int getMin() {
        return dq.getFirst();
    }

    // Max at back end
    public static void insertMax(int val) {
        dq.addLast(val);
    }

    public static int getMax() {
        return dq.getLast();
    }

    /**
     * Max in all subarrays of size K
     */

    public static void maxOfAllSubarray(int arr[], int k) {

        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < k; i++) {

            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
                dq.removeLast();
            }

            dq.addLast(i);
        }

        for (int i = k; i < arr.length; i++) {

            System.out.print(arr[dq.peekFirst()]);

            while (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.removeFirst();

            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()])
                dq.removeLast();

            dq.addLast(i);

        }

        System.out.print(arr[dq.peekFirst()]);
    }

    /**
     * Sort k-sorted array
     */
    public static void kSortedArray(int arr[]) {

    }

    public static void main(String[] args) {
       
       
       
       
       
       
       
       
       
       
        // MaxHeap heap = new MaxHeap(10);
        // int a[] = { 10, 15, 50, 4, 20 };
        // heap.size = a.length;
        // heap.arr = a;
        // heap.heapSort();

        // for (int x : heap.arr) {
        //     System.out.print(x + " ");
        // }
    }
}
