import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Part 2: a priority queue stored in a binary min-heap.
 *
 * The heap is a complete binary tree kept in an array, level by level. The
 * root is heap[0], and the node at index i has its children at 2i + 1 and
 * 2i + 2 and its parent at (i - 1) / 2. Every node is <= its children
 * (compared with compareTo), so the smallest item is always at the root.
 *
 *              1                 index:  0   1   2   3   4   5
 *            /   \               heap:  [1] [3] [2] [7] [4] [9]
 *           3     2
 *          / \   /
 *         7   4 9
 *
 * The array doubles in size whenever it is full.
 */
public class MinHeap<T extends Comparable<T>> {

    private static final int INITIAL_CAPACITY = 8;

    private T[] heap;   // heap[0 .. size-1] hold the items; heap[0] is the smallest
    private int size;   // number of items in the heap

    /** Creates an empty heap. */
    @SuppressWarnings("unchecked")
    public MinHeap() {
        heap = (T[]) new Comparable<?>[INITIAL_CAPACITY];
        size = 0;
    }

    /** Returns the number of items in the heap. O(1). */
    public int size() {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.size");
    }

    /** Returns true if the heap has no items. O(1). */
    public boolean isEmpty() {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.isEmpty");
    }

    /**
     * Adds item to the heap. O(log n), plus O(n) on the rare calls that grow the array.
     *
     * @throws IllegalArgumentException if item is null
     */
    public void add(T item) {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.add");
    }

    /**
     * Returns the smallest item without removing it. O(1).
     *
     * @throws NoSuchElementException if the heap is empty
     */
    public T peek() {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.peek");
    }

    /**
     * Removes and returns the smallest item. O(log n).
     *
     * @throws NoSuchElementException if the heap is empty
     */
    public T removeMin() {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.removeMin");
    }

    /**
     * Returns all items in increasing order, without changing this heap.
     * Copies the items into a second heap and removes them one by one. O(n log n).
     */
    public List<T> toSortedList() {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.toSortedList");
    }

    // ===================== Private helpers: you write these =====================
    // You may add more private helper methods.

    /** Moves the item at index up, swapping it with its parent while it is smaller than the parent. */
    private void siftUp(int index) {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.siftUp");
    }

    /** Moves the item at index down, swapping it with its smaller child while that child is smaller. */
    private void siftDown(int index) {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.siftDown");
    }

    /**
     * Replaces the array with one twice as long that holds the same items.
     * Create the new array the same way the constructor does.
     */
    private void grow() {
        // TODO
        throw new UnsupportedOperationException("TODO: MinHeap.grow");
    }

    // ===================== Given helpers: do not change =====================

    private static int parent(int index) {
        return (index - 1) / 2;
    }

    private static int left(int index) {
        return 2 * index + 1;
    }

    private static int right(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
