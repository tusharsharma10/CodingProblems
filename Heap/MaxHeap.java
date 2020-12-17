package Heap;

public class MaxHeap {

    public int arr[];
    public int size;
    public int capacity;

    public MaxHeap(int c) {
        arr = new int[c];
        capacity = c;
        size = 0;
    }

    public int getLeftChild(int index) {
        return 2 * index + 1;
    }

    public int getRightChild(int index) {
        return 2 * index + 2;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * For a particular index it heapifies the array. 
     * Ensures that root is the max element.
     */
    public void maxHeapify(int index) {

        int lChild = getLeftChild(index);
        int rChild = getRightChild(index);
        int largest = index;

        if (lChild < size && arr[lChild] > arr[index]) {
            largest = lChild;
        }

        if (rChild < size && arr[rChild] > arr[largest]) {
            largest = rChild;
        }

        if (index != largest) {
            swap(index, largest);
            maxHeapify(largest);
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void insert(int val) {

        if (size == capacity)
            return;
        size++;
        arr[size - 1] = val;

        int posn = size - 1;
        int parent = getParent(posn);

        while (posn > 0 && arr[posn] > arr[parent]) {
            parent = getParent(posn);
            swap(posn, parent);
            posn = parent;
        }
    }

    public int removeMax() {

        if (size == 0)
            return Integer.MAX_VALUE;

        else if (size == 1) {
            size--;
            return arr[0];
        }

        else {

            int maxNode = arr[0];
            swap(0, size - 1);
            maxHeapify(0);
            size--;
            return maxNode;
        }
    }

    /**
     * Ensures max heap property i.e. root of every subtree is greater than its
     * children.
     */
    public void buildHeap() {

        int index = (size - 2) / 2;

        for (int i = index; i >= 0; i--) {
            maxHeapify(i);
        }

    }

    /**
     * It increases the value of a posn and then keeps on putting it to root of
     * subtree if it is greater than its parent, thus ensuring max heap.
     */

    public void increaseKey(int index, int value) {

        arr[index] = value;
        int parentIndex = getParent(index);

        while (index != 0 && arr[parentIndex] < arr[index]) {
            swap(parentIndex, index);
            index = parentIndex;
        }
    }

    /**
     * Deleting a node at an index
     */
    public void delete(int index) {

        increaseKey(index, Integer.MAX_VALUE);
        removeMax();
    }

    /**
     * Heap Sort code:
     * Convert array to a heap, now root contains highest value.
     * From last index to 1 index swap root i.e. 0 & last element 
     * reduce the size so that we dont include previous higher values. 
     * perform maxHeapify on 0 to keep geeting highest element on top.
     */

    public void heapSort() {

        buildHeap();

        for (int i = size - 1; i >= 1; i--) {
            swap(i, 0);
            size--;
            maxHeapify(0);
        }

    }

}
