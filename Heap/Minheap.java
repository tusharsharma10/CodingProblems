package Heap;

/**
 * Min Heap Implementation Heap is a complete binary tree: MinHeap Properties:
 * 1. Complete Binary Tree 2. Element at root should be smallest at every level.
 */
public class Minheap {

    public int arr[];
    public int size;
    public int capacity;

    public Minheap(int c) {
        arr = new int[c];
        size = 0;
        capacity = c;
    }

    public int getLeftChild(int i) {
        return 2 * i + 1;
    }

    public int getRightChild(int i) {
        return 2 * i + 2;
    }

    public int getParent(int i) {
        return (int) Math.floor((i - 1) / 2);
    }

    public void insert(int val) {

        if (size == capacity)
            return;

        size++;
        arr[size - 1] = val;

        int posn = size - 1;

        while (val > arr[this.getParent(posn)] && posn > 0) {

            swap(arr, val, this.getParent(posn));
            posn = this.getParent(posn);
        }
    }

    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Heapify operation in MinHeap Given a incorrect heap which is violating Min
     * heap properties, i.e. root is not the smallest value. Time - O(logn)
     */
    public void minHeapify(int index) {

        int lChild = this.getLeftChild(index);
        int rChild = this.getRightChild(index);
        int smallest = index;

        if (lChild < size && arr[lChild] < arr[index])
            smallest = lChild;

        if (rChild < size && arr[rChild] < arr[smallest])
            smallest = rChild;

        if (smallest != index) {
            swap(arr, index, smallest);
            minHeapify(smallest);
        }
    }

    /**
     * Remove minimum element from MinHeap Root is the min element in MinHeap but
     * removing it will destroy structure of minheap which we need to ensure.
     */
    public int removeMinimum() {

        if (size == 0)
            return Integer.MAX_VALUE;

        if (size == 1) {
            size--;
            return arr[0];
        }

        int minNode = arr[0];
        arr[0] = arr[size - 1];
        minHeapify(0);
        size--;
        return minNode;

    }

    public void decreaseKey(int value, int index) {

        arr[index] = value;
        int parentIndex = getParent(index);

        while (index != 0 && arr[parentIndex] > arr[index]) {
            swap(arr, index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * Deleting key at a index. Algo: Replace key with Min_VALUE Call removeMinimum
     * 
     * @param index
     */
    public void delete(int index) {

        decreaseKey(Integer.MIN_VALUE, index);
        removeMinimum();
    }

    /**
     * Given a array, we need to convert that array into a MinHeap.
     * O(N)
     */
    public void buildHeap() {

        int index = (size - 2) / 2;
        
        for (int i = index; i >= 0; i--) {
            minHeapify(i);
        }

    }


}
