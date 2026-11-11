/**
 * One node of a chain in a HashTable. Given: do not change this file.
 *
 * Each bucket of the table holds a singly linked list ("chain") of these
 * nodes: one node for every key that hashes to that bucket.
 */
class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;   // next node in the same chain (null at the end of the chain)

    HashNode(K key, V value, HashNode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
