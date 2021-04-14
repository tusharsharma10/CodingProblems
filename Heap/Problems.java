package Heap;

import java.util.PriorityQueue;

import ibit.Common;

public class Problems {

    public static void kSortedArray(Integer arr[], int k) {

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i <= k && i < arr.length; i++) {

            q.add(arr[i]);
        }

        int index = 0;

        for (int i = k + 1; i < arr.length; i++) {

            arr[index] = q.poll();
            index++;
            q.add(arr[i]);
        }

        while (!q.isEmpty()) {

            arr[index] = q.poll();
            index++;
        }

    }

    /**
     * Purchasing maximum items
     * 
     * @param args
     */

    public static int buyMaxItems(int cost[], int money) {

        int ans = 0;

        // build heap operation, removeMin operation

        buildHeap(cost);

        for (int i = 0; i < cost.length; i++) {

           
            int min = removeMin(cost);
            
            if (min <= money) {
                money -= min;
                ans++;
            }
            else break;
        }
        return ans;
    }

    public static void minHeapify(int arr[], int index) {

        int smallest = index;
        int lchild = (index * 2) + 1;
        int rchild = (index * 2) + 2;
        int size = arr.length;

        if (lchild < size && arr[lchild] <= arr[smallest])
            smallest = lchild;

         if (rchild < size && arr[rchild] <= arr[smallest])
            smallest = rchild;

        if (index != smallest) {

            swap(arr, index, smallest);
            minHeapify(arr, smallest);

        }

    }

    public static void buildHeap(int arr[]) {

        int size = arr.length;

        int index = (size - 2) / 2;

        for (int i = index; i >= 0; i--) {

            minHeapify(arr, i);
        }

    }

    // Min is the first node.
    public static int removeMin(int arr[]) {

        int size = arr.length;
        int minNode = arr[0];

        if (size == 0)
            return Integer.MAX_VALUE;

        if (size == 1) {
            arr[0] = Integer.MAX_VALUE;
            return minNode;
        }

        swap(arr, 0, size - 1);
        arr[size - 1] = Integer.MAX_VALUE;
        minHeapify(arr, 0);

        return minNode;
    }

    public static void swap(int arr[], int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArr(int arr[]) {

        for (int x : arr) {
            System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {

        int arr[] = { 1, 200, 2, 12, 111, 2, 5 };

        // kSortedArray(arr,5);

        System.out.println(buyMaxItems(arr, 10));

        printArr(arr);

    }

}
