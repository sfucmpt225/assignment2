import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Part 1: an ordered map stored in an AVL tree.
 *
 * An AVL tree is a binary search tree that stays balanced: at every node, the
 * heights of the left and right subtrees differ by at most 1. Its height is
 * therefore O(log n), so put, get and containsKey run in O(log n) time, even
 * when the keys arrive in sorted order.
 *
 * Every node stores the height of its own subtree (a leaf has height 0, and an
 * empty subtree counts as -1). After put adds a node, each node on the path
 * back up to the root updates its height and, if it has become unbalanced, is
 * fixed with one or two rotations.
 *
 * Keys must not be null; you may assume values are never null.
 */
public class AVLTree<K extends Comparable<K>, V> {

    private AVLNode<K, V> root;   // the root node (null when the tree is empty)
    private int size;             // number of keys in the tree

    /** Creates an empty tree. */
    public AVLTree() {
        root = null;
        size = 0;
    }

    /** Returns the number of keys in the tree. O(1). */
    public int size() {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.size");
    }

    /** Returns true if the tree has no keys. O(1). */
    public boolean isEmpty() {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.isEmpty");
    }

    /** Returns the height of the tree: -1 if it is empty, 0 if it has one node. O(1). */
    public int height() {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.height");
    }

    /** Returns the value stored with key, or null if key is not in the tree. O(log n). */
    public V get(K key) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.get");
    }

    /** Returns true if key is in the tree. O(log n). */
    public boolean containsKey(K key) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.containsKey");
    }

    /**
     * Adds key with the given value, or replaces the value if key is already
     * in the tree. Rebalances the tree as needed. O(log n).
     * Given: the work is done by the private put method below, which you write.
     *
     * @throws IllegalArgumentException if key is null
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        root = put(root, key, value);
    }

    /**
     * Returns the smallest key in the tree. O(log n).
     *
     * @throws NoSuchElementException if the tree is empty
     */
    public K minKey() {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.minKey");
    }

    /**
     * Returns the largest key in the tree. O(log n).
     *
     * @throws NoSuchElementException if the tree is empty
     */
    public K maxKey() {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.maxKey");
    }

    /**
     * Returns every value, in increasing order of their keys (an in-order traversal). O(n).
     * Given: the work is done by the private inOrder method below, which you write.
     */
    public List<V> valuesInOrder() {
        List<V> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    /**
     * Returns every key in pre-order: each node before its left subtree, then its right subtree. O(n).
     * Given: the work is done by the private preOrder method below, which you write.
     */
    public List<K> keysPreOrder() {
        List<K> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    /**
     * Returns the values of all keys k with low <= k <= high, in increasing
     * order of their keys. Returns an empty list if low > high. Visits only the
     * subtrees that can contain such keys: O(log n + m) for m results.
     * Given: the work is done by the private range method below, which you write.
     */
    public List<V> valuesInRange(K low, K high) {
        List<V> result = new ArrayList<>();
        range(root, low, high, result);
        return result;
    }

    // ===================== Private helpers: you write these =====================
    // You may add more private helper methods.

    /**
     * Adds key and value to the subtree rooted at node (or replaces the value
     * if key is already there) and returns the root of the subtree afterwards,
     * which is a different node if a rotation happened. Adds 1 to size only
     * when a new node is created.
     */
    private AVLNode<K, V> put(AVLNode<K, V> node, K key, V value) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.put(node, key, value)");
    }

    /**
     * Updates node's height and, if node is unbalanced (balance factor 2 or -2),
     * fixes it with one or two rotations. Returns the new root of this subtree.
     */
    private AVLNode<K, V> rebalance(AVLNode<K, V> node) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.rebalance");
    }

    /**
     * Rotates right around node and returns the new root of this subtree:
     *
     *          node               child
     *         /    \             /     \
     *      child    C    ==>    A      node
     *      /   \                       /   \
     *     A     B                     B     C
     *
     * Updates the heights of node and child (node first: it is now lower down).
     */
    private AVLNode<K, V> rotateRight(AVLNode<K, V> node) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.rotateRight");
    }

    /**
     * Rotates left around node and returns the new root of this subtree:
     *
     *       node                    child
     *      /    \                  /     \
     *     A    child     ==>    node      C
     *          /   \            /   \
     *         B     C          A     B
     *
     * Updates the heights of node and child (node first: it is now lower down).
     */
    private AVLNode<K, V> rotateLeft(AVLNode<K, V> node) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.rotateLeft");
    }

    /** Adds the values of the subtree rooted at node to result, in key order. */
    private void inOrder(AVLNode<K, V> node, List<V> result) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.inOrder");
    }

    /** Adds the keys of the subtree rooted at node to result, in pre-order. */
    private void preOrder(AVLNode<K, V> node, List<K> result) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.preOrder");
    }

    /**
     * Adds to result, in key order, the values in node's subtree whose keys are
     * in [low, high]. Skips the left subtree when node.key <= low, and the right
     * subtree when node.key >= high, because no key in range can be there.
     */
    private void range(AVLNode<K, V> node, K low, K high, List<V> result) {
        // TODO
        throw new UnsupportedOperationException("TODO: AVLTree.range");
    }

    // ===================== Given helpers: do not change =====================

    /** Returns the height of the subtree rooted at node: -1 for an empty subtree. */
    private int height(AVLNode<K, V> node) {
        return (node == null) ? -1 : node.height;
    }

    /** Recomputes node.height from the heights of its children, which must already be correct. */
    private void updateHeight(AVLNode<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /** Returns height(left) - height(right): positive when the left side is taller. */
    private int balanceFactor(AVLNode<K, V> node) {
        return height(node.left) - height(node.right);
    }

    /**
     * Returns a drawing of the tree turned on its side, for debugging and for
     * the app's "tree" command. The root is at the left edge; each node's right
     * subtree is drawn above it and its left subtree below it.
     */
    public String toTreeString() {
        if (root == null) {
            return "(empty tree)\n";
        }
        StringBuilder sb = new StringBuilder();
        draw(root, 0, sb);
        return sb.toString();
    }

    private void draw(AVLNode<K, V> node, int depth, StringBuilder sb) {
        if (node == null) {
            return;
        }
        draw(node.right, depth + 1, sb);
        for (int i = 0; i < depth; i++) {
            sb.append("      ");
        }
        sb.append(node.key).append('\n');
        draw(node.left, depth + 1, sb);
    }
}
