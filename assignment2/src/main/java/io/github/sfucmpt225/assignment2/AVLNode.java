/**
 * One node of an AVLTree. Given: do not change this file.
 *
 * Each node stores a key, the value that goes with it, links to its two
 * children, and the height of the subtree rooted at this node. A leaf has
 * height 0, and an empty subtree (null) counts as height -1.
 */
class AVLNode<K, V> {
    K key;
    V value;
    int height;            // height of the subtree rooted at this node (a leaf has height 0)
    AVLNode<K, V> left;    // subtree with the smaller keys (null if none)
    AVLNode<K, V> right;   // subtree with the larger keys (null if none)

    /** Creates a leaf: no children, height 0. */
    AVLNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.height = 0;
        this.left = null;
        this.right = null;
    }
}
