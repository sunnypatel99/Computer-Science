import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of an AVL.
 *
 * @author Sunny Patel
 * @version 1.0
 * @userid spatel725
 * @GTID 903466059
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid input! Data is null!");
        } //if
        for (T item : data) {
            if (item == null) {
                throw new IllegalArgumentException("Item in data is null!");
            } else {
                add(item);
            } //else
        } //for
    } //AVL Constructor

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input! Data can't be null!");
        } //if
        root = addHelper(data, root);
    } //add

    /**
     * This is a helper method for the add(T data) method.
     *
     * @param data input data to be added to tree
     * @param curr is set to root of tree
     * @return balanced node
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> curr) {
        if (curr == null) {
            size++;
            return new AVLNode<T>(data);
        } //if
        //traverses to find proper spot
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        } else {
            return curr;
        } //else
        return balanceChecker(curr);
    } //addHelper

    /**
     * Helper method for add(T data).
     * Checks if the tree needs to be balanced based on current node.
     * Does the proper rotation if balancing is needed.
     *
     * @param curr node that will be balanced
     * @return balanced node
     */
    private AVLNode<T> balanceChecker(AVLNode<T> curr) {
        hBFCalc(curr);
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rightRotation(curr.getRight()));
            } //if
            curr = leftRotation(curr);
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(leftRotation(curr.getLeft()));
            } //if
            curr = rightRotation(curr);
        } //else if
        return curr;
    } //balanceChecker

    /**
     * Helper method for add(T data).
     * Right rotation helper method.
     *
     * @param curr Node to be right rotated
     * @return right-rotated node
     */
    private AVLNode<T> rightRotation(AVLNode<T> curr) {
        AVLNode<T> item = curr.getLeft();
        curr.setLeft(item.getRight());
        item.setRight(curr);
        hBFCalc(curr);
        hBFCalc(item);
        return item;
    } //rightRotation

    /**
     * Helper method for add(T data).
     * Left rotation helper method.
     *
     * @param curr Node to be left rotated
     * @return left-rotated node
     */
    private AVLNode<T> leftRotation(AVLNode<T> curr) {
        AVLNode<T> item = curr.getRight();
        curr.setRight(item.getLeft());
        item.setLeft(curr);
        hBFCalc(curr);
        hBFCalc(item);
        return item;
    } //leftRotation

    /**
     * Helper method for add(T data)
     * Calculates the height and balance factor
     *
     * @param curr node which needs its height and balance factor to be calculated.
     */
    private void hBFCalc(AVLNode<T> curr) {
        int left = height(curr.getLeft());
        int right = height(curr.getRight());
        curr.setHeight(Math.max(left, right) + 1);
        curr.setBalanceFactor(left - right);
    } //hBFCalc

    /**
     * Helper method for add(T data)
     * Returns height of a specific node.
     *
     * @param curr node to calculate height for
     * @return height of input node
     */
    private int height(AVLNode<T> curr) {
        if (curr != null) {
            return curr.getHeight();
        } else {
            return -1;
        } //else
    } //height

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node. Do NOT use the provided public
     * predecessor method to remove a 2-child node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input! Data can't be null!");
        } //if
        AVLNode<T> removedItem = new AVLNode<T>(null);
        root = removeHelper(data, root, removedItem);
        return removedItem.getData();
    } //remove

    /**
     * Helper method for remove(T data)
     *
     * @param data input data to search for and remove
     * @param curr current node
     * @param removedItem holds data of to be removed data
     * @return balanced node
     * @throws java.util.NoSuchElementException if tree is empty or data is not found in tree
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> curr, AVLNode<T> removedItem) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data to be removed is not found in tree!");
        } //if
        if (curr.getData().compareTo(data) == 0) {
            removedItem.setData(curr.getData());
            size--;
            if (curr.getRight() == null && curr.getLeft() == null) {
                return null;
                //case 1
            } else if (curr.getLeft() == null) {
                return curr.getRight();
                //case 2 right
            } else if (curr.getRight() == null) {
                return curr.getLeft();
                //case 2 left
            } else {
                AVLNode<T> child = new AVLNode<T>(curr.getData());
                curr.setRight(removeSuccessor(curr.getRight(), child));
                curr.setData(child.getData());
                //case 3 successor
            } //else
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                throw new java.util.NoSuchElementException("Data to be removed is not found in tree!");
            } //if
            curr.setRight(removeHelper(data, curr.getRight(), removedItem));
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                throw new java.util.NoSuchElementException("Data to be removed is not found in tree!");
            } //if
            curr.setLeft(removeHelper(data, curr.getLeft(), removedItem));
        } //else if
        curr = balanceChecker(curr);
        return curr;
    } //removeHelper

    /**
     * Helper method for remove(T data)
     * This method finds the successor
     *
     * @param curr current node
     * @param child child of a node to be removed
     * @return successor of node to be removed
     */
    private AVLNode<T> removeSuccessor(AVLNode<T> curr, AVLNode<T> child) {
        if (curr.getLeft() == null) {
            child.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), child));
            curr = balanceChecker(curr);
            return curr;
        } //else
    } //removeSuccessor

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input! Data can't be null!");
        } //if
        return getHelper(data, root);
    } //get

    /**
     * Helper method for get(T data)
     *
     * @param data data to search for
     * @param curr current node
     * @return data in tree that is the same as input data
     */
    private T getHelper(T data, AVLNode<T> curr) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data to be removed is not found in tree!");
        } //if
        if (data.compareTo(curr.getData()) < 0) {
            return getHelper(data, curr.getLeft());
        } else if (data.compareTo(curr.getData()) > 0) {
            return getHelper(data, curr.getRight());
        } else {
            return curr.getData();
        } //else
    } //getHelper

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input! Data can't be null!");
        } else {
            return containsHelper(data, root);
        } //else
    } //contains

    /**
     * Helper method for contains(T data)
     *
     * @param data data to search for if it exists in tree
     * @param curr current node
     * @return false if data is not in tree, true if data is in tree
     */
    private boolean containsHelper(T data, AVLNode<T> curr) {
        if (curr == null) {
            return false;
        } //containsHelper
        if (data.compareTo(curr.getData()) < 0) {
            return containsHelper(data, curr.getLeft());
        } else if (data.compareTo(curr.getData()) > 0) {
            return containsHelper(data, curr.getRight());
        } else {
            return true;
        } //else
    } //containsHelper

    /**
     * Returns the height of the root of the tree. Do NOT compute the height
     * recursively. This method should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        } //else
    } //height

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    } //clear

    /**
     * In your BST homework, you worked with the concept of the predecessor, the
     * largest data that is smaller than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the predecessor of the data
     * passed in. There are 3 cases to consider:
     * 1: The left subtree is non-empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 2: The left subtree is empty. In this case, the predecessor is the deepest
     * ancestor of the node containing data whose right child is also
     * an ancestor of data.
     * 3: If the data passed in is the minimum data in the tree, return null.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *     76
     *   /    \
     * 34      90
     *  \    /
     *  40  81
     * predecessor(76) should return 40
     * predecessor(81) should return 76
     *
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Input! Data can't be null!");
        } //if
        AVLNode<T> temp = new AVLNode<T>(null);
        AVLNode<T> pre = preHelper(data, root, temp);
        if (pre == null) {
            throw new java.util.NoSuchElementException("Data to be removed is not found in tree!");
        } //if
        return pre.getData();
    } //predecessor

    /**
     * Helper method for predecessor(T data)
     *
     * @param data data to search for
     * @param curr current node
     * @param pre this holds the predecessor
     * @return predecessor of input data
     */
    private AVLNode<T> preHelper(T data, AVLNode<T> curr, AVLNode<T> pre) {
        if (curr == null) {
            return null;
        } else {
            if (data.compareTo(curr.getData()) < 0) {
                return preHelper(data, curr.getLeft(), pre);
            } else if (data.compareTo(curr.getData()) > 0) {
                pre = curr;
                return preHelper(data, curr.getRight(), pre);
            } else {
                if (curr.getLeft() != null) {
                    return preHelper2(curr.getLeft());
                } //if
            } //else
            return pre;
        } //else
    } //preHelper

    /**
     * Helper method for predecessor(T data)
     * Keeps traversing to get to the rightmost node of the left subtree.
     *
     * @param curr current node
     * @return predecessor of data
     */
    private AVLNode<T> preHelper2(AVLNode<T> curr) {
        if (curr.getRight() != null) {
            return preHelper2(curr.getRight());
        } else {
            return curr;
        } //else
    } //preHelper2

    /**
     * Finds and retrieves the k-smallest elements from the AVL in sorted order,
     * least to greatest.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * kSmallest(0) should return the list []
     * kSmallest(5) should return the list [10, 12, 13, 15, 25].
     * kSmallest(3) should return the list [10, 12, 13].
     *
     * @param k the number of smallest elements to return
     * @return sorted list consisting of the k smallest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > n, the number
     *                                            of data in the AVL
     */
    public List<T> kSmallest(int k) {
        if (k < 0 || k > size) {
            throw new IllegalArgumentException("K has to be greater then 0 and k less then number of data in AVL!");
        } //if
        List<T> kList = new ArrayList<T>();
        if (root == null || k == 0) {
            return kList;
        } //if
        return kSmallestHelper(root, kList, k);
    } //kSmallest

    /**
     * Helper method for kSmallest(int k)
     *
     * @param curr current node
     * @param kList list containing k smallest nodes
     * @param k number of smallest elements to return
     * @return list of smallest nodes
     */
    private List<T> kSmallestHelper(AVLNode<T> curr, List<T> kList, int k) {
        if (kList.size() < k && curr.getLeft() != null) {
            kSmallestHelper(curr.getLeft(), kList, k);
        } //if
        if (kList.size() < k && curr != null) {
            kList.add(curr.getData());
        } //if
        if (kList.size() < k && curr.getRight() != null) {
            kSmallestHelper(curr.getRight(), kList, k);
        } //if
        return kList;
    } //kSmallestHelper

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
