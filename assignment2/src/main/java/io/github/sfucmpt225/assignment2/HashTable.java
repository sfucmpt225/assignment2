import java.util.ArrayList;
import java.util.List;

/**
 * Part 3: a map stored in a hash table with separate chaining.
 *
 * The table is an array of buckets. Each bucket holds a singly linked list
 * ("chain") of the HashNodes whose keys hash to that bucket:
 *
 *     buckets[0]  -> null
 *     buckets[1]  -> ["ann" | 7] -> ["kim" | 2] -> null     two keys collide here
 *     buckets[2]  -> ["bo" | 4] -> null
 *     ...
 *
 * A key's bucket is (key.hashCode() & 0x7fffffff) % buckets.length. When the
 * load factor (size / capacity) goes above 0.75, the table doubles its number
 * of buckets and moves every node to its bucket in the new array.
 *
 * Keys are compared with equals. Keys must not be null; you may assume values
 * are never null.
 */
public class HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;

    private HashNode<K, V>[] buckets;   // buckets[i] is the first node of chain i (null if empty)
    private int size;                   // number of keys in the table

    /** Creates an empty table with 16 buckets. */
    @SuppressWarnings("unchecked")
    public HashTable() {
        buckets = (HashNode<K, V>[]) new HashNode<?, ?>[INITIAL_CAPACITY];
        size = 0;
    }

    /** Returns the number of keys in the table. O(1). */
    public int size() {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.size");
    }

    /** Returns true if the table has no keys. O(1). */
    public boolean isEmpty() {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.isEmpty");
    }

    /** Returns the value stored with key, or null if key is not in the table. O(1) on average. */
    public V get(K key) {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.get");
    }

    /** Returns true if key is in the table. O(1) on average. */
    public boolean containsKey(K key) {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.containsKey");
    }

    /**
     * Adds key with the given value, or replaces the value if key is already
     * in the table. After adding a new key, doubles the number of buckets if
     * size > 0.75 * capacity. O(1) on average.
     *
     * @throws IllegalArgumentException if key is null
     */
    public void put(K key, V value) {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.put");
    }

    /** Removes key and returns its value, or returns null if key is not in the table. O(1) on average. */
    public V remove(K key) {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.remove");
    }

    /** Returns every value in the table, in no particular order. O(n + capacity). */
    public List<V> values() {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.values");
    }

    // ===================== Private helpers: you write these =====================
    // You may add more private helper methods.

    /**
     * Returns the bucket for key: a number from 0 to buckets.length - 1.
     * hashCode() can be negative, so the & 0x7fffffff clears the sign bit first.
     */
    private int indexFor(K key) {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.indexFor");
    }

    /**
     * Doubles the number of buckets and moves every node to its bucket in the
     * new array. Create the new array the same way the constructor does.
     */
    private void resize() {
        // TODO
        throw new UnsupportedOperationException("TODO: HashTable.resize");
    }

    // ===================== Given methods: do not change =====================

    /** Returns the number of buckets. */
    public int capacity() {
        return buckets.length;
    }

    /** Returns size / capacity: the average length of a chain. */
    public double loadFactor() {
        return (double) size / buckets.length;
    }
}
